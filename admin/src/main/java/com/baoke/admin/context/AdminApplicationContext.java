package com.baoke.admin.context;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component 
public class AdminApplicationContext implements ApplicationContextAware{

    protected static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext paramApplicationContext)
			throws BeansException {
       
		AdminApplicationContext.applicationContext = paramApplicationContext;
	
	}
    
    /**
     * 从spring application context 获取bean实例
     * @param beanName
     * @return
     */
    public static Object getInstance(String beanName){
        return applicationContext.getBean(beanName);
    }
    
    public static Map<String,Object> getInstancesWithAnnotation(Class<? extends Annotation> annotationType){
        return applicationContext.getBeansWithAnnotation(annotationType);
    }
}
