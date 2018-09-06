package com.baoke.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MethodDefinition {

	String value() default "";

	/**
	 * 是否需要校验登陆
	 * 
	 * @return
	 */
	boolean needLogin() default false;

	/**
	 * 是否需要检查版本
	 * 
	 * @return
	 */
	boolean needCheckVersion() default false;

	/**
	 * 是否需要IP
	 * 
	 * @return
	 */
	boolean needIp() default false;

}
