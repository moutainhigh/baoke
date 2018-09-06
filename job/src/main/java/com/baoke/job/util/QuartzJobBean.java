package com.baoke.job.util;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.context.ApplicationContext;

/**
 * 定时任务类
 *
 */
public class QuartzJobBean implements StatefulJob {
	
	private Log log = LogFactory.getLog(QuartzJobBean.class);
	
	public static final String TARGET_CLASS = "class";
	public static final String TARGET_METHOD = "method";
	public static final String TARGET_ARGUMENTS = "arguments";
	
	private static ApplicationContext ac;

	public static void setAc(ApplicationContext ac) {
		QuartzJobBean.ac = ac;
	}

	/**
	 * 通过类名和方法名去获取目标对象，再通过反射执行
	 * 类名和方法名保存在jobDetail中
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		String targetClass = (String)context.getMergedJobDataMap().get(TARGET_CLASS); 
		String targetMethod = (String)context.getMergedJobDataMap().get(TARGET_METHOD);  
		String methodArgs = (String)context.getMergedJobDataMap().get(TARGET_ARGUMENTS); 
		Object[] args = null;
        if(StringUtils.isEmpty(targetClass) || StringUtils.isEmpty(targetMethod))  
            return;  
        if (!StringUtils.isEmpty(methodArgs)) {
        	String[] argString = methodArgs.split("&&");
        	args = new Object[argString.length];
        	for (int i=0; i<argString.length; i++) {
        		args[i] = argString[i].trim();
        	}
        }
        long startTime = System.currentTimeMillis();
		try {
			Object target = ac.getBean(targetClass);
			 if (null != target) {
				 Class tc = target.getClass();
				 Class[] parameterType = null;
				 if (args != null) {
					 parameterType = new Class[args.length];
					 for (int i=0; i<args.length; i++) {
						 parameterType[i] = String.class;
					 }
				 }
				 Method method = tc.getDeclaredMethod(targetMethod, parameterType);
				 if (null != method) {
					 method.invoke(target, args);
				 }
			 }
		} catch (Exception e) {
			log.error(e.toString(), e);
			throw new JobExecutionException(e);
		}
		
		log.error("[QuartzJobBean]" + targetClass + ", " + targetMethod + ", " + methodArgs
                + ", used time " + (System.currentTimeMillis() - startTime));
          
	}
	
	

}
