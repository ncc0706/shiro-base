package com.xlinyu;

import org.apache.shiro.authz.Permission;

public class CustomPermission implements Permission {

	private String resourceId;
	
	private String operator;
	
	private String instanceId;
	
	public CustomPermission() {
		
	}
	
	public CustomPermission(String permissionStr) {
		String[] strs = permissionStr.split("\\+");
		if(strs.length>1) {
			this.setResourceId(strs[1]);
		}
		if(this.getResourceId()==null||"".equals(this.getResourceId())) {
			this.setResourceId("*");
		}
		
		if(strs.length>2) {
			this.setOperator(strs[2]);
		}
		
		if(strs.length>3) {
			this.setInstanceId(strs[3]);
		}
		
		if(this.getInstanceId()==null||"".equals(this.getInstanceId())) {
			this.setInstanceId("*");
		}
	}

	@Override
	public boolean implies(Permission p) {
		
		if(!(p instanceof CustomPermission)) return false;
		
		CustomPermission cPermission = (CustomPermission)p;
		if(!this.getResourceId().equals("*") && !cPermission.getResourceId().equals(resourceId))
			return false;
		if(!this.getOperator().equals("*") && !cPermission.getOperator().equals(operator))
			return false;
		if(!this.getInstanceId().equals("*") && !cPermission.getInstanceId().equals(instanceId))
			return false;
		return true;
		
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	@Override
	public String toString() {
		return "CustomPermission [resourceId=" + resourceId + ", operator="
				+ operator + ", instanceId=" + instanceId + "]";
	}
	
}
