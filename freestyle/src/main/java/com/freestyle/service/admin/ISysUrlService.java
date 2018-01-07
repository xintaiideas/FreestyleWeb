package com.freestyle.service.admin;

import com.freestyle.domain.entity.admin.SysUrl;
import com.freestyle.service.commons.Page;
import com.freestyle.service.exception.EntityNotFoundException;

/**
 * 系统URL资源管理服务接口
 * @author Leo Lien
 * 2016年10月19日 下午10:28:36 创建
 */
public interface ISysUrlService {
    
    /**
     * 创建URL资源
     * @author Leo Lien
     * 2016年10月20日 下午5:07:36 创建
     * @param url   URL
     * @param remark    备注
     */
    public void createUrl(String url, String remark);
    
    /**
     * 分页查询 条件 URL （可选）
     * @author Leo Lien
     * 2016年10月20日 下午5:08:03 创建
     * @param url   URL，为NULL即不限制
     * @return  分页记录
     */
    public Page<SysUrl> findPage(int pageIndex, int rows, String url);
    
    /**
     * 根据ID查找URL资源
     * @author Leo Lien
     * 2016年10月20日 下午5:08:58 创建
     * @param id    ID
     * @return  记录
     */
    public SysUrl select(long id);
    
    /**
     * 根据ID删除URL资源
     * @author Leo Lien
     * 2016年10月20日 下午5:09:47 创建
     * @param id    ID
     */
    public void delete(long id);
    
    /**
     * 更新URL资源
     * @author Leo Lien
     * 2016年10月20日 下午5:10:35 创建
     * @param id    ID
     * @param url   URL
     * @param remark    备注
     */
    public void update(long id, String url, String remark) throws EntityNotFoundException;

}
