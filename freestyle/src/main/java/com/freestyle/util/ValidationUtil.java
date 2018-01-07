package com.freestyle.util;

import org.springframework.validation.BindingResult;

import com.freestyle.domain.view.http.ApiResponse;
import com.freestyle.domain.view.http.ApiStatusCode;

/**
 * Pojo属性验证工具
 * @author Leo Lien
 * 2016年10月19日 上午1:37:43 创建
 */
public class ValidationUtil {

    /**
     * 验证属性是否存在错误
     * @author Leo Lien
     * 2016年10月19日 上午1:38:55 创建
     * @param bindingResult
     * @param resp
     * @return
     */
    public static boolean validate(BindingResult bindingResult, ApiResponse resp) {
        if(bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            resp.setCode(ApiStatusCode.ILLEGAL_ARGUMENT);
            resp.setMessage(message);
            return true;
        }
        return false;
    }
    
}
