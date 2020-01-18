package com.l7.connecteam.service;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;

public interface UserService {
	public UserDto createUser(UserDto userDataObj) throws UIException, SQLException;
	public UserDto ifUserExists(UserDto userDataObj) throws UIException, SQLException;
	public UserDto userLogin(UserDto userDataObj) throws UIException, SQLException;
	public List<UserDto> getAllUsers() throws UIException, SQLException;
}
