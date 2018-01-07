package com.freestyle.util;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * JSON处理工具统一接口
 * 内部使用FastJson实现
 * @author Leo Lien
 * 2016年10月7日 下午8:18:55 创建
 */
public class JsonUtil {

    /**
     * 将对象转换为Json字符串。
     * @author Leo Lien
     * 2016年10月7日 下午8:14:35 创建
     * @param obj   对象
     * @return  json字符串
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }
    
    /**
     * 将Json字符串转换为指定类型的对象
     * @author Leo Lien
     * 2016年10月7日 下午8:14:56 创建
     * @param json  json字符串
     * @param clazz 对象类型
     * @return  指定类型的对象
     */
    public static <T> T parse(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }
    
    /**
     * 将Json字符串转换为指定类型的列表
     * @author Leo Lien
     * 2016年11月12日 上午3:12:44 创建
     * @param json  json字符串
     * @param clazz 对象类型
     * @return  指定类型的对象列表
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }
    
}
