package org.nerd.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * <p>拦截器</p>
 * 
 * @author dixingxing	
 * @date May 29, 2012
 */
public class InterceptorChain {
	protected Method method;

	protected Object args[];

	protected Object target;

	protected Object returnValue;

	protected Iterator<Interceptor> interceptors;

	private InterceptorChain(Object obj,
							Method method,
							List<Interceptor> interceptors,
							Object[] args) {
		this.target = obj;
		this.method = method;
		this.args = args;
		if(interceptors == null) {
			this.interceptors = new ArrayList<Interceptor>().iterator();
		} else {
			this.interceptors = interceptors.iterator();
		}
	}

	public static InterceptorChain doA(Object obj,Method method,List<Interceptor> interceptors,Object[] args) throws Throwable {
		InterceptorChain chain = new InterceptorChain(obj,method,interceptors,args);
		return chain.doChain();
	}
	/**
	 * 
	 * <p>顺序执行拦截器</p>
	 *
	 * @return
	 * @throws Throwable
	 */
	public InterceptorChain doChain() throws Throwable {
		if(interceptors.hasNext()) {
			interceptors.next().intercept(this);
		} else {
			this.returnValue = method.invoke(target, args);
		}
		return this;
	}

	public void setReturnValue(Object returnValue) {
		this.returnValue = returnValue;
	}

	public Object getReturnValue() {
		return returnValue;
	}

	public Object[] getArgs() {
		return args;
	}

	public Method getMethod() {
		return method;
	}

	public Object getTarget() {
		return target;
	}
}