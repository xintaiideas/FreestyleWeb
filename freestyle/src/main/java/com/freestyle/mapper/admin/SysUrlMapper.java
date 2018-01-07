package com.freestyle.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.freestyle.domain.entity.admin.SysUrl;

public interface SysUrlMapper {
    
    /**
     * 根据URL条件进行分页查询
     * @author Leo Lien
     * 2016年10月25日 下午10:07:11 创建
     * @param url   url地址
     * @param index 起始位置
     * @param count 数量
     * @return
     */
    List<SysUrl> pageSelectByUrl(@Param("url") String url, @Param("index") int index, @Param("count") int count);
    
    /**
     * 获取本连接最近一次SQL_CALC_FOUND_ROWS查询的总条数
     * @author Leo Lien
     * 2016年10月25日 下午10:05:39 创建
     * @return
     */
    int selectFoundRows();
    
    /**
     * 插入URL资源
     * @author Leo Lien
     * 2016年10月25日 下午7:18:26 创建
     * @param url
     * @param remark
     */
    void insert(@Param("url") String url, @Param("remark") String remark);
    
    /**
     * 根据ID查找系统URL资源
     * @author Leo Lien
     * 2016年10月25日 下午7:07:06 创建
     * @param id
     * @return  系统URL资源对象
     */
    SysUrl select(@Param("id") long id);
    
    /**
     * 根据ID删除系统URL资源
     * @author Leo Lien
     * 2016年10月25日 下午7:07:18 创建
     * @param id
     * @return   影响条数
     */
    int delete(@Param("id") long id);
    
    /**
     * 更新URL资源
     * @author Leo Lien
     * 2016年10月25日 下午7:07:30 创建
     * @param id    ID
     * @param url   URL地址
     * @param remark    备注
     * @return  影响条数
     */
    int update(@Param("id") Long id, @Param("url") String url, @Param("remark") String remark);
    
}