package com.freestyle.util;

import java.util.List;

/**
 * 字节处理工具
 * @author Leo Lien
 * 2016年10月19日 下午3:35:39 创建
 */
public class ByteUtil {

    /**
     * 将字节或字节数组转换为16进制字符串（小写）
     * @author Leo Lien
     * 2016年10月19日 下午3:37:36 创建
     * @param arr
     * @return
     */
    public static String byte2Hex(byte... arr) {
        //  用来将字节转换成 16 进制表示的字符
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        // 每个字节用 16 进制表示的话，使用两个字符
        char str[] = new char[16 * 2]; 
        // 表示转换结果中对应的字符位置
        int k = 0; 
        // 从第一个字节开始，对每一个字节,转换成 16 进制字符的转换
        for (int i = 0; i < 16; i++) { 
            // 取第 i 个字节
            byte byte0 = arr[i]; 
            // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移
            str[k++] = hexDigits[byte0 >>> 4 & 0xf]; 
            // 取字节中低 4 位的数字转换
            str[k++] = hexDigits[byte0 & 0xf];
        }
        // 换后的结果转换为字符串
        return new String(str); 
    }
    
    /**
     * 字节数组转整型数组
     * @author Leo Lien
     * 2016年11月12日 上午3:26:05 创建
     * @param byteArr   字节数组
     * @return  整型数组
     */
    public static int[] byteArray2IntArray(byte[] byteArr) {
        if(byteArr == null) {
            return null;
        }
        int[] intArr = new int[byteArr.length];
        for(int i = 0 ; i < intArr.length ; i ++ ) {
            intArr[i] = byteArr[i];
        }
        return intArr;
    }
    
    /**
     * List<Byte>转byte[]
     * @author Leo Lien
     * 2016年11月12日 上午3:26:29 创建
     * @param list  字节列表
     * @return  字节数组
     */
    public static byte[] byteList2ByteArray(List<Byte> list) {
        if(list != null) {
            byte[] arr = new byte[list.size()];
            for(int i = 0 ; i < arr.length ; i ++ ) {
                arr[i] = list.get(i).byteValue();
            }
            return arr;
        }
        return null;
    }
}
