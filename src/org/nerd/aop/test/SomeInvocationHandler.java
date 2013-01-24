/*
 * $Id: MmtBusinessInvocationHandler.java,v 1.5 2008/12/02 23:30:42 weiw Exp $
 * Copyright(c) 2000-2007 HC360.COM, All Rights Reserved.
 */
package org.nerd.aop.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.nerd.aop.Interceptor;
import org.nerd.aop.InterceptorChain;

/**
 * 
 * <p>
 * jdk 动态代理类
 * </p>
 * 
 * @author dixingxing
 * @date May 29, 2012
 */
public class SomeInvocationHandler implements InvocationHandler {
	private Object target;

	/**
	 * 
	 * @param target
	 *            被代理的对象
	 */
	public SomeInvocationHandler(Object target) {
		this.target = target;
	}

	public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
		List<Interceptor> ics = new ArrayList<Interceptor>();
		ics.add(new SomeInterceptor1());
		ics.add(new SomeInterceptor2());
		InterceptorChain chain = InterceptorChain.doA(target, method, ics, args);
		try{
			chain.doChain();
		}catch (Throwable t) {
			throw t;
		}
		return chain.getReturnValue();
	}
}
