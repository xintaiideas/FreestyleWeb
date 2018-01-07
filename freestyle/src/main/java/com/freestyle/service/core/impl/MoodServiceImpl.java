package com.freestyle.service.core.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freestyle.domain.entity.admin.SysFile;
import com.freestyle.domain.entity.core.Account;
import com.freestyle.domain.entity.core.Comment;
import com.freestyle.domain.entity.core.CommentExample;
import com.freestyle.domain.entity.core.Mood;
import com.freestyle.domain.entity.core.MoodExample;
import com.freestyle.domain.entity.core.MoodFile;
import com.freestyle.domain.entity.core.MoodFileExample;
import com.freestyle.domain.view.core.CommentVo;
import com.freestyle.domain.view.core.MoodVo;
import com.freestyle.mapper.core.CommentMapper;
import com.freestyle.mapper.core.MoodFileMapper;
import com.freestyle.mapper.core.MoodMapper;
import com.freestyle.service.admin.ISysFileService;
import com.freestyle.service.commons.Page;
import com.freestyle.service.core.IAccountService;
import com.freestyle.service.core.IMoodService;
import com.freestyle.service.exception.EntityExistException;
import com.freestyle.service.exception.EntityNotFoundException;

@Service
@Transactional(readOnly=true)
public class MoodServiceImpl implements IMoodService {
	
	@Autowired
	private MoodMapper moodMapper;
	
	@Autowired
	private MoodFileMapper moodFileMapper;
	
	@Autowired
	private ISysFileService sysFileService;
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private CommentMapper commentMapper;

	@Override
	@Transactional(readOnly=false)
	public void publish(long accountId, String content, String type, Long[] moodFiles) {
		Mood mood = new Mood();
		mood.setContent(content);
		mood.setAccountId(accountId);
		mood.setType(type);
		this.moodMapper.insert(mood);
		if(moodFiles != null) {
			for(Long moodFileId : moodFiles) {
				MoodFile moodFile = new MoodFile();
				moodFile.setFileId(moodFileId);
				moodFile.setMoodId(mood.getId());
				this.moodFileMapper.insert(moodFile);
			}
		}
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteMood(long moodId) {
		this.moodMapper.deleteByPrimaryKey(moodId);
	}

	/*@Override
	public Mood getMood(long moodId) throws EntityNotFoundException {
		Mood mood = this.moodMapper.selectByPrimaryKey(moodId);
		if(mood == null) {
			throw new EntityNotFoundException("心情不存在，ID：" + moodId, Mood.class);
		}
		return mood;
	}

	@Override
	public void checkExistMood(long moodId) throws EntityNotFoundException {
		this.getMood(moodId);
	}*/

	@Override
	@Transactional(readOnly=false)
	public long uploadMoodFile(String name, String type, Long size, String md5,
			InputStream in) throws IOException, EntityExistException {
		long fileId = sysFileService.saveFile(md5, name, type, size, in);
		return fileId;
	}

	@Override
	public Page<MoodVo> pageList(Long accountId, Integer pageIndex, Integer pageSize) {
		MoodExample example = new MoodExample();
		example.setOffset((pageIndex - 1) * pageSize);
		example.setLimit(pageSize);
		if(accountId != null) {
			example.or().andAccountIdEqualTo(accountId);
		}
		example.setOrderByClause("created_time desc");
		int total = this.moodMapper.countByExample(example);
		List<Mood> moods = this.moodMapper.selectByExample(example);
		List<MoodVo> rows = new ArrayList<MoodVo>();
		for(Mood mood : moods) {
			MoodVo vo = new MoodVo();
			Account account;
			try {
				account = this.accountService.getAccount(mood.getAccountId());
				vo.setPublisherName(account.getRealname());
				vo.setHeadImgId(account.getHeadImgThumbId());
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
			}
			
			CommentExample commentExample = new CommentExample();
			commentExample.or().andMoodIdEqualTo(mood.getId());
			List<Comment> comments = this.commentMapper.selectByExample(commentExample);
			List<CommentVo> commentVo = new ArrayList<CommentVo>();
			for(Comment comment : comments) {
				CommentVo cVo = new CommentVo();
				cVo.setAccountId(comment.getAccountId());
				Account acct;
				try {
					acct = this.accountService.getAccount(comment.getAccountId());
					cVo.setCommentor(acct.getUsername());
					cVo.setHeadImgId(acct.getHeadImgId());
				} catch (EntityNotFoundException e) {
					e.printStackTrace();
				}
				cVo.setContent(comment.getContent());
				cVo.setCreatedTime(comment.getCreatedTime());
				cVo.setId(comment.getId());
				cVo.setMoodId(comment.getMoodId());
				cVo.setParentId(comment.getParentId());
				cVo.setUpdatedTime(comment.getUpdatedTime());
				commentVo.add(cVo);
			}
			vo.setComments(commentVo);
			
			vo.setAccountId(mood.getAccountId());
			vo.setContent(mood.getContent());
			vo.setCreatedTime(mood.getCreatedTime());
			vo.setId(mood.getId());
			vo.setType(mood.getType());
			vo.setUpdatedTime(mood.getUpdatedTime());
			
			MoodFileExample moodFileExample = new MoodFileExample();
			moodFileExample.or().andMoodIdEqualTo(mood.getId());
			List<MoodFile> moodFiles = this.moodFileMapper.selectByExample(moodFileExample);
			vo.setMoodFile(moodFiles);
			
			rows.add(vo);
		}
		Page<MoodVo> page = new Page<MoodVo>(total, rows, pageIndex, pageSize);
		return page;
	}
	
	
	@Override
	public SysFile getMoodFile(long moodFileId) throws EntityNotFoundException {
		MoodFile moodFile = this.moodFileMapper.selectByPrimaryKey(moodFileId);
		return this.sysFileService.getFile(moodFile.getFileId());
	}

	@Override
	public Page<MoodVo> aboutMe(long accountId, Integer pageIndex, Integer pageSize) {
		MoodExample example = new MoodExample();
		example.setOffset((pageIndex - 1) * pageSize);
		example.setLimit(pageSize);
		example.or().andAccountIdEqualTo(accountId);
		example.setOrderByClause("created_time desc");
		int total = this.moodMapper.countByExample(example);
		List<Mood> moods = this.moodMapper.selectByExample(example);
		List<MoodVo> rows = new ArrayList<MoodVo>();
		for(Mood mood : moods) {
			MoodVo vo = new MoodVo();
			Account account;
			try {
				account = this.accountService.getAccount(mood.getAccountId());
				vo.setPublisherName(account.getRealname());
				vo.setHeadImgId(account.getHeadImgThumbId());
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
			}
			
			CommentExample commentExample = new CommentExample();
			commentExample.or().andMoodIdEqualTo(mood.getId());
			List<Comment> comments = this.commentMapper.selectByExample(commentExample);
			List<CommentVo> commentVo = new ArrayList<CommentVo>();
			for(Comment comment : comments) {
				CommentVo cVo = new CommentVo();
				cVo.setAccountId(comment.getAccountId());
				Account acct;
				try {
					acct = this.accountService.getAccount(comment.getAccountId());
					cVo.setCommentor(acct.getUsername());
					cVo.setHeadImgId(acct.getHeadImgId());
				} catch (EntityNotFoundException e) {
					e.printStackTrace();
				}
				cVo.setContent(comment.getContent());
				cVo.setCreatedTime(comment.getCreatedTime());
				cVo.setId(comment.getId());
				cVo.setMoodId(comment.getMoodId());
				cVo.setParentId(comment.getParentId());
				cVo.setUpdatedTime(comment.getUpdatedTime());
				commentVo.add(cVo);
			}
			vo.setComments(commentVo);
			
			vo.setAccountId(mood.getAccountId());
			vo.setContent(mood.getContent());
			vo.setCreatedTime(mood.getCreatedTime());
			vo.setId(mood.getId());
			vo.setType(mood.getType());
			vo.setUpdatedTime(mood.getUpdatedTime());
			
			MoodFileExample moodFileExample = new MoodFileExample();
			moodFileExample.or().andMoodIdEqualTo(mood.getId());
			List<MoodFile> moodFiles = this.moodFileMapper.selectByExample(moodFileExample);
			vo.setMoodFile(moodFiles);
			
			rows.add(vo);
		}
		Page<MoodVo> page = new Page<MoodVo>(total, rows, pageIndex, pageSize);
		return page;
	}

}
