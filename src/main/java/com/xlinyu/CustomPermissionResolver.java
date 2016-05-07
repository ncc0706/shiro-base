package com.xlinyu;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class CustomPermissionResolver implements PermissionResolver {

	@Override
	public Permission resolvePermission(String permissionString) {
		
		if(permissionString.startsWith("+")){
			return new CustomPermission(permissionString);
		}
		
		return new WildcardPermission(permissionString);
	}

}
