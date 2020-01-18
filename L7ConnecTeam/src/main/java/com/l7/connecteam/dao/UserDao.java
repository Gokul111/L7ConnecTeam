package com.l7.connecteam.dao;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.dto.RoleDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;

/**
 * @author soumya.raj
 * This class acts as DAO to user DTO
 */
public interface UserDao {
	public UserDto isUserExists(UserDto userDataObj) throws UIException, SQLException;
	public UserDto createUser(UserDto userDataObj) throws UIException, SQLException;
	public boolean setUserRoleRel(int userID) throws UIException, SQLException;
	public List<RoleDto> getRoleByUser(UserDto userDataObj) throws SQLException, UIException;
	public UserDto userLogin(UserDto userDataObj) throws UIException, SQLException;
	public List<UserDto> getAllUsers() throws UIException, SQLException;

}
