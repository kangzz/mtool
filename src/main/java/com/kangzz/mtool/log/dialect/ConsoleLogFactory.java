package com.kangzz.mtool.log.dialect;


import com.kangzz.mtool.log.Log;
import com.kangzz.mtool.log.LogFactory;

/**
 * 利用System.out.println()打印日志
 * @author Looly
 *
 */
public class ConsoleLogFactory extends LogFactory {
	
	public ConsoleLogFactory() {
		super("Hutool Console Logging");
	}

	@Override
	public Log getLog(String name) {
		return new ConsoleLog(name);
	}

	@Override
	public Log getLog(Class<?> clazz) {
		return new ConsoleLog(clazz);
	}

}
