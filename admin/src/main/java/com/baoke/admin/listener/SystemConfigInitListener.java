package com.baoke.admin.listener;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SystemConfigInitListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		String prefix = sce.getServletContext().getRealPath("/");
		String file = sce.getServletContext().getInitParameter("systemConfigLocation");
		Properties props = new Properties();
		InputStream is = null;
		try {
			if (file.startsWith("classpath:")) {
				is = SystemConfigInitListener.class.getClassLoader().getResourceAsStream(file.substring(10));
			} else {
				String filePath = prefix + file;
				is = new FileInputStream(filePath);
			}
			props.load(is);
			for (Enumeration<Object> it = props.keys(); it.hasMoreElements();) {
				String key = (String) it.nextElement();
				System.setProperty(key, props.getProperty(key));
				sce.getServletContext().setAttribute(key, props.getProperty(key));
			}
		} catch (Exception e) {
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
				}
			}
		}

		file = sce.getServletContext().getInitParameter("systemConfigLocationOverride");
		if (null != file) {

			file = file.replace(" ", "");
			file = file.replace("\n", "");
			file = file.replace("\r", "");
			file = file.replace("\t", "");

			String[] files = file.split(",");
			for (String fileStr : files) {
				Properties props2 = new Properties();
				InputStream is2 = null;
				try {
					if (fileStr.startsWith("file:")) {
						String userHome = System.getProperty("user.home").replace("\\", "\\\\");
						String filePath = fileStr.substring(5).replaceAll("\\$\\{user.home\\}", userHome);
						is2 = new FileInputStream(filePath);
					} else {
						String filePath = prefix + fileStr;
						is2 = new FileInputStream(filePath);
					}
					props2.load(is2);
					for (Enumeration<Object> it = props2.keys(); it.hasMoreElements();) {
						String key = (String) it.nextElement();
						System.setProperty(key, props2.getProperty(key));
						sce.getServletContext().setAttribute(key, props2.getProperty(key));
					}
				} catch (Exception e) {
				} finally {
					if (is != null) {
						try {
							is2.close();
						} catch (Exception e) {
						}
					}
				}
			}
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
