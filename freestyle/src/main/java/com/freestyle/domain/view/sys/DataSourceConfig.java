package com.freestyle.domain.view.sys;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 数据源配置对象
 * @author Leo Lien
 * 2016年10月18日 下午9:50:25 创建
 */
public class DataSourceConfig {

    /*数据库类型*/
    @NotBlank(message="{DataSourceConfig.validation.dbType}")
    private String dbType;
    
    /*数据库IP地址*/
    @NotBlank(message="{DataSourceConfig.validation.dbIp}")
    private String dbIp;
    
    /*数据库端口*/
    @Min(value=1,message="{DataSourceConfig.validation.dbPort}")
    @Max(value=65535,message="{DataSourceConfig.validation.dbPort}")
    private int dbPort;
    
    /*数据库名称*/
    private String dbName;
    
    /*数据库账号*/
    private String username;
    
    /*数据库密码*/
    private String password;
    
    /*最大激活数量*/
    private int maxActive;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbIp() {
        return dbIp;
    }

    public void setDbIp(String dbIp) {
        this.dbIp = dbIp;
    }

    public int getDbPort() {
        return dbPort;
    }

    public void setDbPort(int dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }
    
}
