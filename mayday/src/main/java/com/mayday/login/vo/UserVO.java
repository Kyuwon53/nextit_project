package com.mayday.login.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
public class UserVO implements Serializable{
	String userId;
	String userName;
	String userPass;
	String userRole;
	String recentLogin;
	String userCond;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getRecentLogin() {
		return recentLogin;
	}
	public void setRecentLogin(String recentLogin) {
		this.recentLogin = recentLogin;
	}
	public String getUserCond() {
		return userCond;
	}
	public void setUserCond(String userCond) {
		this.userCond = userCond;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
	
}
