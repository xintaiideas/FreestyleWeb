package com.freestyle.domain.view.http;

/**
 * 响应状态码定义
 * @author Leo Lien
 * 2016年10月18日 下午11:18:38 创建
 */
public enum ApiStatusCode {
    OK,                                     //      成功
    NOT_FOUND,                      //      未找到
    UNAUTHORIZED,                //      未登录
    PERMISSION_DENIED,         //      没有权限
    ILLEGAL_ARGUMENT,          //      请求参数错误
    FAILED;                               //      失败
}
