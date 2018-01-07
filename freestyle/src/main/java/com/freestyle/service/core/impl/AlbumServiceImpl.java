package com.freestyle.service.core.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freestyle.domain.entity.core.Account;
import com.freestyle.domain.entity.core.Album;
import com.freestyle.domain.entity.core.AlbumExample;
import com.freestyle.domain.entity.core.Photo;
import com.freestyle.domain.entity.core.VAlbum;
import com.freestyle.domain.entity.core.VAlbumExample;
import com.freestyle.domain.entity.core.VPhoto;
import com.freestyle.domain.view.core.HotPhotoVo;
import com.freestyle.mapper.core.AlbumMapper;
import com.freestyle.mapper.core.PhotoMapper;
import com.freestyle.mapper.core.VAlbumMapper;
import com.freestyle.service.commons.Page;
import com.freestyle.service.core.IAccountService;
import com.freestyle.service.core.IAlbumService;
import com.freestyle.service.core.IPhotoService;
import com.freestyle.service.exception.EntityExistException;
import com.freestyle.service.exception.EntityNotFoundException;

@Service
@Transactional(readOnly=true)
public class AlbumServiceImpl implements IAlbumService {
	
	@Autowired
	private IPhotoService photoService;
	
	@Autowired
	private AlbumMapper albumMapper;
	
	@Autowired
	private PhotoMapper photoMapper;
	
	@Autowired
	private VAlbumMapper valbumMapper;
	
	@Autowired
	private IAccountService accountService;

	@Override
	@Transactional(readOnly=false)
	public void createAlbum(long accountId, String name, String description) throws EntityNotFoundException, EntityExistException {
		this.accountService.checkExistAccount(accountId);
		if(existAlbum(name)) {
			throw new EntityExistException("相册名称已存在,名称：" + name, Album.class);
		}
		Album album = new Album();
		album.setAccountId(accountId);
		album.setName(name);
		album.setDescription(description);
		album.setPhotoCount(0);
		album.setVisitsCount(0);
		album.setAgreeCount(0);
		album.setCommentCount(0);
		album.setCoverPhotoId(null);
		albumMapper.insertSelective(album);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteAlbum(long albumId) {
		List<Photo> photos = this.photoService.list(albumId);
		for(Photo photo : photos) {
			this.photoService.deletePhoto(photo.getId());
		}
		albumMapper.deleteByPrimaryKey(albumId);
	}

	@Override
	@Transactional(readOnly=false)
	public void modifyAlbum(long albumId, String name, String description) throws EntityNotFoundException {
		Album album = this.getAlbum(albumId);
		album.setName(name);
		album.setDescription(description);
		albumMapper.updateByPrimaryKeySelective(album);
	}

	@Override
	public Album getAlbum(long albumId) throws EntityNotFoundException {
		Album album = this.albumMapper.selectByPrimaryKey(albumId);
		if(album == null) {
			throw new EntityNotFoundException("相册不存在，ID：" + albumId, Album.class);
		}
		return album;
	}

	@Override
	public void checkExistAlbum(long albumId) throws EntityNotFoundException {
		this.getAlbum(albumId);
	}
	
	@Override
	public boolean existAlbum(String name) {
		AlbumExample example = new AlbumExample();
		example.or().andNameEqualTo(name);
		int count = this.albumMapper.countByExample(example);
		if(count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 分页查询相册，通过example来设置查询条件，这是通过mybatis来实现的
	 * mybatis是一个数据库操作框架
	 */
	@Override
	public Page<VAlbum> pageList(long accountId, int pageIndex, int pageSize) {
		VAlbumExample example = new VAlbumExample();
		//	设置账号 accountId == 具体的账号ID 
		example.or().andAccountIdEqualTo(accountId);
		int total = this.valbumMapper.countByExample(example);
		example.setOffset((pageIndex - 1) * pageSize);
		example.setLimit(pageSize);
		List<VAlbum> list = this.valbumMapper.selectByExample(example);
		Page<VAlbum> page = new Page<VAlbum>(total,list,pageIndex,pageSize);
		return page;
	}

	@Override
	public List<Album> list(long accountId) {
		AlbumExample example = new AlbumExample();
		example.or().andAccountIdEqualTo(accountId);
		return this.albumMapper.selectByExample(example);
	}

	@Override
	@Transactional(readOnly=false)
	public void updateAlbum(Album album) {
		this.albumMapper.updateByPrimaryKey(album);
	}

	@Override
	@Transactional(readOnly=false)
	public void mulPhotoCount(long albumId) {
		try {
			Album album = this.getAlbum(albumId);
			if(album.getPhotoCount() < 1) {
				album.setPhotoCount(0);
			}else {
				album.setPhotoCount(album.getPhotoCount() - 1);
			}
			this.updateAlbum(album);
		} catch (EntityNotFoundException e) {}
	}

	@Override
	@Transactional(readOnly=false)
	public void incPhotoCount(long albumId) {
		try {
			Album album = this.getAlbum(albumId);
			album.setPhotoCount(album.getPhotoCount() + 1);
			this.updateAlbum(album);
		} catch (EntityNotFoundException e) {}
	}

	@Override
	@Transactional(readOnly=false)
	public void cover(Long albumId, Long photoId) throws EntityNotFoundException {
		Album album = this.getAlbum(albumId);
		album.setCoverPhotoId(photoId);
		this.albumMapper.updateByPrimaryKey(album);
	}
	
	@Override
	public HotPhotoVo getHotPhoto() throws EntityNotFoundException {
		AlbumExample example = new AlbumExample();
		int total = this.albumMapper.countByExample(example);
		if(total == 0) return null;
		Random r = new Random();
		int index = r.nextInt(total);
		example.clear();
		example.setOffset(index);
		example.setLimit(1);
		List<Album> albums = this.albumMapper.selectByExample(example);
		if(!albums.isEmpty()) {
			Album album = albums.get(0);
			HotPhotoVo vo = new HotPhotoVo();
			vo.setAlbumId(album.getId());
			vo.setCreatedTime(album.getCreatedTime());
			vo.setAccountId(album.getAccountId());
			Account account = this.accountService.getAccount(album.getAccountId());
			vo.setAccount(account.getUsername());
			Page<VPhoto> photos = this.photoService.pageList(album.getId(), 1, 1000);
			VPhoto photo = null;
			if(photos.getRows().size() > 1) {
				Random rr = new Random();
				int i = rr.nextInt(photos.getRows().size() - 1);
				photo = photos.getRows().get(i);
			}else if(photos.getRows().size() == 1) {
				photo = photos.getRows().get(0);
			}
			if(photo == null) {
				return null;
			}
			vo.setPhotoId(photo.getId());
			return vo;
		}
		return null;
	}

}
