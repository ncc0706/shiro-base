package com.xlinyu;

import java.util.Arrays;

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

	
	private Subject login(String username, String password){
		
		SecurityManager securityManager = new IniSecurityManagerFactory("classpath:shiro.ini").getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			return subject;
		} catch (UnknownAccountException e) {
			System.out.println("用户不存在");
		} catch (IncorrectCredentialsException e){
			System.out.println("用户密码错误");
		}
		return null;
	}
	
	@Test
	public void testRealem() {
		Subject subject = login("n", "node");
		PrincipalCollection ps = subject.getPrincipals();
		System.out.println(ps.asList().size());
		System.out.println(ps.asList());
		System.out.println(ps.getRealmNames());
		System.out.println(subject.getPrincipal().toString());
	}

	@Test
	public void testRole(){
		Subject subject = login("n", "node");
		boolean hasRole = subject.hasRole("r1");
		System.out.println(hasRole);
		System.out.println(subject.hasAllRoles(Arrays.asList("r1", "r2", "r3")));
		System.out.println(subject.hasRoles(Arrays.asList("r1", "r3"))[1]);
		try {
			subject.checkRoles("r4");
		} catch (Exception e) {
			System.out.println("r4 not have");
		}
	}
	
	@Test
	public void testPermission(){
		Subject subject = login("n", "node");
		
		System.out.println(subject.isPermitted("user:del"));
		System.out.println(subject.isPermitted("topic:asdfa"));
		System.out.println(subject.isPermitted("dep:view"));
		
	}
	
	@Test
	public void testPermission2(){
		Subject subject = login("lisi", "123");
		System.out.println("细粒度控制："+subject.isPermitted("admin:user:del:100"));
		System.out.println("细粒度控制："+subject.isPermitted("admin:user:view:100"));
		System.out.println("细粒度控制："+subject.isPermitted("user:view:100"));
		
	}
	
	@Test
	public void testCustomPermission(){
		Subject subject = login("admin", "admin");
//		System.out.println(subject.isPermitted("+user+abc"));
		System.out.println(subject.isPermitted("+topic+del+3"));
//		System.out.println(subject.isPermitted("test:del"));
		//System.out.println(subject.isPermitted("classroom:add"));
	}
	
}
