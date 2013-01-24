package org.nerd.rt;

import java.util.ArrayList;
import java.util.List;

public class UserService {
	public Rt<Void> save(User u) {
		System.out.println("user has been saved!");
		return Rt.v();
	}
	
	public Rt<Boolean> login(User u) {
		System.out.println("user logon!");
		if(u.getId() == 1L) {
			return Rt.f().put("您输入的用户名不存在！");
		}
		return Rt.t();
	}
	
	public Rt<List<User>> list() {
		System.out.println("list user!");
		List<User> uList = new ArrayList<User>();
		uList.add(new User(2L,"lisi"));
		uList.add(new User(3L,"wangwu"));
		return Rt.rt(uList).put("idArray", new Long[]{2L,3L});
	}
}
