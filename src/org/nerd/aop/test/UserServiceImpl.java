package org.nerd.aop.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.nerd.rt.Rt;

public class UserServiceImpl implements UserService {
	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
	
	public Rt<Void> save(User u) {
		LOG.debug("user has been saved!");
		return Rt.v();
	}
	
	public Rt<Boolean> login(User u) {
		LOG.debug("user logon!");
		if(u.getId() == 1L) {
			return Rt.f().put("您输入的用户名不存在！");
		}
		return Rt.t();
	}
	
	public Rt<List<User>> list() {
		LOG.debug("list user!");
		List<User> uList = new ArrayList<User>();
		uList.add(new User(2L,"lisi"));
		uList.add(new User(3L,"wangwu"));
		return Rt.rt(uList).put("idArray", new Long[]{2L,3L});
	}

	public Rt<Void> ex() {
		LOG.debug("throw an exception");
		throw new RuntimeException("just throw an exception");
	}
	
	
}
