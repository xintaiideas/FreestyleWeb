package com.freestyle.service.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.freestyle.domain.entity.core.Photo;
import com.freestyle.domain.entity.core.VPhoto;
import com.freestyle.domain.entity.core.VRecentlyPhoto;
import com.freestyle.service.commons.Page;
import com.freestyle.service.exception.EntityExistException;
import com.freestyle.service.exception.EntityNotFoundException;

/**
 * 照片管理服务
 * @author 大爱阳哥
 *
 */
public interface IPhotoService {
	
	/**
	 * 获取相册所有照片
	 * @param albumId	相册ID
	 * @return
	 */
	List<Photo> list(long albumId);
	
	/**
	 * 获取照片分页列表数据
	 * @param albumId	相册ID
	 * @param pageIndex	页号
	 * @param pageSize	每页条数
	 * @return
	 */
	Page<VPhoto> pageList(long albumId, int pageIndex, int pageSize);

	/**
	 * 添加照片
	 * @param albumId	相册ID
	 * @param name	照片名称
	 * @param type	文件类型
	 * @param size	文件尺寸
	 * @param md5	文件md5
	 * @param in	文件输入流
	 * @throws EntityNotFoundException
	 * @throws IOException
	 * @throws EntityExistException
	 */
	void addPhoto(long albumId, String name, String type, long size,String md5, InputStream in) throws EntityNotFoundException, IOException, EntityExistException;
	
	/**
	 * 根据照片ID删除照片
	 * @param photoId	照片ID
	 */
	void deletePhoto(long photoId);
	
	/**
	 * 修改照片信息
	 * @param albumId 相册ID
	 * @param photoId	照片ID
	 * @param name	照片名称
	 * @throws EntityNotFoundException	照片不存在
	 */
	void modifyPhoto(long albumId, long photoId,String name) throws EntityNotFoundException;
	
	/**
	 * 获取照片信息
	 * @param photoId	照片ID
	 */
	Photo getPhoto(long photoId) throws EntityNotFoundException;
	
	/**
	 * 检查照片是否存在
	 * @param photoId	照片ID
	 * @throws EntityNotFoundException
	 */
	void checkExistPhoto(long photoId) throws EntityNotFoundException;

	/**
	 * 获取照片信息
	 * @param photoId	照片ID
	 * @return
	 * @throws EntityNotFoundException
	 */
	VPhoto getVPhoto(long photoId) throws EntityNotFoundException;

	/**
	 * 最近照片
	 * @param accountId	账号ID
	 * @param pageIndex	页号
	 * @param pageSize	每页条数
	 * @return
	 */
	Page<VRecentlyPhoto> recentlyPhoto(long accountId, int pageIndex,
			int pageSize);
	
}
