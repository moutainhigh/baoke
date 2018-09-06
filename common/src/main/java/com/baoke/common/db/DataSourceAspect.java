package com.baoke.common.db;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 动态切换数据源切面
 * 
 * @author zjm
 * @Date: 2018年6月1日 下午1:43:53
 */
@Component
@Aspect
@Order(1)
public class DataSourceAspect {

	@Pointcut("execution(* com.baoke.*..*ManagerImpl.*(..))")
	public void pointCut() {

	}

	@Before("pointCut()")
	public void doBefore(JoinPoint joinPoint) {

		// 拦截的实体类

		Class<?> target = joinPoint.getTarget().getClass();
		// 拦截的方法名称
		// String methodName = joinPoint.getSignature().getName();
		// Class<?>[] classes = target.getClass().getInterfaces();
		// 拦截的放参数类型
		// Class<?>[] parameterTypes = ((MethodSignature)
		// joinPoint.getSignature()).getMethod().getParameterTypes();
		try {
			// Method m = classes[0].getMethod(methodName, parameterTypes);
			// if (m != null && m.isAnnotationPresent(DataSource.class)) {
			// DataSource data = m.getAnnotation(DataSource.class);
			// CustomerContextHolder.setCustomerType(data.value());
			// }
			if (target != null && target.isAnnotationPresent(DataSource.class)) {
				DataSource data = target.getAnnotation(DataSource.class);
				CustomerContextHolder.setCustomerType(data.value());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doAfterReturning(JoinPoint joinPoint) {
		CustomerContextHolder.clearCustomerType();
	}

}
