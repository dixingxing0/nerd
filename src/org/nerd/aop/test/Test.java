/**
 * Test.java 11:19:28 AM May 29, 2012
 *
 * Copyright(c) 2000-2012 HC360.COM, All Rights Reserved.
 */
package org.nerd.aop.test;

import java.lang.reflect.Proxy;

import org.nerd.rt.Rt;

/**
 * <p></p>
 * 
 * @author dixingxing	
 * @date May 29, 2012
 */
public class Test {
	private static User u = new User(1L, "张三");
	
	@SuppressWarnings("unchecked")
	public static <T> T getProxy() {
		UserService us = new UserServiceImpl();
		return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
				new Class<?>[]{UserService.class}, new SomeInvocationHandler(us));
	}
	
	public static void main(String[] args) {
		UserService us = getProxy();
		Rt<Boolean> rt = us.login(u);
		if(rt != null) {
			System.out.println(rt.rv());
		}
		try{
			us.ex();
		}catch (Exception e) {
			// do nothing
		}
		us.list();
		us.save(u);
	}
}
