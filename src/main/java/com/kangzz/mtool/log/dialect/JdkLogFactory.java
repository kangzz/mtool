package com.kangzz.mtool.log.dialect;

import com.kangzz.mtool.log.Log;
import com.kangzz.mtool.log.LogFactory;
import com.kangzz.mtool.util.IoUtil;

import java.io.InputStream;
import java.util.logging.LogManager;

/**
 *  <a href="http://java.sun.com/javase/6/docs/technotes/guides/logging/index.html">java.util.logging</a> log.
 * @author Looly
 *
 */
public class JdkLogFactory extends LogFactory {
	
	public JdkLogFactory() {
		super("JDK Logging");
		readConfig();
	}

	@Override
	public Log getLog(String name) {
		return new JdkLog(name);
	}

	@Override
	public Log getLog(Class<?> clazz) {
		return new JdkLog(clazz);
	}

	/**
	 * 读取ClassPath下的logging.properties配置文件
	 */
	private void readConfig() {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("logging.properties");
		if(null == in){
			System.err.println("[WARN] Can not find [logging.properties], use [%JRE_HOME%/lib/logging.properties] as default!");
			return;
		}
		
		try {
			LogManager.getLogManager().readConfiguration(in);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				LogManager.getLogManager().readConfiguration();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		} finally {
			IoUtil.close(in);
		}
	}
}
