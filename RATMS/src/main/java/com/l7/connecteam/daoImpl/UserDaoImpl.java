package com.l7.connecteam.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

import com.l7.connecteam.dao.RoleDao;
import com.l7.connecteam.dao.UserDao;
import com.l7.connecteam.dto.FeatureDto;
import com.l7.connecteam.dto.RoleDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.DBDownException;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.manager.ConnectionManager;
import com.l7.connecteam.utility.QueryManager;

public class UserDaoImpl implements UserDao {
	Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

	UserDto userObj = new UserDto();
	RoleDao roleImplObj = new RoleDaoImpl();

	/**
	 * Checks if a user exists in DB or not
	 * 
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public UserDto isUserExists(UserDto userDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = connectionManager.getConnection();
			String sql = new QueryManager().getQuery("ifUserExists");
			sql="";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, userDataObj.getUsername());
			rs = stmt.executeQuery();
			if (rs.next()) {
				userDataObj.setUserId(rs.getInt("user_id"));
				userDataObj.setEmployeeId(rs.getString("employee_id"));
				userDataObj.setUsername(rs.getString("username"));
				userDataObj.setPassword(rs.getString("Password"));
				userDataObj.setActiveStatus(rs.getInt("active_status"));
				userDataObj.setCreationDate(rs.getDate("creation_date"));
				userDataObj.setRoleList(getRoleByUser(userDataObj));
			} else {
				return userDataObj;
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
			throw new UIException("Something went wrong. Try again",e);
		} catch (DBDownException e) {
			logger.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable", e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					logger.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable");
				}
			}
		}
		return userDataObj;
	}

	/**
	 * Writes user related data to DB
	 * 
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public UserDto createUser(UserDto userDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		int ifCreated = 0;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("createUser");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, userDataObj.getEmployeeId());
			stmt.setString(2, userDataObj.getUsername());
			ifCreated = stmt.executeUpdate();
			if (ifCreated == 1) {
				logger.info("User with user name " + userDataObj.getUsername() + " created");
			} else {
				logger.info("Failed to create user with user name" + userDataObj.getUsername());
			}
			userDataObj = isUserExists(userDataObj);
			Boolean ifRelSet = setUserRoleRel(userDataObj.getUserId());
			if (ifRelSet == true) {
				logger.info("User Role relation created");
			} else {
				logger.info("User Role relation creation failed");
			}
			userDataObj.setRoleList(getRoleByUser(userDataObj));
		} catch (SQLException e) {
			logger.info(e.getMessage());
			throw new UIException("Something went wrong. Try again");
		} catch (DBDownException e) {
			logger.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable");

		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					logger.info(e.getMessage());
					throw new UIException("Connection temporarily unvailable");
				}
			}
		}
		return userDataObj;
	}

	/**
	 * Sets user role relation to DB
	 * 
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public boolean setUserRoleRel(int userID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		int ifRelSet = 0;
		final int roleID = 4;
		final int statusID = 1;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("setUserRoleRel");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.setInt(2, roleID);
			stmt.setInt(3, statusID);
			ifRelSet = stmt.executeUpdate();
		} catch (SQLException e) {
			logger.info(e.getMessage());
			throw new UIException("Something went wrong. Try again");
		} catch (DBDownException e) {
			logger.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable");
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					logger.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable");
				}
			}
		}
		boolean setSuccess = ifRelSet == 1 ? true : false;
		return setSuccess;
	}

	/**
	 * Returns allocated roles for a particular user
	 * 
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<RoleDto> getRoleByUser(UserDto userDataObj) throws SQLException, UIException {
		ConnectionManager connectionManager = new ConnectionManager();
		List<RoleDto> roleList = new ArrayList<RoleDto>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("getRoleByUser");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userDataObj.getUserId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				RoleDto roleObj = new RoleDto();
				roleObj.setRoleId(rs.getInt("role_id"));
				roleObj.setRoleName(rs.getString("role_name"));
				roleObj.setActiveStatus(rs.getInt("active_status"));
				List<FeatureDto> featureList = roleImplObj.getFeatureByRole(roleObj.getRoleId());
				roleObj.setFeatureList(featureList);
				roleList.add(roleObj);
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
			throw new UIException("Something went wrong.Try again");
		} catch (DBDownException e) {
			logger.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable");
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					logger.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable");
				}
			}
		}
		return roleList;
	}

	/**
	 * Validates login credentials of a user
	 * 
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public UserDto userLogin(UserDto userDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		String Password = userDataObj.getPassword();
		UserDto dataObj = isUserExists(userDataObj);
		if (dataObj.getUserId() != 0) {
			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				con = connectionManager.getConnection();
				final String sql = new QueryManager().getQuery("getUserPassword");
				stmt = con.prepareStatement(sql);
				stmt.setString(1, userDataObj.getUsername());
				rs = stmt.executeQuery();
				if (rs.next() && (rs.getString("password")).equals(Password)) {
					return dataObj;
				} else {
					throw new UIException("Incorrect Password");
				}
			} catch (SQLException e) {
				logger.info(e.getMessage());
				throw new UIException("Something went wrong. Try again");
			} catch (DBDownException e) {
				logger.info(e.getMessage());
				throw new UIException("Connection temporarily unavailable");
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					try {
						connectionManager.closeConnection(con);
					} catch (DBDownException e) {
						logger.info(e.getMessage());
						throw new UIException("Connection temporarily unvailable");
					}
				}
			}
		} else {
			throw new UIException("UserDto does not exist");
		}
	}

	/**
	 * Returns data of all users in DB
	 * 
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<UserDto> getAllUsers() throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		List<UserDto> userList = new ArrayList<UserDto>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("getAllUsers");
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				UserDto userDataObj = new UserDto();
				userDataObj.setUserId(rs.getInt("user_id"));
				userDataObj.setUsername(rs.getString("username"));
				userDataObj.setEmployeeId(rs.getString("employee_id"));
				userDataObj.setPassword(rs.getString("password"));
				userDataObj.setActiveStatus(rs.getInt("active_status"));
				userDataObj.setCreationDate(rs.getDate("creation_date"));
				userList.add(userDataObj);
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
			throw new UIException("Something went wrong");
		} catch (DBDownException e) {
			logger.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable");
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					logger.info(e.getMessage());
					throw new UIException("Connection temporarily unvailable");
				}
			}
		}
		return userList;
	}
}