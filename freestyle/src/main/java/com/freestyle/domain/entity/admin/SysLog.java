package com.freestyle.domain.entity.admin;

import com.freestyle.domain.entity.IdEntity;

/**
 * 系统日志
 * @author Leo Lien
 * 2016年10月19日 下午9:33:51 创建
 */
public class SysLog extends IdEntity {

    /*访问客户端IP*/
    private String ip;
    
    /*访问客户端端口*/
    private Integer port;
    
    /*备注*/
    private String remark;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}
