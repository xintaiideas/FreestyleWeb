package com.freestyle.util;

import java.util.UUID;

/**
 * UUID生成工具类
 * @author Leo Lien
 * 2016年10月18日 上午1:20:06 创建
 */
public class UuidUtil {

    /**
     * 获取32位UUID字符串
     * @author Leo Lien
     * 2016年10月18日 下午11:38:04 创建
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().trim().replace("-", "");
    }

}
