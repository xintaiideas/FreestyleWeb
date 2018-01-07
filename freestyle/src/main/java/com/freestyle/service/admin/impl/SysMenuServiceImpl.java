package com.freestyle.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freestyle.domain.entity.admin.SysMenu;
import com.freestyle.mapper.admin.SysMenuMapper;
import com.freestyle.service.admin.ISysMenuService;

@Service
@Transactional(readOnly=true)
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    
    @Transactional(readOnly=false)
    public void insertMenu(String name) {
        SysMenu record = new SysMenu();
        record.setSort(1);
        record.setEnable(true);
        record.setName(name);
        sysMenuMapper.insert(record);
       /* if(true) {
            throw new IllegalArgumentException();
        }*/
        record = new SysMenu();
        record.setSort(1);
        record.setEnable(true);
        record.setName(name+"---");
        sysMenuMapper.insert(record);
    }
}
