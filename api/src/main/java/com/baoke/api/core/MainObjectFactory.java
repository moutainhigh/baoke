package com.baoke.api.core;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 从 app context中,根据id获取bean实例
 *
 */
public class MainObjectFactory implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		MainObjectFactory.applicationContext = applicationContext;
	}

	/**
	 * 从spring application context 获取bean实例
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getInstance(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static Map<String, Object> getInstancesWithAnnotation(Class<? extends Annotation> annotationType) {
		return applicationContext.getBeansWithAnnotation(annotationType);
	}

}
