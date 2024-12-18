package com.xzy.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author xzy.xiao
 * @since 2024/12/17  18:26
 */
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;

    public SpringContext() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static Object getBean(String beanId) {
        try {
            return context.getBean(beanId);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        try {
            return context.getBean(clazz);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T getBean(String beanId, Class<T> clazz) {
        try {
            return context.getBean(beanId, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getProperty(String key) {
        return context.getEnvironment().getProperty(key);
    }

    public static <T> T getProperty(String key, Class<T> clazz) {
        return context.getEnvironment().getProperty(key, clazz);
    }

    public static String getRequiredProperty(String key) {
        return context.getEnvironment().getRequiredProperty(key);
    }

    public static <T> T getRequiredProperty(String key, Class<T> clazz) {
        return context.getEnvironment().getRequiredProperty(key, clazz);
    }

    public static String[] getActiveProfiles() {
        return context.getEnvironment().getActiveProfiles();
    }
}
