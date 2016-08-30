package com.kangzz.mtool.log.dialect;


import com.kangzz.mtool.log.Log;
import com.kangzz.mtool.log.LogFactory;

/**
 *  Apache Commons Logging
 * @author Looly
 *
 */
public class ApacheCommonsLogFactory extends LogFactory {
	
	public ApacheCommonsLogFactory() {
		super("Apache Common Logging");
	}

	@Override
	public Log getLog(String name) {
		return new ApacheCommonsLog(name);
	}

	@Override
	public Log getLog(Class<?> clazz) {
		return new ApacheCommonsLog(clazz);
	}

}
