package com.freestyle.service.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.freestyle.domain.entity.admin.SysFile;
import com.freestyle.service.exception.EntityExistException;
import com.freestyle.service.exception.EntityNotFoundException;

public interface ISysFileService {
	
	/*临时文件存储文件夹名称*/
	public static String TEMP_FILE_FLODER = "TempFileFolder";
	
	/*目标存储文件夹名称*/
	public static String TARGET_FILE_FLODER = "TargetFileFolder";
	
	String getFileFolder();
	
	/**
	 * 获取临时文件存储文件夹
	 * @return
	 */
	File getTempFileFolder();
	
	/**
	 * 获取目标文件存储文件夹
	 * @return
	 */
	File getTargetFileFolder();
	
	/**
	 * 一次性保存文件
	 * @param filename	文件名
	 * @param contentType	文件类型
	 * @param size	文件尺寸
	 * @param in	文件输入流
	 * @throws IOException 
	 * @throws EntityExistException 
	 */
	long saveFile(String md5, String filename, String contentType, long size, InputStream in) throws IOException, EntityExistException;
	
	/**
	 * 保存文件
	 * @param uuid	文件唯一标识
	 * @param chunks	分片总数
	 * @param chunk		当前分片数
	 * @param filename	文件名称
	 * @param contentType	mime类型
	 * @param size		文件尺寸
	 * @param in	文件输入流
	 * @return 
	 * @throws EntityExistException 
	 */
	long saveFile(String uuid, String md5, int chunks, int chunk, String filename, String contentType, long size, InputStream in) throws IOException, EntityExistException;

	/**
	 * 通过文件md5获取文件信息
	 * @param md5
	 * @return
	 * @throws EntityNotFoundException
	 */
	SysFile getFile(String md5) throws EntityNotFoundException;
	
	/**
	 * 获取文件信息
	 * @param fileId
	 * @return
	 */
	SysFile getFile(long fileId) throws EntityNotFoundException;
	
	/**
	 * 检查文件是否存在
	 * @param fileId
	 * @throws EntityNotFoundException
	 */
	void checkExistFile(long fileId) throws EntityNotFoundException;

	/**
	 * 是否存在文件
	 * @param md5
	 * @return
	 */
	boolean existFile(String md5);
	
	void incRefCount(long fileId) throws EntityNotFoundException;

	void mulRefCount(long fileId) throws EntityNotFoundException;
}
