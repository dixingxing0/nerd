package org.nerd.rt;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 封装返回值，可包含多个附加对象
 * </p>
 * <li>基本用法
 * 
 * <pre>
 * void （无返回值）: return Rt.v(); or return Rt.v(obj2);
 * SomeType : return Rt.rt(someObject);
 * boolean(true) : return Rt.t();
 * boolean(false) : return Rt.f();
 * </pre> <li>支持链式访问
 * 
 * <pre>
 * return Rt.v().put("userName","dxx").set("this is an object")
 * </pre>
 * 
 * @author djr
 * 
 * @param <T>
 */
public class Rt<T> {
	/**
	 * {@link #put(Object)} using this key
	 */
	private static final String DEFAULT_KEY = "$_return_type_additional_key";
	
	private boolean isVoid = false;
	
	/**
	 * 方法的返回值 return value
	 */
	public T rv;

	/**
	 * 返回多个附加对象时可使用put
	 * 
	 * @see #put(Object, Object)
	 */
	public Map<Object, Object> map;

	private Rt(T returnValue) {
		this.rv = returnValue;
		this.map = new HashMap<Object, Object>();
	}
	
	private Rt() {
		this.isVoid = true;
	}

	/**
	 * 得到Rt实例。(无返回值)
	 * @return new Rt<Void>(null);
	 */
	public static Rt<Void> v() {
		return new Rt<Void>();
	}

	/**
	 * 得到Rt实例(无返回值)，并设置附加对象obj。
	 * @see #v()
	 * @see #put(Object)
	 * @param obj
	 * @return v().put(obj);
	 */
	public static Rt<Void> v(Object obj) {
		return v().put(obj);
	}
	
	/**
	 * 得到Rt实例。（此处使用泛型，避免声明泛型，代码会比new简洁一点。）
	 * @param <R>
	 * @param returnValue
	 * @return
	 */
	public static <R> Rt<R> rt(R returnValue) {
		return new Rt<R>(returnValue);
	}

	/**
	 * 
	 * @return new Rt<Boolean>(true)
	 */
	public static Rt<Boolean> t() {
		return new Rt<Boolean>(true);
	}

	/**
	 * new Rt<Boolean>(false)
	 * @return
	 */
	public static Rt<Boolean> f() {
		return new Rt<Boolean>(false);
	}
	
	/**
	 * 重新设置返回值
	 * @param rv
	 * @return
	 */
	public Rt<T> rv(T rv) {
		if(isVoid) {
			throw new IllegalStateException("当前返回值类型为void，不能设置返回值。");
		}
		this.rv = rv;
		return this;
	}

	/**
	 * 使用 {@link #DEFAULT_KEY} 为key。<font color="red">重复调用此方法会抛出异常</font>。 <li>如果返回多个附加对象请使用
	 * {@link #put(Object, Object)}
	 * 
	 * @see #put(Object, Object)
	 * @param obj
	 * @return
	 */
	public Rt<T> put(Object obj) {
		if (map.containsKey(DEFAULT_KEY)) {
			throw new IllegalStateException("put(Object)，不允许重复调用，请使用put(Object,Object)");
		}
		return put(DEFAULT_KEY, obj);
	}

	
	/**
	 * 增加一个附加对象，保存在hashMap中
	 * @param key
	 * @param v
	 * @return
	 */
	public Rt<T> put(Object key, Object v) {
		map.put(key, v);
		return this;
	}

	// /////////////////////
	
	/**
	 * 判断是否无返回值
	 * @see #v()
	 * @see #v(Object)
	 */
	public boolean isVoid() {
		return isVoid;
	}
	
	/**
	 * 获取返回值(return value)
	 */
	public T rv() {
		return this.rv;
	}

	/**
	 * 获取附加对象，若想得到方法的返回值请使用 {@link #rv()}
	 * 
	 * @param <R>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <R> R get() {
		return (R) map.get(DEFAULT_KEY);
	}

	/**
	 * 从map中获取附加对象
	 * 
	 * @param <R>
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <R> R get(Object key) {
		return (R) map.get(key);
	}

	/**
	 * 获得保存附加对象的 {@link #map}
	 * 
	 * @return
	 */
	public Map<Object, Object> getMap() {
		return map;
	}

}
