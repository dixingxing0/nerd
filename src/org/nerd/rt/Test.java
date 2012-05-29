package org.nerd.rt;

import static java.lang.String.format;
import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;

public class Test {
	static User u = new User(1L,"zhangsan");
	
	static UserService us = new UserService();
	public static void main(String[] args) {
		out.println(format("the returnType of save is void : %s",us.save(u).isVoid()));
		
		Rt<Boolean> rtLogin =  us.login(u);
		out.println(format("the returnValue of login is : %s,the errorInfo is : %s", rtLogin.rv(), rtLogin.get()));
		
		Rt<List<User>> rtList = us.list();
		out.println(format("the returnValue of list is : %s", rtList.rv()));
		out.println(format("the additional info 'idArray' of list is : %s", Arrays.toString((Long[])rtList.get("idArray"))));
	}
}
