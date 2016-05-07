package com.xlinyu;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestEncoding {

	@Test
	public void md5Hash() {
		System.out.println(new Md5Hash("w", "u001").toHex());
	}

	@Test
	public void testPassword() {

		DefaultPasswordService passwordService = new DefaultPasswordService();

		/*
		 * for(int i=0;i<10;i++){ String password =
		 * passwordService.encryptPassword("123"); System.out.println(password);
		 * }
		 */

		String password = passwordService.encryptPassword("123");

		boolean passwordsMatch = passwordService.passwordsMatch("123", password);

		System.out.println(passwordsMatch);

	}

	private Subject login(String username, String password) {

		SecurityManager securityManager = new IniSecurityManagerFactory(
				"classpath:shiro.ini").getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		try {
			subject.login(token);
			return subject;
		} catch (UnknownAccountException e) {
			System.out.println("用户不存在");
		} catch (IncorrectCredentialsException e) {
			System.out.println("用户密码错误");
		}
		return null;
	}

	@Test
	public void passworMatcher(){
		login("wandou", "w");
	}
	
}
