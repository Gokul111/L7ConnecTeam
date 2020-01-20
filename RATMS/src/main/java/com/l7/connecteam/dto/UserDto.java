package com.l7.connecteam.dto;

import java.sql.Date;
import java.util.List;

public class UserDto {
	private int userId;
	private String employeeId;
	private String username;
	private String password;
	private int activeStatus;
	private Date creationDate;
	private List<RoleDto> roleList;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int active_status) {
		this.activeStatus = active_status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creation_date) {
		this.creationDate = creation_date;
	}

	public List<RoleDto> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleDto> roleList) {
		this.roleList = roleList;
	}
	
	@Override
	public String toString() {
		return "user [userId=" + userId + ", employeeId=" + employeeId + ", username=" + username + ", password="
				+ password + ", activeStatus=" + activeStatus + ", creationDate=" + creationDate + ", roleList="
				+ roleList + "]";
	}
}
