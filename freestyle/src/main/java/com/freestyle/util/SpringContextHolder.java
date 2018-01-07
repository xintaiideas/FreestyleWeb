package com.freestyle.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Spring ApplicationContext封装，包括如下功能
 * 1.可通过类型或beanName直接获取bean对象而无需注解
 * @author Leo Lien
 * 2016年10月18日 上午1:38:06 创建
 */
public class SpringContextHolder implements ApplicationContextAware {
    
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        applicationContext = context;
    }

    /**
     * 通过beanName获取bean对象
     * @author Leo Lien
     * 2016年10月18日 上午1:34:49 创建
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
    
    /**
     * Spring容器内发布事件
     * @author Leo Lien
     * 2016年12月4日 下午2:09:34 创建
     * @param event
     */
    public static void publishEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }
    
    /**
     * Spring容器内发布事件
     * @author Leo Lien
     * 2016年12月4日 下午2:10:16 创建
     * @param object
     */
    public static void publishEvent(Object object) {
        applicationContext.publishEvent(object);
    }
    
    /**
     * 通过类型获取bean对象
     * @author Leo Lien
     * 2016年10月18日 上午1:35:31 创建
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
    
    /**
     * 刷新Spring ApplicationContext
     * @author Leo Lien
     * 2016年10月18日 上午2:33:27 创建
     */
    public static void refresh() {
        if(applicationContext instanceof AbstractApplicationContext) {
            ((AbstractApplicationContext) applicationContext).refresh();
        }
    }
}
