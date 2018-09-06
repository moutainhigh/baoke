package com.baoke.seller.listener;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SystemConfigInitListener implements ServletContextListener {

	private static Log log = LogFactory.getLog(SystemConfigInitListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String prefix = sce.getServletContext().getRealPath("/");
		String file = sce.getServletContext().getInitParameter("systemConfigLocationOverride");
		if (null != file) {
			file = file.replace(" ", "");
			file = file.replace("\n", "");
			file = file.replace("\r", "");
			file = file.replace("\t", "");

			String[] files = file.split(",");
			for (String fileStr : files) {

				System.out.println("init config file, file=" + fileStr);
				log.info("init config file, file=" + fileStr);

				Properties properties = new Properties();
				InputStream inputStream = null;
				try {
					if (fileStr.startsWith("file:")) {
						String userHome = System.getProperty("user.home").replace("\\", "\\\\");
						String filePath = fileStr.substring(5).replaceAll("\\$\\{user.home\\}", userHome);
						inputStream = new FileInputStream(filePath);
					} else {
						String filePath = prefix + fileStr;
						inputStream = new FileInputStream(filePath);
					}
					properties.load(inputStream);
					for (Enumeration<Object> it = properties.keys(); it.hasMoreElements();) {
						String key = (String) it.nextElement();
						System.setProperty(key, properties.getProperty(key));
						sce.getServletContext().setAttribute(key, properties.getProperty(key));
					}
				} catch (Exception e) {
					log.error("init config file error, file=" + fileStr, e);
				} finally {
					if (inputStream != null) {
						try {
							inputStream.close();
						} catch (Exception e) {
							log.error("init config file error, close file error, file=" + fileStr, e);
						}
					}
				}
			}
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
