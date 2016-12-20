package com.kangzz.mtool.proxy.aspects;



import com.kangzz.mtool.date.TimeInterval;
import com.kangzz.mtool.log.Log;
import com.kangzz.mtool.log.LogFactory;

import java.lang.reflect.Method;

/**
 * 通过日志打印方法的执行时间的切面
 * @author Looly
 *
 */
public class TimeIntervalAspect extends SimpleAspect{
	private static final Log log = LogFactory.get();

	public TimeIntervalAspect(Object target) {
		super(target);
	}
	
	private TimeInterval interval = new TimeInterval();

	@Override
	public boolean before(Object target, Method method, Object[] args) {
		interval.start();
		return true;
	}
	
	@Override
	public boolean after(Object target, Method method, Object[] args) {
		log.info("Method [{}.{}] execute spend [{}]ms", target.getClass().getName(), method.getName(), interval.intervalMs());
		return true;
	}
}
