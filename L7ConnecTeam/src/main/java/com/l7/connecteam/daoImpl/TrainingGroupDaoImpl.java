package com.l7.connecteam.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.l7.connecteam.dao.TrainingGroupDao;
import com.l7.connecteam.dto.RoleDto;
import com.l7.connecteam.dto.TrainingGroupDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.DBDownException;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.manager.ConnectionManager;
import com.l7.connecteam.utility.QueryManager;

/**
 * @author soumya.raj
 * This class implements the DAO class for training group
 */
public class TrainingGroupDaoImpl implements TrainingGroupDao {
	Logger logger = Logger.getLogger(TrainingGroupDaoImpl.class.getName());

	/**
	 * Checks if a training group exists in DB or not
	 * @param trainDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public TrainingGroupDto ifTrainingGroupExists(TrainingGroupDto trainDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = connectionManager.getConnection();
			final String sql = QueryManager.getQuery("ifTrainingGroupExists");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, trainDataObj.getTrainGroupName());
			rs = stmt.executeQuery();
			if (rs.next()) {
				trainDataObj.setTrainGroupID(rs.getInt("training_group_id"));
				trainDataObj.setTrainGroupName(rs.getString("training_group_name"));
				trainDataObj.setTrainStartDate(rs.getDate("start_date"));
				trainDataObj.setTrainEndDate(rs.getDate("end_date"));
				trainDataObj.setCoursePlanPath(rs.getString("course_plan_path"));
				trainDataObj.setTypeID(rs.getInt("type_id"));
				trainDataObj.setActiveStatus(rs.getInt("active_status"));
			} else {
				return trainDataObj;
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
					throw new UIException("Connection temporarily unavailable");
				}
			}
		}
		return trainDataObj;
	}

	/**
	 * Writes a training group's data to DB if not already exists
	 * @param trainDataObj
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public TrainingGroupDto createTrainingGroup(TrainingGroupDto trainDataObj, UserDto userDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int trainTypeID = 0;
		int ifCreated = 0;
		final String coursePlan = "Plan path details";
		
		try {
			con = connectionManager.getConnection();
			final String sql = QueryManager.getQuery("createTrainingGrp");
			trainTypeID = getTrainTypeID();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, trainDataObj.getTrainGroupName());
			stmt.setDate(2, trainDataObj.getTrainStartDate());
			stmt.setDate(3, trainDataObj.getTrainEndDate());
			stmt.setString(4, coursePlan);
			stmt.setInt(5, trainTypeID);
			ifCreated = stmt.executeUpdate();
			if (ifCreated == 1) {
				logger.info("Training group " + trainDataObj.getTrainGroupName() + "created");
			} else {
				logger.info("Failed to create training group" + trainDataObj.getTrainGroupName());
			}
			trainDataObj = ifTrainingGroupExists(trainDataObj);
			Boolean isRelSet = setUserTrainingRel(userDataObj, trainDataObj.getTrainGroupID());
			if (isRelSet == true) {
				logger.info("User training group relation created");
			} else {
				logger.info("User training group relation creation failed");
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
					throw new UIException("Connection temporarily unavailable");
				}
			}
		}
		return trainDataObj;
	}

	/**
	 * Sets relation between user and respective training group in DB
	 * @param userDataObj
	 * @param trainID
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public boolean setUserTrainingRel(UserDto userDataObj, int trainID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt=null;
		int ifCreated = 0;
		int roleID = 0;
		final int statusID=1;
		
		try {
			con = connectionManager.getConnection();
			final String sql = QueryManager.getQuery("setUserTrainingGrpRel");
			stmt = con.prepareStatement(sql);
			List<RoleDto> roleList = userDataObj.getRoleList();
			for (RoleDto roleDto : roleList) {
				roleID = roleDto.getRoleId();
			}
			boolean ifRelExists = ifUserTrainRelExists(userDataObj.getUserId(), trainID);
			if (ifRelExists == false) {
				stmt.setInt(1, trainID);
				stmt.setInt(2, userDataObj.getUserId());
				stmt.setInt(3, roleID);
				stmt.setInt(4, statusID);
				ifCreated = stmt.executeUpdate();
			}
			else {
				logger.info("UserDto training group relation already exists");
			}
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
		boolean setSuccess = ifCreated == 1 ? true : false;
		return setSuccess;
	}

	/**
	 * Checks if user training group relation exists in DB or not
	 * @param userID
	 * @param trainID
	 * @return
	 * @throws UIException
	 * @throws SQLException 
	 */
	public boolean ifUserTrainRelExists(int userID, int trainID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		boolean ifRelExists = false;
		
		try {
			con = connectionManager.getConnection();
			final String sql = QueryManager.getQuery("ifUserTrainRelExists");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, trainID);
			stmt.setInt(2, userID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				ifRelExists = true;
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
			throw new UIException("Something went wrong. Try again");
		} catch (DBDownException e) {
			logger.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable");
		} finally {
			if(rs!=null) {
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
		return ifRelExists;
	}

	/**
	 * Returns the training type ID for a training
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public int getTrainTypeID() throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int trainTypeID = 0;
		
		try {
			con = connectionManager.getConnection();
			final String sql = QueryManager.getQuery("getTrainTypeID");
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				trainTypeID = rs.getInt("type_id");
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
					throw new UIException("Connection temporarily unavailable");
				}
			}
			
		}
		return trainTypeID;
	}
}
