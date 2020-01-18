package com.l7.connecteam.controller;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.serviceImpl.UserServiceImpl;

/**
 * Controls flow of user related data
 * @author soumya.raj
 */
public class UserController {
	UserServiceImpl userServiceObj = new UserServiceImpl();

	/**
	 * Routes data required to create a user
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public UserDto createUser(UserDto userDataObj) throws UIException, SQLException {
		userDataObj = userServiceObj.ifUserExists(userDataObj);
		return userDataObj;
	}

	/**
	 * Routes data required for user login
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public UserDto userLogin(UserDto userDataObj) throws UIException, SQLException  {
		userDataObj = userServiceObj.userLogin(userDataObj);
		return userDataObj;
	}
	
	/**
	 * Routes data required to get all user info
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<UserDto> getAllUsers() throws UIException, SQLException {
		List<UserDto> userList = userServiceObj.getAllUsers();
		return userList;
	}

}
