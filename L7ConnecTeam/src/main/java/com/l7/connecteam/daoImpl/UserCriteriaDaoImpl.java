package com.l7.connecteam.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

import com.l7.connecteam.dao.UserCriteriaDao;
import com.l7.connecteam.dto.UserCriteriaDto;
import com.l7.connecteam.exception.DBDownException;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.manager.ConnectionManager;
import com.l7.connecteam.utility.QueryManager;

/**
 * @author soumya.raj
 * This class implements the DAO class for user criteria
 */
public class UserCriteriaDaoImpl implements UserCriteriaDao{
	Logger logger = Logger.getLogger(UserCriteriaDaoImpl.class.getName());

	/**
	 * Writes user criteria relation to DB if not already exists
	 * @param empID
	 * @param criteriaMap
	 * @param assess
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public UserCriteriaDto createUserCriteriaRel(String empID, Map<String, Integer> criteriaMap, String assess)
			throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		int assessID = 0;
		int grpID = 0;
		int criteriaID = 0;
		int userID = 0;
		int minMarks = 0;
		int status = 0;
		int ifCreated = 0;

		for (String key : criteriaMap.keySet()) {
			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				con = connectionManager.getConnection();
				final String sqlAssess = QueryManager.getQuery("getAssessByName");
				stmt = con.prepareStatement(sqlAssess);
				stmt.setString(1, assess);
				rs = stmt.executeQuery();
				if (rs.next()) {
					assessID = rs.getInt("assessment_id");
				}
				final String sqlTrain = QueryManager.getQuery("setTrainGrpTechRelByAssessID");
				stmt = con.prepareStatement(sqlTrain);
				stmt.setInt(1, assessID);
				rs = stmt.executeQuery();
				if (rs.next()) {
					grpID = rs.getInt("grp_technology_id");
				}
				final String sqlCriteria = QueryManager.getQuery("getCriteriaByGrpTechID");
				stmt = con.prepareStatement(sqlCriteria);
				stmt.setInt(1, grpID);
				stmt.setString(2, key);
				rs = stmt.executeQuery();
				if (rs.next()) {
					criteriaID = rs.getInt("criteria_id");
					minMarks = rs.getInt("criteria_minmarks");
				}
				final String sqlUser = QueryManager.getQuery("getUserByEmpID");
				stmt = con.prepareStatement(sqlUser);
				stmt.setString(1, empID);
				rs = stmt.executeQuery();
				if (rs.next()) {
					userID = rs.getInt("user_id");
				}
				if (criteriaMap.get(key) >= minMarks) {
					status = 1;
				} else {
					status = 0;
				}

				UserCriteriaDto userCriteria = ifUserCriteriaRelExists(userID, criteriaID);
				if (userCriteria.getCriteriaID() == 0) {
					final String sqlEval = QueryManager.getQuery("setUserEvaluationCriteria");
					stmt = con.prepareStatement(sqlEval);
					stmt.setInt(1, criteriaID);
					stmt.setInt(2, userID);
					stmt.setInt(3, criteriaMap.get(key));
					stmt.setInt(4, status);
					ifCreated = stmt.executeUpdate();
					if (ifCreated == 1) {
						logger.info("User criteria relation set");
					} else {
						logger.info("User criteria relation failed");
					}
				}
				else {
					logger.info("User criteria relation already exists");
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
				if(stmt!=null) {
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
		}
		return null;
	}

	/**
	 * Checks if user criteria relation exists in DB or not
	 * @param userID
	 * @param criteriaID
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public UserCriteriaDto ifUserCriteriaRelExists(int userID, int criteriaID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con=null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		UserCriteriaDto userCriteria = new UserCriteriaDto();
		try {
			con = connectionManager.getConnection();
			final String sql = QueryManager.getQuery("ifUserCriteriaRelExists");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, criteriaID);
			stmt.setInt(2, userID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				userCriteria.setCriteriaID(rs.getInt("criteria_id"));
				userCriteria.setUserID(rs.getInt("user_id"));
				userCriteria.setMarks(rs.getInt("obtained_marks"));
				userCriteria.setStatus(rs.getInt("status"));
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
		return userCriteria;
	}
}
