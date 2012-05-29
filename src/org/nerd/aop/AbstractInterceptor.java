/**
 * AbstractInterceptor.java 2:31:49 PM May 29, 2012
 *
 * Copyright(c) 2000-2012 HC360.COM, All Rights Reserved.
 */
package org.nerd.aop;

import java.lang.reflect.Method;

/**
 * <p>拦截器的基类，子类可重写以下方法进行扩展：</p>
 * <li> {@link #before(Object, Method, Object[])}
 * <li> {@link #after(long, Object, Object, Method, Object[])}
 * <li> {@link #error(Throwable, Object, Method, Object[])}
 * 
 * @author dixingxing	
 * @date May 29, 2012
 */
public class AbstractInterceptor implements Interceptor{
	/**
	 * 
	 * 执行拦截
	 * 
	 * @param chain
	 * @throws Throwable
	 * @see com.hc360.mmt.business.aop.Interceptor#intercept(com.hc360.mmt.business.aop.InterceptorChain)
	 */
	public void intercept(InterceptorChain chain) throws Throwable {
		try {
			if (before(chain.getTarget(), chain.getMethod(), chain.getArgs())) {
				chain.doChain();
			}
		} catch (Throwable e) {
			if (error(e, chain.getTarget(), chain.getMethod(), chain.getArgs())) {
				throw e;
			}
		} finally {
			Object obj = after(0L,chain.getReturnValue(),chain.getTarget(),chain.getMethod(),chain.getArgs());
			chain.setReturnValue(obj);
		}
		
	}
	
	/**
	 * 
	 * <p>在目标方法前执行</p>
	 * <li>返回true表示继续执行，返回false时中断(目标方法返回值为null，慎用)
	 *
	 * @param target 最终执行目标方法的对象
	 * @param m
	 * @param args
	 * @return
	 */
	protected boolean before(Object target,Method m,Object[] args) {
		return true;
	}
	
	/**
	 * <p>在目标方法后执行</p>
	 * <li>可以修改目标方法的返回值
	 *
	 * @param beginTime 目标方法开始执行时间
	 * @param result 目标方法返回值
	 * @param target 最终执行目标方法的对象
	 * @param m
	 * @param args
	 * @return
	 */
	protected Object after(long beginTime,Object result,Object target, Method m,Object[] args) {
		return result;
	}
	
	/**
	 * 
	 * <p>目标方法出现异常时执行</p>
	 * <li> 返回true时抛出异常，返回false忽略异常（慎用）
	 *
	 * @param t 目标方法或其他拦截器抛出的异常
	 * @param target 最终执行目标方法的对象
	 * @param m
	 * @param args
	 * @return
	 */
	protected boolean error(Throwable t,Object target,Method m,Object[] args) {
		return true;
	}
}
