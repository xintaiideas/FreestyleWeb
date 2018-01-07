package com.freestyle.service.core;

import java.io.IOException;
import java.io.InputStream;

import com.freestyle.domain.entity.admin.SysFile;
import com.freestyle.domain.view.core.MoodVo;
import com.freestyle.service.commons.Page;
import com.freestyle.service.exception.EntityExistException;
import com.freestyle.service.exception.EntityNotFoundException;


public interface IMoodService {

	/**
	 *  发表心情
	 * @param accountId	我的账号ID
	 * @param content	心情内容
	 * @param type	心情类型
	 * @param moodFiles	心情文件ID
	 */
	void publish(long accountId, String content,String type,Long[] moodFiles);
	
	/**
	 * 删除心情
	 * @param moodId	心情ID
	 */
	void deleteMood(long moodId);
	
	/**
	 * 获取心情信息
	 * @param moodId	心情ID
	 */
//	Mood getMood(long moodId) throws EntityNotFoundException;
	
	/**
	 * 检查心情是否存在
	 * @param moodId	心情ID
	 * @throws EntityNotFoundException
	 */
//	void checkExistMood(long moodId) throws EntityNotFoundException;

	/**
	 * 上传心情文件
	 * @param name	文件名称
	 * @param type	文件类型
	 * @param size	文件尺寸
	 * @param md5	文件md5
	 * @param inputStream	文件输入流
	 * @return 
	 * @throws EntityExistException 
	 * @throws IOException 
	 */
	long uploadMoodFile(String name, String type, Long size, String md5,
			InputStream inputStream) throws IOException, EntityExistException;

	/**
	 * 心情分页列表数据
	 * @param accountId	账号ID
	 * @param pageIndex	页号
	 * @param pageSize	每页条数
	 * @return
	 */
	Page<MoodVo> pageList(Long accountId, Integer pageIndex, Integer pageSize);

	/**
	 * 获取心情文件
	 * @param moodFileId	心情文件ID
	 * @return
	 * @throws EntityNotFoundException
	 */
	SysFile getMoodFile(long moodFileId) throws EntityNotFoundException;

	/**
	 * 获取与我相关心情分页列表数据
	 * @param accountId	账号ID
	 * @param pageIndex	页号
	 * @param pageSize	每页条数
	 * @return
	 */
	Page<MoodVo> aboutMe(long accountId, Integer pageIndex, Integer pageSize);
}
