package com.freestyle.service.admin.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.freestyle.domain.view.sys.DataBaseType;
import com.freestyle.domain.view.sys.DataSourceConfig;
import com.freestyle.service.admin.IDataSourceService;

/**
 * Druid数据源管理服务实现
 * @author Leo Lien
 * 2016年10月18日 下午10:06:12 创建
 */
@Component
public class DruidDataSourceServiceImpl implements IDataSourceService {
    
    private static Logger logger = LoggerFactory.getLogger(DruidDataSourceServiceImpl.class);
    
    @Resource(name="dataSource")
    private DruidDataSource dataSource;

    @Override
    public void configure(DataSourceConfig config)
            throws IllegalArgumentException, IllegalStateException {
        if(this.getActiveCount() > 0) {
            throw new IllegalStateException("数据源配置失败！当前有活动的数据库连接.");
        }
        if(config == null) {
            throw new IllegalArgumentException("数据源配置失败！DataSourceConfig对象不能为null.");
        }
        if(config.getDbType() == null) {
            throw new IllegalArgumentException("数据源配置失败！未设置数据库类型.");
        }
        //  TODO 校验参数
        if(DataBaseType.MySQL.equals(DataBaseType.valueOf(config.getDbType()))) {
            dataSource.setUrl(String.format(JDBC_MYSQL_URL, config.getDbIp(),config.getDbPort(),config.getDbName()));
        }else {
            throw new IllegalStateException("数据源配置失败！暂不支持除MySQL外的数据库.");
        }
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());
        dataSource.setMaxActive(config.getMaxActive());
        try {
            dataSource.restart();
        } catch (SQLException e) {
            logger.error("DruidDataSource.restart()失败.",e);
            throw new IllegalStateException("数据源配置失败！数据源配置无法生效.");
        }
        //  TODO 写入配置文件
    }

    @Override
    public int getActiveCount() {
        return dataSource.getActiveCount();
    }

    @Override
    public int getPoolingCount() {
        return dataSource.getPoolingCount();
    }

    @Override
    public DataSourceConfig getConfig() {
        DataSourceConfig config = new DataSourceConfig();
        config.setUsername(dataSource.getUsername());
        config.setPassword(dataSource.getPassword());
        config.setMaxActive(dataSource.getMaxActive());
        return config;
    }

}
