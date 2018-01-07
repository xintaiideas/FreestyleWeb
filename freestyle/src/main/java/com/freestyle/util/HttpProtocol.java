package com.freestyle.util;

import java.io.InputStream;
import java.util.Map;

/**
 * Http协议
 * @author Leo Lien
 * 2016年12月4日 下午2:54:16 创建
 */
public class HttpProtocol {

    /*http头部*/
    private Map<String,Object> headers;
    
    /*http内容*/
    private InputStream body;

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    public InputStream getBody() {
        return body;
    }

    public void setBody(InputStream body) {
        this.body = body;
    }
    
}
