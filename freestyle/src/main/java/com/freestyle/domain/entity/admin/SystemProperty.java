package com.freestyle.domain.entity.admin;

import com.freestyle.domain.entity.IdEntity;

/**
 * 系统属性实体
 * @author Leo Lien
 * 2016年10月19日 下午8:26:14 创建
 */
public class SystemProperty extends IdEntity {

    /*系统属性名称*/
    private String name;
    
    /*系统属性值*/
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
