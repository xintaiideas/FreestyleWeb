package com.freestyle.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freestyle.domain.entity.admin.SysUrl;
import com.freestyle.mapper.admin.SysUrlMapper;
import com.freestyle.service.admin.ISysUrlService;
import com.freestyle.service.commons.Page;
import com.freestyle.service.exception.EntityNotFoundException;

@Service
@Transactional(readOnly=true)
public class SysUrlServiceImpl implements ISysUrlService {
    
    @Autowired
    private SysUrlMapper sysUrlMapper;

    @Override
    @Transactional(readOnly=false)
    public void createUrl(String url, String remark) {
        sysUrlMapper.insert(url, remark);
    }

    @Override
    public Page<SysUrl> findPage(int pageIndex, int rows, String url) {
        if(pageIndex < 1) pageIndex = 1;
        if(rows < 1) rows = 1;
        List<SysUrl> content = sysUrlMapper.pageSelectByUrl(url, (pageIndex - 1) * rows, rows);
        int total = sysUrlMapper.selectFoundRows();
        Page<SysUrl> page = new Page<SysUrl>();
        page.setRows(content);
        page.setTotal(total);
        page.setPageNumber(pageIndex);
        page.setTotalPageNum((int)Math.ceil(total * 1.0 / rows));
        return page;
    }

    @Override
    public SysUrl select(long id) {
        return sysUrlMapper.select(id);
    }

    @Override
    @Transactional(readOnly=false)
    public void delete(long id) {
        //  影响条数可以忽略处理，用户删除不存在的记录没有必要再次提醒
        sysUrlMapper.delete(id);
    }

    @Override
    @Transactional(readOnly=false)
    public void update(long id, String url, String remark) throws EntityNotFoundException {
        int effectNum = sysUrlMapper.update(id, url, remark);
        if(effectNum == 0) throw new EntityNotFoundException("记录不存在. ID:" + id, SysUrl.class);
    }

}
