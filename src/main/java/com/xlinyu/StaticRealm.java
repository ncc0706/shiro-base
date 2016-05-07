package com.xlinyu;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class StaticRealm extends AuthorizingRealm {

	/**
	 * 用于判断授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		
		info.addRole("r1");
		info.addRole("r2");
		
		info.addStringPermission("+user+*");
		info.addObjectPermission(new CustomPermission("+topic+create"));
		info.addObjectPermission(new CustomPermission("+topic+del+1"));
		
		info.addObjectPermission(new WildcardPermission("test:*"));
		
		return info;
	}

	/**
	 * 用于判断认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		String username = token.getPrincipal().toString();
		String password = new String((char[])token.getCredentials());
		System.out.println(username + "-->" + password);
		
		if(!"admin".equals(username)) throw new UnknownAccountException("用户不存在");
		if(!"admin".equals(password)) throw new IncorrectCredentialsException("用户密码错误");
		
		return new SimpleAuthenticationInfo("ncc@gmail.com", password, getName());
	}

}
