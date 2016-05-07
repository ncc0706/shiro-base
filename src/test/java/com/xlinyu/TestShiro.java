package com.xlinyu;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestShiro {

	@Test
	public void testBase() {
		
		SecurityManager securityManager = new IniSecurityManagerFactory("classpath:shiro.ini").getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("n", "node");
		try {
			subject.login(token);
			PrincipalCollection ps = subject.getPrincipals();
			System.out.println(ps.asList().size());
			System.out.println(ps.asList());
			System.out.println(ps.getRealmNames());
			System.out.println(subject.getPrincipal().toString());
		} catch (UnknownAccountException e) {
			System.out.println("用户不存在");
		} catch (IncorrectCredentialsException e){
			System.out.println("用户密码错误");
		}
	}

}
