package com.freestyle.service.core;

import java.util.List;

import com.freestyle.domain.entity.core.Album;
import com.freestyle.domain.entity.core.VAlbum;
import com.freestyle.domain.view.core.HotPhotoVo;
import com.freestyle.service.commons.Page;
import com.freestyle.service.exception.EntityExistException;
import com.freestyle.service.exception.EntityNotFoundException;

/**
 * 相册管理服务
 * @author 大爱阳哥
 *
 */
public interface IAlbumService {
	
	/**
	 * 获取相册分页列表数据
	 * @param accountId	账号ID
	 * @param pageIndex	页号
	 * @param pageSize	每页条数
	 * @return
	 */
	Page<VAlbum> pageList(long accountId, int pageIndex, int pageSize);

	/**
	 * 创建相册
	 * @param accountId	账号ID
	 * @param name	相册名称
	 * @param description
	 * @throws EntityNotFoundException
	 * @throws EntityExistException
	 */
	void createAlbum(long accountId, String name, String description) throws EntityNotFoundException,EntityExistException;
	
	/**
	 * 删除相册
	 * @param albumId	相册ID
	 */
	void deleteAlbum(long albumId);
	
	/**
	 * 修改相册信息
	 * @param albumId	相册ID
	 * @param name	相册名称
	 * @param description
	 * @throws EntityNotFoundException
	 */
	void modifyAlbum(long albumId, String name, String description) throws EntityNotFoundException;
	
	/**
	 * 获取相册信息
	 * @param albumId	相册ID
	 * @return
	 * @throws EntityNotFoundException
	 */
	Album getAlbum(long albumId) throws EntityNotFoundException;
	
	/**
	 * 检查相册是否存在
	 * @param albumId	相册ID
	 * @throws EntityNotFoundException
	 */
	void checkExistAlbum(long albumId) throws EntityNotFoundException;

	/**
	 * 获取相册信息列表
	 * @param accountId	账号ID
	 * @return
	 */
	List<Album> list(long accountId);

	/**
	 * 根据相册名称判断是否存在
	 * @param name	相册名称
	 * @return
	 */
	boolean existAlbum(String name);

	/**
	 * 更新相册
	 * @param album	相册实体
	 */
	void updateAlbum(Album album);

	/**
	 * 照片数量+1
	 * @param albumId
	 */
	void mulPhotoCount(long albumId);

	/**
	 * 相册数量-1
	 * @param albumId
	 */
	void incPhotoCount(long albumId);

	/**
	 * 设置封面
	 * @param albumId	相册ID
	 * @param photoId	照片ID
	 * @throws EntityNotFoundException 
	 */
	void cover(Long albumId, Long photoId) throws EntityNotFoundException;

	/**
	 * 获取随机照片信息
	 */
	HotPhotoVo getHotPhoto() throws EntityNotFoundException;
}
