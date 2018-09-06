package com.baoke.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在json处理时跳过对应字段
 * 
 * @author wyh
 * @date: 2018年6月15日 下午6:41:50
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface IgnoreJson {

}
