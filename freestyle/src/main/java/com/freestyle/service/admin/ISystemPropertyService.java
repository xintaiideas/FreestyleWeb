package com.freestyle.service.admin;

import java.util.Map;

/**
 * 系统配置服务
 * @author Leo Lien
 * 2016年10月10日 下午6:43:50 创建
 */
public interface ISystemPropertyService {
    
    /*以下为系统固定属性的属性名称常量*/
    /*系统应用名称*/
    public static final String APP_NAME = "APP_NAME";
    
    /*系统版本*/
    public static final String APP_VERSION = "APP_VERSION";
    
    /*版权信息*/
    public static final String COPY_RIGHT = "COPY_RIGHT";
    
    /*公司名称*/
    public static final String COMPANY_NAME = "COMPANY_NAME";
    
    /*系统应用名称默认值*/
    public static final String APP_NAME_DEFAULT = "拓科信息系统";
    
    /*系统版本默认值*/
    public static final String APP_VERSION_DEFAULT = "V1.0";
    
    /*版权信息默认值*/
    public static final String COPY_RIGHT_DEFAULT = "Copyright © 2013 - 2016 TopCode Inc. All Rights Reserved";
    
    /*公司名称默认值*/
    public static final String COMPANY_NAME_DEFAULT = "泉州拓科信息技术有限公司";
    
    /**
     * 设置应用名称
     * @author Leo Lien
     * 2016年10月10日 下午8:50:39 创建
     * @param appName   应用名称
     */
    public void setAppName(String appName);
    
    /**
     * 设置应用版本号
     * @author Leo Lien
     * 2016年10月10日 下午8:51:03 创建
     * @param appVersion    应用版本号
     */
    public void setAppVersion(String appVersion);
    
    /**
     * 设置版权信息
     * @author Leo Lien
     * 2016年10月10日 下午8:51:36 创建
     * @param copyRight 版权信息
     */
    public void setCopyRight(String copyRight);
    
    /**
     * 设置公司名称
     * @author Leo Lien
     * 2016年10月10日 下午8:52:02 创建
     * @param companyName   公司名称
     */
    public void setCompanyName(String companyName);
    
    /**
     * 获取应用名称
     * @author Leo Lien
     * 2016年10月10日 下午8:47:09 创建
     * @return  应用名称
     */
    public String getAppName();
    
    /**
     * 获取应用版本号
     * @author Leo Lien
     * 2016年10月10日 下午8:47:23 创建
     * @return 应用版本号
     */
    public String getAppVersion();
    
    /**
     * 获取版权信息
     * @author Leo Lien
     * 2016年10月10日 下午8:47:39 创建
     * @return  版权信息
     */
    public String getCopyRight();
    
    /**
     * 获取公司名称
     * @author Leo Lien
     * 2016年10月10日 下午8:47:52 创建
     * @return  公司名称
     */
    public String getCompanyName();

    /**
     * 设置系统配置属性
     * @author Leo Lien
     * 2016年10月19日 下午8:40:19 创建
     * @param oldName   原属性名称
     * @param newName   新属性名称
     * @param value     属性值
     */
    public void setProperty(String oldName, String newName, Object value);
    
    /**
     * 设置系统配置属性
     * @author Leo Lien
     * 2016年10月19日 下午8:44:46 创建
     * @param name  属性名称
     * @param value 属性值
     */
    public void setProperty(String name, String value);
    
    /**
     * 获取系统配置属性
     * @author Leo Lien
     * 2016年10月10日 下午8:05:57 创建
     * @param name  属性名称
     * @param clazz 值类型
     * @return  值对象
     */
    public <T> T getProperty(String name, Class<T> clazz, T defaultValue);
    
    /**
     * 删除系统属性
     * @author Leo Lien
     * 2016年10月19日 下午8:51:34 创建
     * @param name  属性名称
     */
    public void deleteProperty(String name);
    
    /**
     * 获取所有系统属性
     * @author Leo Lien
     * 2016年10月19日 下午8:57:02 创建
     * @return  
     */
    public Map<String,String> getProperties();
    
    /**
     * 是否存在属性
     * @author Leo Lien
     * 2016年10月19日 下午8:57:45 创建
     * @param name  属性名称
     * @return  是否存在
     */
    public boolean existProperty(String name);
    
}
