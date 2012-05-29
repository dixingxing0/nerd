/**
 * UserService.java 11:18:46 AM May 29, 2012
 *
 * Copyright(c) 2000-2012 HC360.COM, All Rights Reserved.
 */
package org.nerd.aop.test;

import java.util.List;

import org.nerd.rt.Rt;

/**
 * <p></p>
 * 
 * @author dixingxing	
 * @date May 29, 2012
 */
public interface UserService {
	public Rt<Void> save(User u);
	
	public Rt<Boolean> login(User u);
	
	public Rt<List<User>> list();
	
	public Rt<Void> ex();
}
