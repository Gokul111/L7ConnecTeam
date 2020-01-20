package com.l7.connecteam.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

import com.l7.connecteam.dao.AssessmentDao;
import com.l7.connecteam.dto.AssessmentDto;
import com.l7.connecteam.dto.AssessmentTypeDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.DBDownException;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.manager.ConnectionManager;
import com.l7.connecteam.utility.QueryManager;

/**
 * This class implements the DAO class for assessment
 * @author soumya.raj 
 */
public class AssessmentDaoImpl implements AssessmentDao {
	Logger logger = Logger.getLogger(AssessmentDaoImpl.class.getName());

	/**
	 * Checks if an assessment exists in DB or not
	 * @param assessDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public AssessmentDto ifAssessmentExists(AssessmentDto assessDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		PreparedStatement stmt = null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("ifAssessExists");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, assessDataObj.getAssessmentName());
			rs = stmt.executeQuery();
			if (rs.next()) {
				assessDataObj.setAssessmentID(rs.getInt("assessment_id"));
				assessDataObj.setAssessmentName(rs.getString("assessment_name"));
				assessDataObj.setCreationDate(rs.getDate("creation_date"));
				assessDataObj.setExpiryDate(rs.getDate("expiry_date"));
				assessDataObj.setCreatedyBy(rs.getInt("created_by"));
				assessDataObj.setAssessmentTypeID(rs.getInt("assessment_type_id"));
				assessDataObj.setActiveStatus(rs.getInt("active_status"));
			} else {
				return assessDataObj;
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
			if (rs != null) {
				rs.close();
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
		return assessDataObj;
	}

	/**
	 * Writes an assessment's data to DB if not already exists
	 * 
	 * @param assessDataObj
	 * @param userDataObj
	 * @param assessTypeObj
	 * @param marks
	 * @param status
	 * @param maxMarks
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public AssessmentDto createAssessment(AssessmentDto assessDataObj, UserDto userDataObj, AssessmentTypeDto assessTypeObj,
			int marks, String status, int maxMarks) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		final int trainerID = 27;
		PreparedStatement stmt = null;
		Connection con = null;
		int ifCreated = 0;
		int assessTypeID = 0;
		
		try {
			con = connectionManager.getConnection();
			assessTypeID = assessTypeObj.getAssessmentTypeID();
			final String sql = new QueryManager().getQuery("createAssessment");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, assessDataObj.getAssessmentName());
			stmt.setString(2, "2019-12-12");
			stmt.setInt(3, trainerID);
			stmt.setInt(4, assessTypeID);
			ifCreated = stmt.executeUpdate();
			if (ifCreated == 1) {
				logger.info("Assessment with assessment name" + assessDataObj.getAssessmentName() + "created");
			} else {
				logger.info("Failed to create assessment with assessment name" + assessDataObj.getAssessmentName());
			}
			assessDataObj = ifAssessmentExists(assessDataObj);
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
		return assessDataObj;
	}

	/**
	 * Sets relation between user and respective assessment in DB
	 * 
	 * @param assessID
	 * @param userID
	 * @param marks
	 * @param status
	 * @param maxMarks
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 * 
	 */
	public boolean setAssessUserRel(int assessID, int userID, int marks, String status, int maxMarks)
			throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int isRelSet = 0;
		int statusID = 0;
		int grpID = 0;
		
		try {
			con = connectionManager.getConnection();
			grpID = getTrainingGrpIDByAssessID(assessID);
			if (status.equalsIgnoreCase("pass")) {
				statusID = 1;
			} else if (status.equalsIgnoreCase("fail")) {
				statusID = 0;
			}
			float percentMark = (float) marks / maxMarks;
			float percentage = percentMark * 100;
			boolean ifExists = ifUserAssessRelExists(userID, assessID);

			if (ifExists == false && marks < maxMarks) {
				final String sql = new QueryManager().getQuery("createUserAssessRel");
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, grpID);
				stmt.setInt(2, userID);
				stmt.setInt(3, marks);
				stmt.setInt(4, statusID);
				stmt.setFloat(5, percentage);
				isRelSet = stmt.executeUpdate();
			} else {
				logger.info("User assessment relation already exists");
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
			if (rs != null) {
				rs.close();
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
		boolean setSuccess = isRelSet == 1 ? true : false;
		return setSuccess;
	}

	/**
	 * Checks if user assessment relation exists in DB or not
	 * 
	 * @param userID
	 * @param assessID
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public boolean ifUserAssessRelExists(int userID, int assessID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean ifExists = false;
		int grpID = 0;
		
		try {
			con = connectionManager.getConnection();
			grpID = getTrainingGrpIDByAssessID(assessID);
			final String sql = new QueryManager().getQuery("ifUserAssessRelExists");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.setInt(2, grpID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				ifExists = true;
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
			throw new UIException("Something went wrong");
		} catch (DBDownException e) {
			logger.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable");
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
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
		return ifExists;
	}

	/**
	 * Returns corresponding training groupID for an assessment
	 * 
	 * @param assessID
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public int getTrainingGrpIDByAssessID(int assessID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int grpID = 0;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("getTrainingGrpIDByAssessID");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, assessID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				grpID = rs.getInt("grp_technology_id");
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
			if (rs != null) {
				rs.close();
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
		return grpID;
	}
}
