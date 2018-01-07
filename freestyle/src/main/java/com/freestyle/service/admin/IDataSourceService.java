package com.freestyle.service.admin;

import com.freestyle.domain.view.sys.DataSourceConfig;

/**
 * 连接池数据源管理服务
 * @author Leo Lien
 * 2016年10月18日 下午9:58:02 创建
 */
public interface IDataSourceService {
    
    /*jdbc url - mysql*/
    public static final String JDBC_MYSQL_URL = "jdbc:mysql://%s:%d/%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";

    /**
     * 重新配置数据源并立即生效
     * 如果当前有活动的数据库连接将配置失败
     * @author Leo Lien
     * 2016年10月18日 下午10:02:21 创建
     * @param config 配置参数对象
     * @throws IllegalArgumentException 配置参数错误
     * @throws IllegalStateException    当前数据连接状态不允许修改配置
     */
    public void configure(DataSourceConfig config) throws IllegalArgumentException,IllegalStateException;
    
    /**
     * 获取当前的数据源配置
     * @author Leo Lien
     * 2016年10月18日 下午10:38:55 创建
     * @return
     */
    public DataSourceConfig getConfig();
    
    /**
     * 获取活动的数据库连接
     * @author Leo Lien
     * 2016年10月18日 下午10:01:26 创建
     * @return
     */
    public int getActiveCount();
    
    /**
     * 获取池中连接数量
     * @author Leo Lien
     * 2016年10月18日 下午10:26:00 创建
     * @return
     */
    public int getPoolingCount();
}
