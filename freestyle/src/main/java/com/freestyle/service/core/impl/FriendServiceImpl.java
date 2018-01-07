package com.freestyle.service.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freestyle.domain.entity.core.Account;
import com.freestyle.domain.entity.core.AccountExample;
import com.freestyle.domain.entity.core.Friend;
import com.freestyle.domain.entity.core.FriendExample;
import com.freestyle.domain.entity.core.FriendGroup;
import com.freestyle.domain.entity.core.FriendGroupExample;
import com.freestyle.domain.view.core.FriendVo;
import com.freestyle.mapper.core.AccountMapper;
import com.freestyle.mapper.core.FriendGroupMapper;
import com.freestyle.mapper.core.FriendMapper;
import com.freestyle.service.core.IAccountService;
import com.freestyle.service.core.IFriendService;
import com.freestyle.service.exception.EntityNotFoundException;
import com.google.common.base.Strings;

@Service
@Transactional(readOnly=true)
public class FriendServiceImpl implements IFriendService {

	@Autowired
	private FriendMapper friendMapper;
	
	@Autowired
	private FriendGroupMapper friendGroupMapper;
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	@Transactional(readOnly=false)
	public void addFriend(Long myId, Long groupId, long accountId, String remark) throws EntityNotFoundException {
		accountService.checkExistAccount(accountId);
		this.checkExistFriendGroup(groupId);
		
		Friend friend = new Friend();
		friend.setAccountId(myId);
		friend.setFriendId(accountId);
		friend.setGroupId(groupId);
		friend.setRemark(remark);
		this.friendMapper.insert(friend);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteFriend(long friendId) {
		friendMapper.deleteByPrimaryKey(friendId);
	}

	@Override
	@Transactional(readOnly=false)
	public void moveToGroup(long groupId, long friendId) throws EntityNotFoundException {
		Friend friend = this.getFriend(friendId);
		friend.setGroupId(groupId);
		friendMapper.updateByPrimaryKeySelective(friend);
	}

	@Override
	@Transactional(readOnly=false)
	public void modifyRemark(long friendId, Long groupId, String remark) throws EntityNotFoundException {
		Friend friend = this.getFriend(friendId);
		friend.setRemark(remark);
		friend.setGroupId(groupId);
		this.friendMapper.updateByPrimaryKeySelective(friend);
	}

	@Override
	public Friend getFriend(long friendId) throws EntityNotFoundException {
		Friend friend = this.friendMapper.selectByPrimaryKey(friendId);
		if(friend == null) {
			throw new EntityNotFoundException("好友不存在，ID：" + friendId, Friend.class);
		}
		return friend;
	}

	@Override
	public void checkExistFriend(long friendId) throws EntityNotFoundException {
		this.getFriend(friendId);
	}

	@Override
	public FriendGroup getFriendGroup(long friendGroupId)
			throws EntityNotFoundException {
		FriendGroup group = this.friendGroupMapper.selectByPrimaryKey(friendGroupId);
		if(group == null) {
			throw new EntityNotFoundException("好友分组不存在，ID：" + friendGroupId, FriendGroup.class);
		}
		return group;
	}

	@Override
	public void checkExistFriendGroup(long friendGroupId)
			throws EntityNotFoundException {
		this.getFriendGroup(friendGroupId);
	}

	@Override
	@Transactional(readOnly=false)
	public void modifyGroupName(long friendGroupId, String name)
			throws EntityNotFoundException {
		FriendGroup group = this.getFriendGroup(friendGroupId);
		group.setName(name);
		this.friendGroupMapper.updateByPrimaryKeySelective(group);
	}

	@Override
	public List<FriendVo> friendList(long accountId,Long groupId, String username) throws EntityNotFoundException {
		FriendExample example = new FriendExample();
		if(groupId != null) {
			example.or().andGroupIdEqualTo(groupId);
		}
		List<Friend> list = this.friendMapper.selectByExample(example);
		
		List<Account> accounts = new ArrayList<Account>();
		if(!Strings.isNullOrEmpty(username)) {
			AccountExample accountExample = new AccountExample();
			accountExample.or().andUsernameLike("%" + username + "%");
			accounts.addAll(this.accountMapper.selectByExample(accountExample));
		}
		
		List<FriendVo> friendVos = new ArrayList<FriendVo>();
		for(Friend friend : list) {
			FriendVo vo = new FriendVo();
			vo.setId(friend.getId());
			vo.setAccountId(friend.getAccountId());
			vo.setFriendId(friend.getFriendId());
			vo.setFriendRemark(friend.getRemark());
			vo.setFriendTime(friend.getCreatedTime());
			vo.setIsFriend(true);
			Account account = this.accountService.getAccount(friend.getFriendId());
			vo.setAccountName(account.getUsername());
			vo.setRealname(account.getRealname());
			friendVos.add(vo);
		}
		
		for(Account account : accounts) {
			FriendVo vo = new FriendVo();
			vo.setAccountId(account.getId());
			vo.setFriendId(account.getId());
			vo.setAccountName(account.getUsername());
			vo.setIsSelf(account.getId().equals(accountId));
			vo.setRealname(account.getRealname());
			vo.setIsFriend(false);
			if(!friendVos.contains(vo)) {
				vo.setFriendId(null);
				friendVos.add(vo);
			}
		}
		return new ArrayList<FriendVo>(friendVos);
	}

	@Override
	public List<FriendGroup> getMyFriendGroup(long accountId) {
		FriendGroupExample groupExample = new FriendGroupExample();
		groupExample.or().andAccountIdEqualTo(accountId);
		List<FriendGroup> groups = this.friendGroupMapper.selectByExample(groupExample);
		return groups;
	}

	@Override
	@Transactional(readOnly=false)
	public FriendGroup addGroup(long accountId, String name) {
		FriendGroup group = new FriendGroup();
		group.setAccountId(accountId);
		group.setName(name);
		this.friendGroupMapper.insert(group);
		return group;
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteGroup(long accountId, Long groupId) {
		this.friendGroupMapper.deleteByPrimaryKey(groupId);
	}

	@Override
	public boolean isFriend(Long userId, Long id) {
		FriendExample example = new FriendExample();
		FriendExample.Criteria criteria = example.createCriteria();
		criteria.andAccountIdEqualTo(userId);
		criteria.andFriendIdEqualTo(id);
		int count = this.friendMapper.countByExample(example);
		if(count > 0) {
			return true;
		}
		return false;
	}
	
}
