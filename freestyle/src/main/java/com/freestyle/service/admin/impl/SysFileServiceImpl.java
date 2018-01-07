package com.freestyle.service.admin.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freestyle.domain.entity.admin.SysFile;
import com.freestyle.domain.entity.admin.SysFileExample;
import com.freestyle.mapper.admin.SysFileMapper;
import com.freestyle.service.admin.ISysFileService;
import com.freestyle.service.exception.EntityExistException;
import com.freestyle.service.exception.EntityNotFoundException;
import com.google.common.base.Strings;

@Service
@Transactional(readOnly=true)
public class SysFileServiceImpl implements ISysFileService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SysFileMapper sysFileMapper;
	
	@Value("${file.folder}")
	private String fileFolder;	//	TODO 存储路径
	
	public void setFileFolder(String fileFolder) {
		this.fileFolder = fileFolder;
	}

	@Override
	public long saveFile(String uuid, String md5, int chunks, int chunk, String filename,
			String contentType, long size, InputStream in) throws IOException, EntityExistException {
		SysFile file = this.getFile(md5);
		if(file != null) {
			try {
				this.incRefCount(file.getId());
			} catch (EntityNotFoundException e) {
				return -1;
			}
			return file.getId();
		}
		
		if(chunks == 1 && chunk == 1) {		//	文件无分片
			return saveTargetFile(uuid, md5, filename, contentType, size, in);
		}else if(chunks > 1 && chunk > 0 && chunk <= chunks) {	//	文件有分片
			File tempFileUuidFolder = new File(getTempFileFolder(),uuid);
			if(!tempFileUuidFolder.exists() || !tempFileUuidFolder.isDirectory()) {
				tempFileUuidFolder.mkdirs();
			}
			File tempFile = new File(tempFileUuidFolder, uuid + "_" + chunk);
			if(tempFile.exists()) {
				return -1;
			}
			FileUtils.copyInputStreamToFile(in, tempFile);
			//	判断分片是否接收完成，需要注意并发问题
			boolean uploadDone = true;
			for(int i = 1 ; i <= chunks ; i ++ ) {
				File mFile = new File(tempFileUuidFolder, uuid + "_" + i);
				if(!mFile.exists() || mFile.isDirectory()) {
					uploadDone = false;
				}
			}
			if(uploadDone) {
				File targetFile = new File(getTargetFileFolder(), uuid);
				if(targetFile.exists() && targetFile.isFile()) {
					logger.info("文件已存在，md5:" + uuid);
					return -1;
				}
				FileOutputStream out = new FileOutputStream(targetFile);
				for(int i = 1 ; i <= chunks ; i ++ ) {
					File partFile = new File(tempFileUuidFolder,uuid + "_" + i);
					FileUtils.copyFile(partFile, out);
				}
				out.close();
				FileUtils.deleteDirectory(tempFileUuidFolder);
			}
			return saveSysFile(uuid, filename, md5, contentType,size);
		}else {
			throw new IllegalArgumentException("分片参数不正确，Chunks:" + chunks + ",Chunk:" + chunk);
		}
	}
	
	private long saveTargetFile(String uuid, String md5, String filename, String contentType, long size, InputStream in) throws IOException {
		File targetFile = new File(getTargetFileFolder(),uuid);
		FileUtils.copyInputStreamToFile(in, targetFile);
		return saveSysFile(uuid, filename, md5, contentType,size);
	}
	
	private long saveSysFile(String uuid, String filename, String md5, String contentType,long size) {
		SysFile sysFile = new SysFile();
		sysFile.setFilename(filename);
		sysFile.setMd5(md5);
		sysFile.setMime(contentType);
		sysFile.setRefCount(1);
		sysFile.setSize(size);
		sysFile.setUri(TARGET_FILE_FLODER + "/" + uuid);	//	TODO 相对路径
		this.sysFileMapper.insert(sysFile);
		return sysFile.getId();
	}
	
	@Override
	public File getTempFileFolder() {
		File fTempFileFolder = new File(fileFolder + File.separator + TEMP_FILE_FLODER);
		if(!fTempFileFolder.exists() || !fTempFileFolder.isDirectory()) {
			fTempFileFolder.mkdirs();
		}
		return fTempFileFolder;
	}

	@Override
	public File getTargetFileFolder() {
		File fTargetFileFolder = new File(fileFolder + File.separator + TARGET_FILE_FLODER);
		if(!fTargetFileFolder.exists() || !fTargetFileFolder.isDirectory()) {
			fTargetFileFolder.mkdirs();
		}
		return fTargetFileFolder;
	}
	
	@Override
	public boolean existFile(String md5) {
		if(Strings.isNullOrEmpty(md5)) {
			return false;
		}
		SysFile sysFile = getFile(md5);
		if(sysFile == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public SysFile getFile(String md5) {
		SysFileExample example = new SysFileExample();
		example.or().andMd5EqualTo(md5);
		List<SysFile> files = this.sysFileMapper.selectByExample(example);
		if(files.isEmpty()) {
			return null;
		}
		return files.get(0);
	}

	@Override
	public SysFile getFile(long fileId) throws EntityNotFoundException {
		SysFile sysFile = sysFileMapper.selectByPrimaryKey(fileId);
		if(sysFile == null) {
			throw new EntityNotFoundException("文件不存在，ID：" + fileId, SysFile.class);
		}
		return sysFile;
	}

	@Override
	public void checkExistFile(long fileId) throws EntityNotFoundException {
		this.getFile(fileId);
	}

	@Override
	public long saveFile(String md5, String filename, String contentType,
			long size, InputStream in) throws IOException, EntityExistException {
		return saveFile(md5, md5, 1, 1, filename, contentType, size, in);
	}

	@Override
	public String getFileFolder() {
		return this.fileFolder;
	}

	@Override
	@Transactional(readOnly=false)
	public void incRefCount(long fileId) throws EntityNotFoundException {
		SysFile sysFile = this.getFile(fileId);
		sysFile.setRefCount(sysFile.getRefCount() + 1);
		this.sysFileMapper.updateByPrimaryKey(sysFile);
	}

	@Override
	@Transactional(readOnly=false)
	public void mulRefCount(long fileId) throws EntityNotFoundException {
		SysFile sysFile = this.getFile(fileId);
		if(sysFile.getRefCount() <= 1) {
			File file = new File(this.fileFolder + "/" + sysFile.getUri());
			if(file.exists()) {
				file.delete();
			}
			this.sysFileMapper.deleteByPrimaryKey(fileId);
		}else {
			sysFile.setRefCount(sysFile.getRefCount() - 1);
			this.sysFileMapper.updateByPrimaryKey(sysFile);
		}
		
	}
	
	//	String uuid, String md5, int chunks, int chunk, String filename,String contentType, long size, InputStream in
	/**
	 * 	如果是不分片文件上传，不需要uuid，chunks，chunk，因为可以内部自动生成uuid
	 * 		如果不带md5
	 * 			写入磁盘，入库
	 * 	如果是分片上传，需要uuid、chunks、chunk
	 * 		如果不带md5
	 */
	

}
