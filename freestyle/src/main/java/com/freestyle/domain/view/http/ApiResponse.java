package com.freestyle.domain.view.http;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP响应基础实体
 * @author Leo Lien
 * 2016年10月18日 下午11:15:19 创建
 */
public class ApiResponse {
    
    public static final ApiResponse OK = new ApiResponse(ApiStatusCode.OK, "成功");
    
    public static final ApiResponse NOT_FOUND = new ApiResponse(ApiStatusCode.NOT_FOUND, "资源不存在");
    
    public static final ApiResponse UNAUTHORIZED = new ApiResponse(ApiStatusCode.UNAUTHORIZED, "未登录");
    
    public static final ApiResponse PERMISSION_DENIED = new ApiResponse(ApiStatusCode.PERMISSION_DENIED, "没有权限");
    
    public static final ApiResponse ILLEGAL_ARGUMENT = new ApiResponse(ApiStatusCode.ILLEGAL_ARGUMENT, "请求参数错误");
    
    public static final ApiResponse FAILED = new ApiResponse(ApiStatusCode.FAILED, "失败");
    
    /*响应码*/
    private String code;
    
    /*响应消息*/
    private String message;
    
    private Map<String,Object> attributes;
    
    public ApiResponse() {
        super();
    }

    public ApiResponse(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
    
    public ApiResponse(ApiStatusCode code, String message) {
        super();
        this.code = code.toString();
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public void setCode(ApiStatusCode apiStatusCode) {
        this.code = apiStatusCode.toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	public ApiResponse putAttribute(String key, Object value) {
		if(this.attributes == null) {
			this.attributes = new HashMap<String,Object>();
		}
		this.attributes.put(key, value);
		return this;
	}

	/*快速构建响应对象*/
    public static ApiResponse buildOK(String message) {
        return new ApiResponse(ApiStatusCode.OK, message);
    }
    
    public static ApiResponse buildFailed(String message) {
        return new ApiResponse(ApiStatusCode.FAILED, message);
    }
    
    public static ApiResponse buildNotFound(String message) {
        return new ApiResponse(ApiStatusCode.NOT_FOUND, message);
    }
    
    public static ApiResponse buildUnauthorized(String message) {
        return new ApiResponse(ApiStatusCode.UNAUTHORIZED, message);
    }
    
    public static ApiResponse buildPermissionDenied(String message) {
        return new ApiResponse(ApiStatusCode.PERMISSION_DENIED, message);
    }
    
    public static ApiResponse buildIllegalArgument(String message) {
        return new ApiResponse(ApiStatusCode.ILLEGAL_ARGUMENT, message);
    }
}
