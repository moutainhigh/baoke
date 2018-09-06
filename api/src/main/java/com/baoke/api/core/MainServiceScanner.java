package com.baoke.api.core;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.baoke.common.annotation.MethodDefinition;
import com.baoke.common.annotation.ServiceDefinition;

/**
 * spring初始化完成后,将扫描指定目录下的service类
 *
 */
public class MainServiceScanner implements ApplicationListener<ContextRefreshedEvent> {

	private static final Log log = LogFactory.getLog(MainServiceScanner.class);

	private final ConcurrentHashMap<String, ObjectAndMethod> methods = new ConcurrentHashMap<String, ObjectAndMethod>();

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// if (event.getApplicationContext().getParent() != null) {
		// return;
		// }
		Map<String, Object> instances = MainObjectFactory.getInstancesWithAnnotation(ServiceDefinition.class);
		if (instances == null || instances.size() == 0) {
			log.info("[Scanner]service is null");
			return;
		}
		for (Map.Entry<String, Object> entry : instances.entrySet()) {
			Class<?> clazz = entry.getValue().getClass();
			ServiceDefinition service = clazz.getAnnotation(ServiceDefinition.class);
			for (Method method : clazz.getMethods()) {

				MethodDefinition methodDefinition = method.getAnnotation(MethodDefinition.class);
				if (methodDefinition != null) {

					log.info("[ScannerMethod]service=" + clazz.getName() + ", method=" + method.getName() + ", optype="
							+ methodDefinition);

					ObjectAndMethod oam = new ObjectAndMethod();
					oam.setMethod(methodDefinition.value());
					oam.setTargetMethod(method);
					oam.setBeanObject(MainObjectFactory.getInstance(service.value()));
					oam.setNeedLogin(methodDefinition.needLogin());
					oam.setNeedCheckVersion(methodDefinition.needCheckVersion());
					oam.setNeedIp(methodDefinition.needIp());
					methods.put(oam.getMethod(), oam);
				}
			}
		}
	}

	/**
	 * 根据操作类型,返回对应的service实例及method信息
	 * 
	 * @param method
	 * @return
	 */
	public ObjectAndMethod getServiceByMethodName(String method) {
		return methods.get(method);
	}

	public class ObjectAndMethod {
		private String method;
		private Method targetMethod;
		private Object beanObject;
		private boolean needLogin;
		private boolean needIp;
		private boolean needCookie;
		private boolean needLogVisit;
		private boolean needCheckVersion;

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		public Method getTargetMethod() {
			return targetMethod;
		}

		public void setTargetMethod(Method targetMethod) {
			this.targetMethod = targetMethod;
		}

		public Object getBeanObject() {
			return beanObject;
		}

		public void setBeanObject(Object beanObject) {
			this.beanObject = beanObject;
		}

		public boolean isNeedLogin() {
			return needLogin;
		}

		public void setNeedLogin(boolean needLogin) {
			this.needLogin = needLogin;
		}

		public boolean isNeedIp() {
			return needIp;
		}

		public void setNeedIp(boolean needIp) {
			this.needIp = needIp;
		}

		public boolean isNeedCookie() {
			return needCookie;
		}

		public void setNeedCookie(boolean needCookie) {
			this.needCookie = needCookie;
		}

		public boolean isNeedLogVisit() {
			return needLogVisit;
		}

		public void setNeedLogVisit(boolean needLogVisit) {
			this.needLogVisit = needLogVisit;
		}

		public boolean isNeedCheckVersion() {
			return needCheckVersion;
		}

		public void setNeedCheckVersion(boolean needCheckVersion) {
			this.needCheckVersion = needCheckVersion;
		}

	}
}
