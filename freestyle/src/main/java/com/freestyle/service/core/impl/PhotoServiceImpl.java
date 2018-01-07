package com.freestyle.service.core.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freestyle.domain.entity.core.Photo;
import com.freestyle.domain.entity.core.PhotoExample;
import com.freestyle.domain.entity.core.VPhoto;
import com.freestyle.domain.entity.core.VPhotoExample;
import com.freestyle.domain.entity.core.VRecentlyPhoto;
import com.freestyle.domain.entity.core.VRecentlyPhotoExample;
import com.freestyle.mapper.core.PhotoMapper;
import com.freestyle.mapper.core.VPhotoMapper;
import com.freestyle.mapper.core.VRecentlyPhotoMapper;
import com.freestyle.service.admin.ISysFileService;
import com.freestyle.service.commons.Page;
import com.freestyle.service.core.IAlbumService;
import com.freestyle.service.core.IPhotoService;
import com.freestyle.service.exception.EntityExistException;
import com.freestyle.service.exception.EntityNotFoundException;

@Service
@Transactional(readOnly=true)
public class PhotoServiceImpl implements IPhotoService {
	
	//	自动注入，控制器、服务、mapper在程序启动时都自动生成了单例对象，只需要自动注入就能获取到这个对象，不需要new
	@Autowired
	private PhotoMapper photoMapper;
	
	@Autowired
	private VPhotoMapper vphotoMapper;
	
	@Autowired
	private VRecentlyPhotoMapper vrecentlyPhotoMapper;
	
	@Autowired
	private IAlbumService albumService;
	
	@Autowired
	private ISysFileService sysFileService;

	@Override
	@Transactional(readOnly=false)
	public void deletePhoto(long photoId) {
		try {
			Photo photo = this.getPhoto(photoId);
			this.albumService.mulPhotoCount(photo.getAlbumId());
			photoMapper.deleteByPrimaryKey(photoId);
			this.sysFileService.mulRefCount(photo.getFileId());
		} catch (EntityNotFoundException e) {
		}
	}

	@Override
	@Transactional(readOnly=false)
	public void modifyPhoto(long albumId, long photoId, String name) throws EntityNotFoundException {
		Photo photo = this.getPhoto(photoId);
		photo.setName(name);
		if(photo.getAlbumId() != albumId) {
			this.albumService.mulPhotoCount(photo.getAlbumId());
			this.albumService.incPhotoCount(albumId);
		}
		photo.setAlbumId(albumId);
		photoMapper.updateByPrimaryKeySelective(photo);
	}
	
	@Override
	public VPhoto getVPhoto(long photoId) throws EntityNotFoundException {
		VPhotoExample example = new VPhotoExample();
		example.or().andIdEqualTo(photoId);
		List<VPhoto> photos = vphotoMapper.selectByExample(example);
		if(photos.isEmpty()) {
			throw new EntityNotFoundException("照片不存在，ID：" + photoId, VPhoto.class);
		}
		return photos.get(0);
	}

	@Override
	public Photo getPhoto(long photoId) throws EntityNotFoundException {
		Photo photo = photoMapper.selectByPrimaryKey(photoId);
		if(photo == null) {
			throw new EntityNotFoundException("照片不存在，ID：" + photoId, Photo.class);
		}
		return photo;
	}

	@Override
	public void checkExistPhoto(long photoId) throws EntityNotFoundException {
		this.getPhoto(photoId);
	}

	@Override
	public Page<VPhoto> pageList(long albumId, int pageIndex, int pageSize) {
		VPhotoExample example = new VPhotoExample();
		example.or().andAlbumIdEqualTo(albumId);
		int total = this.vphotoMapper.countByExample(example);
		example.setOffset((pageIndex - 1) * pageSize);
		example.setLimit(pageSize);
		List<VPhoto> list = this.vphotoMapper.selectByExample(example);
		Page<VPhoto> page = new Page<VPhoto>(total, list, pageIndex, pageSize);
		return page;
	}

	@Override
	@Transactional(readOnly=false)
	public void addPhoto(long albumId, String name, String type, long size, String md5,
			InputStream in) throws EntityNotFoundException, IOException, EntityExistException {
		long fileId = this.sysFileService.saveFile(md5, name, type, size, in);
		if(fileId != -1) {
			this.albumService.incPhotoCount(albumId);
			Photo photo = new Photo();
			photo.setAlbumId(albumId);
			photo.setName(name);
			photo.setFileId(fileId);
			this.photoMapper.insertSelective(photo);
		}
	}

	@Override
	public List<Photo> list(long albumId) {
		PhotoExample example = new PhotoExample();
		example.or().andAlbumIdEqualTo(albumId);
		List<Photo> list = this.photoMapper.selectByExample(example);
		return list;
	}

	@Override
	public Page<VRecentlyPhoto> recentlyPhoto(long accountId, int pageIndex, int pageSize) {
		VRecentlyPhotoExample example = new VRecentlyPhotoExample();
		example.setOffset((pageIndex - 1) * pageSize);
		example.setLimit(pageSize);
		example.or().andAccountIdEqualTo(accountId);
		example.setOrderByClause("created_time desc");
		List<VRecentlyPhoto> list = this.vrecentlyPhotoMapper.selectByExample(example);
		int total = this.vrecentlyPhotoMapper.countByExample(example);
		Page<VRecentlyPhoto> page = new Page<VRecentlyPhoto>(total, list, pageIndex, pageSize); 
		return page;
	}
}
