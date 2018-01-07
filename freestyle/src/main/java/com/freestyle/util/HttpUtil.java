package com.freestyle.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * Http客户端请求工具
 * @author Leo Lien
 * 2016年12月4日 下午2:46:29 创建
 */
public class HttpUtil {

    public HttpProtocol post(String uri, HttpProtocol hpRequest) {
        try {
            HttpClient httpClient = null;
            HttpPost post = new HttpPost(uri);
            HttpResponse response = httpClient.execute(post);

            HttpProtocol  hpResponse = new HttpProtocol();
            return hpResponse;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void get() {
        
    }
    
    public void head() {
        
    }
    
    public void track() {
        
    }
    
    public void put() {
        
    }
}
