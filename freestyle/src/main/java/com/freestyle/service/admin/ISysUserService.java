package com.freestyle.service.admin;

/**
 * 用户管理服务
 * @author Leo Lien
 * 2016年10月7日 下午8:29:48 创建
 */
public interface ISysUserService {

    /**
     * 系统用户登录
     * @author Leo Lien
     * 2016年10月19日 下午10:10:25 创建
     * @param username	用户名
     * @param password	密码
     */
    public void login(String username, String password);
    
    /**
     * 用户退出
     * @author Leo Lien
     * 2016年10月19日 下午10:10:47 创建
     * @param username	用户名
     */
    public void logout(String username);
    
    /**
     * 修改用户密码
     * @author Leo Lien
     * 2016年10月19日 下午10:15:27 创建
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    public void modifyPassword(String username, String oldPassword, String newPassword);
    
    
    
}
