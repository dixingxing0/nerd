/**
 * Interceptor.java 10:15:14 AM May 29, 2012
 *
 * Copyright(c) 2000-2012 HC360.COM, All Rights Reserved.
 */
package org.nerd.aop;


/**
 * 
 * <p>拦截器接口</p>
 * <li>自定义拦截器时建议继承 {@link AbstractInterceptor}
 * 
 * @see AbstractInterceptor
 * @author dixingxing	
 * @date May 29, 2012
 */
public interface Interceptor {
	void intercept(InterceptorChain chain) throws Throwable;
}