package com.freestyle.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 摘要工具
 * 包括MD5和SHA-1摘要算法
 * @author Leo Lien
 * 2016年10月18日 上午1:23:33 创建
 */
public class DigestUtil {
    
    public static final String ALGORITHM_MD5 = "MD5";
    public static final String ALGORITHM_SHA1 = "SHA-1";
    

    /**
     * MD5摘要
     * @author Leo Lien
     * 2016年10月19日 下午3:38:53 创建
     * @param src
     * @return
     */
    public static String md5(String src) {
        try {
            byte[] encryptStr = digest(src.getBytes(), ALGORITHM_MD5);
           return ByteUtil.byte2Hex(encryptStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    /**
     * SHA-1摘要
     * @author Leo Lien
     * 2016年10月19日 下午3:41:17 创建
     * @param src
     * @return
     */
    public static String sha1(String src) {
        try {
            byte[] encryptStr = digest(src.getBytes(), ALGORITHM_SHA1);
           return ByteUtil.byte2Hex(encryptStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    /**
     * 按指定算法进行摘要
     * @author Leo Lien
     * 2016年10月19日 下午3:56:38 创建
     * @param src
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] digest(byte[] src, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest mdInst = MessageDigest.getInstance(algorithm);
        // 通过使用 update 方法处理数据,使指定的 byte数组更新摘要
        mdInst.update(src);
        //  获得密文完成哈希计算,产生128 位的长整数
        byte[] encryptStr = mdInst.digest();
       return encryptStr;
    }
    
}
