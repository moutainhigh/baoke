package com.baoke.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheData {
	
	/**
	 * 缓存过期时间间隔，一个单位为一秒，默认为0；
	 * @see CacheAspect
	 * @return
	 */
    int interval() default 0;
    
    String namespace() default "";
    
    /**
     * TODO 目前的本地缓存方式在CacheNamespace中，而不是用这个注解符号
     * @return
     */
    @Deprecated
    boolean localCache() default false;
}
