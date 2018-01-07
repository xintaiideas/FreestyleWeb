package com.freestyle.util;

import java.io.InputStream;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;

public class XmlUtil {

    /**
     * 从xml字符串中解析出对象
     * @author Leo Lien
     * 2016年12月4日 上午11:29:35 创建
     * @param xml
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T parse(String xml, Class<T> clazz) {
        XStream xs = new XStream();
        T t = (T) xs.fromXML(xml);
        return t;
    }
    
    /**
     * 从xml输入流中解析出对象
     * @author Leo Lien
     * 2016年12月4日 上午11:30:50 创建
     * @param in
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T parse(InputStream in) {
        XStream xs = new XStream();
        T t = (T) xs.fromXML(in);
        return t;
    }
    
    /**
     * 对象转换成xml字符串
     * @author Leo Lien
     * 2016年12月4日 上午11:33:41 创建
     * @param obj
     * @return
     */
    public static String toXml(Object obj) {
        XStream xs = new XStream();
        return xs.toXML(xs);
    }
    
    /**
     * 对象转换成xml流并输出
     * @author Leo Lien
     * 2016年12月4日 上午11:35:47 创建
     * @param obj
     * @param out
     */
    public static void toXml(Object obj, OutputStream out) {
        XStream xs = new XStream();
        xs.processAnnotations(obj.getClass());
        xs.toXML(obj, out);
    }
}
