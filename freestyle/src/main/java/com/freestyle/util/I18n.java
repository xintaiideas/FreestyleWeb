package com.freestyle.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.support.RequestContext;
import com.google.common.base.Strings;

/**
 * 国际化辅助工具类
 * 包括文字的国际化处理方法、国际化的文字常量及格式化
 * @author Leo Lien
 * 2016年9月16日 下午5:01:10 创建
 */
public class I18n {
    
    public static final String LANGUAGE_CHINESE = "zh-cn";
    public static final String LANGUAGE_ENGLISH = "en";
    
    /**
     * 根据language参数进行国际化
     * 在推送中使用
     * @author Leo Lien
     * 2016年9月16日 下午5:03:17 创建
     * @param key   国际化文字的键名（国际化文字常量）
     * @param language  语言（en/zh-cn），空或空字符串默认为zh-cn
     * @param params    文本格式化处理的填充参数
     * @return  国际化结果字符串
     */
	public static String getMessage(String key, String language, Object ... params) {
	    if(Strings.isNullOrEmpty(key)) throw new IllegalArgumentException("国际化文字键名不能为空");
	    if(language == null) language = "";
		Locale locale = new Locale(language);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/messages",locale);
        if (resourceBundle.containsKey(key)) {
            String pattern = resourceBundle.getString(key);
            return MessageFormat.format(pattern, params);
        }
        return key;
    }
	
	/**
	 * 根据HttpRequest进行国际化
	 * 在http请求响应中使用
	 * @author Leo Lien
	 * 2016年9月16日 下午5:05:35 创建
	 * @param request  请求
	 * @param key 国际化文字的键名（国际化文字常量）
	 * @param params   文本格式化处理的填充参数
	 * @return  国际化结果字符串
	 */
	public static String getMessage(HttpServletRequest request, String key, Object ... params) {
	    if(request == null)  throw new IllegalArgumentException("HttpRequest对象不能为空");
	    if(Strings.isNullOrEmpty(key)) throw new IllegalArgumentException("国际化文字键名不能为空");
		RequestContext context = new RequestContext(request);
		String pattern = context.getMessage(key);
		return MessageFormat.format(pattern, params);
	}

}
