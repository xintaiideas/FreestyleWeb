package com.freestyle.service.core;

import java.util.List;

import com.freestyle.domain.entity.core.Friend;
import com.freestyle.domain.entity.core.FriendGroup;
import com.freestyle.domain.view.core.FriendVo;
import com.freestyle.service.exception.EntityNotFoundException;


public interface IFriendService {

	/**
	 * 添加好友
	 * @param myId	我的ID
	 * @param groupId	分组ID
	 * @param accountId	对方账户ID
	 * @param remark	备注名称
	 * @throws EntityNotFoundException
	 */
	void addFriend(Long myId,Long groupId, long accountId, String remark) throws EntityNotFoundException;
	
	/**
	 * 删除好友
	 * @param friendId	好友ID
	 */
	void deleteFriend(long friendId);
	
	/**
	 * 移动好友到指定分组
	 * @param groupId	分组ID
	 * @param friendId	好友ID
	 */
	void moveToGroup(long groupId,long friendId) throws EntityNotFoundException;
	
	/**
	 * 修改好友备注
	 * @param friendId	好友ID
	 * @param groupId	分组ID
	 * @param remark	备注名称
	 * @throws EntityNotFoundException
	 */
	void modifyRemark(long friendId, Long groupId,String remark) throws EntityNotFoundException;
	
	/**
	 * 获取好友信息
	 * @param friendId	好友ID
	 * @return
	 * @throws EntityNotFoundException
	 */
	Friend getFriend(long friendId) throws EntityNotFoundException;
	
	/**
	 * 检查好友是否存在
	 * @param friendId	好友ID
	 * @throws EntityNotFoundException
	 */
	void checkExistFriend(long friendId) throws EntityNotFoundException;
	
	/**
	 * 修改分组名称
	 * @param friendGroupId	好友分组ID
	 * @throws EntityNotFoundException
	 */
	void modifyGroupName(long friendGroupId, String name) throws EntityNotFoundException;
	
	/**
	 * 获取好友分组信息
	 * @param friendGroupId	分组ID
	 * @return
	 * @throws EntityNotFoundException
	 */
	FriendGroup getFriendGroup(long friendGroupId) throws EntityNotFoundException;
	
	/**
	 * 检查好友分组是否存在
	 * @param friendGroupId	分组ID
	 * @throws EntityNotFoundException
	 */
	void checkExistFriendGroup(long friendGroupId) throws EntityNotFoundException;

	/**
	 * 获取好友列表
	 * @param accountId	我的账户ID
	 * @param groupId	分组ID
	 * @param username	用户名
	 * @return
	 * @throws EntityNotFoundException
	 */
	List<FriendVo> friendList(long accountId, Long groupId, String username) throws EntityNotFoundException;

	/**
	 * 获取我的好友分组
	 * @param accountId	 我的账号ID
	 * @return
	 */
	List<FriendGroup> getMyFriendGroup(long accountId);

	/**
	 * 添加分组
	 * @param accountId	我的账号ID
	 * @param name	分组名称
	 * @return
	 */
	FriendGroup addGroup(long accountId, String name);

	/**
	 * 删除分组
	 * @param accountId	我的账号ID
	 * @param groupId	分组ID
	 */
	void deleteGroup(long accountId, Long groupId);

	/**
	 * 是否好友
	 * @param userId	我的账号ID
	 * @param id	对方账号ID
	 * @return
	 */
	boolean isFriend(Long userId, Long id);

}
