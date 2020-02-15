package com.pinyougou.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        if (null == context){
            context=applicationContext;
        }
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static Object getBeanByName(String name){
        return getContext().getBean(name);
    }

    public static<T> T getBeanByClazz(Class<T> clazz){
        return getContext().getBean(clazz);
    }

    public static <T> T getBeanByNameAndClazz(Class<T> clazz,String name){
        return getContext().getBean(name,clazz);
    }


}
