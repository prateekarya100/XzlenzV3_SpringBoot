package com.cybercube.xzlenzv3.model.User;

import java.io.Serializable;
import java.util.Set;

public enum UserProfileType implements Serializable{

	CLIENT_CEO("CLIENT_CEO"),
	CLIENT_ADMIN("CLIENT_ADMIN"),
	CLIENT_PROJECT_MANAGER("CLIENT_PROJECT_MANAGER"),
	CLIENT_USER("CLIENT_USER"),
	CLIENT_AUDITOR("CLIENT_AUDITOR"),
	CLIENT_USER_READ_ONLY("CLIENT_USER_READ_ONLY"),
	CLIENT_AUDITOR_READ_ONLY("CLIENT_AUDITOR_READ_ONLY"),


<<<<<<< HEAD
	SUPER("SUPER_ADMIN"),
	SUPER_USER("SUPER_USER");
=======
	SUPER("SUPER"),
	SUPER_USER("SUPER_USER")
	;
>>>>>>> eefcff17c2301b814b811c6e70abcafa77d77b84

	String userProfileType;

	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType(){
		return userProfileType;
	}

}
