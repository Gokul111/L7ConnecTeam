package com.l7.connecteam.serviceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.l7.connecteam.daoImpl.UserDaoImpl;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.service.UserService;

/**
 * @author soumya.raj
 * Implements business logic for user related operations
 */
public class UserServiceImpl implements UserService{
	Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
	private UserDaoImpl userImplObj=new UserDaoImpl();
	
	/**
	 * Logical implementations involved in user creation
	 * @param userDataObj
	 * @return
	 * @throws SQLException 
	 * @throws UIException 
	 */
	public UserDto createUser(UserDto userDataObj) throws UIException, SQLException {
		UserDto userData=userImplObj.createUser(userDataObj);
		return userData;
	}
	
	/**
	 * Logical implementations involved in determining if a user already exists
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public UserDto ifUserExists(UserDto userDataObj) throws UIException, SQLException {
		userDataObj=userImplObj.isUserExists(userDataObj);
		if (userDataObj.getUserId() != 0) {
			logger.info("User with username " + userDataObj.getUsername() + " already exists");
			return userDataObj;
		} else {
			userDataObj = createUser(userDataObj);
			return userDataObj;
		}
	}
	
	/** 
	 * Logical implementations involved in user login
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public UserDto userLogin(UserDto userDataObj) throws UIException, SQLException   {
		userImplObj.userLogin(userDataObj);
		return userDataObj;
	}
	
	/**
	 * Logical implementations involved in returning details of a user
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<UserDto> getAllUsers() throws UIException, SQLException {
		List<UserDto> userList=userImplObj.getAllUsers();
		return userList;
	}

}
