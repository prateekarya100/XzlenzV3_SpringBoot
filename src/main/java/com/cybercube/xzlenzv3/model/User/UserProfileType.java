package com.cybercube.xzlenzv3.model.User;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	
	CLIENT_CEO("CLIENT_CEO"),
	CLIENT_ADMIN("CLIENT_ADMIN"),
	CLIENT_PROJECT_MANAGER("CLIENT_PROJECT_MANAGER"),
	CLIENT_USER("CLIENT_USER"),
	CLIENT_AUDITOR("CLIENT_AUDITOR"),
	CLIENT_USER_READ_ONLY("CLIENT_USER_READ_ONLY"),
	CLIENT_AUDITOR_READ_ONLY("CLIENT_AUDITOR_READ_ONLY"),
	
	
	SUPER("SUPER"),
	SUPER_USER("SUPER_USER")
	;
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
