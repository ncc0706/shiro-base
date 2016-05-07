package com.xlinyu;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MapRealm implements Realm {

	private static Map<String, String> users;
	
	static{
		users = new HashMap<String, String>();
		users.put("n", "node");
		users.put("zhangsan", "node");
		users.put("admin", "admin");
	}
	
	@Override
	public String getName() {
		return "mapRealm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		
		String username = token.getPrincipal().toString();
		String password = new String((char[])token.getCredentials());
		System.out.println(username + "-->" + password);
		
		if(!users.containsKey(username)) throw new UnknownAccountException("用户不存在");
		if(!users.get(username).equals(password)) throw new IncorrectCredentialsException("用户密码错误");
		
		AuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
		
		return info;
	}

}
