package com.l7.connecteam.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.l7.connecteam.dao.AssessmentTypeDao;
import com.l7.connecteam.dto.AssessmentTypeDto;
import com.l7.connecteam.exception.DBDownException;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.manager.ConnectionManager;
import com.l7.connecteam.utility.QueryManager;

/**
 * @author soumya.raj
 * This class implements the DAO class for assessment type
 */
public class AssessmentTypeDaoImpl implements AssessmentTypeDao {
	Logger logger = Logger.getLogger(AssessmentTypeDaoImpl.class.getName());

	/**
	 * Checks if assessment type already exists in DB or not
	 * @param assessTypeObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public AssessmentTypeDto ifAssessmentTypeExists(AssessmentTypeDto assessTypeObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con=null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		
		try {
			con = connectionManager.getConnection();
			final String sql = QueryManager.getQuery("ifAssessmentTypeExists");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, assessTypeObj.getAssessmentTypeName());
			rs = stmt.executeQuery();
			if (rs.next()) {
				assessTypeObj.setAssessmentTypeID(rs.getInt("assessment_type_id"));
				assessTypeObj.setAssessmentTypeName(rs.getString("assessment_type_name"));
			} else {
				return assessTypeObj;
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
		return assessTypeObj;
	}

	/**
	 * Writes an assessment type's data to DB
	 * @param assessTypeObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public AssessmentTypeDto createAssessmentType(AssessmentTypeDto assessTypeObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con=null;
		PreparedStatement stmt = null;
		int ifCreated=0;
		
		try {
			con = connectionManager.getConnection();
			final String sql = QueryManager.getQuery("createAssessmentType");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, assessTypeObj.getAssessmentTypeName());
			ifCreated=stmt.executeUpdate();
			if (ifCreated == 1) {
				logger.info("Assessment type with name" + assessTypeObj.getAssessmentTypeName() + "created");
			} else {
				logger.info("Failed to create assessment type with name" + assessTypeObj.getAssessmentTypeName());
			}
			assessTypeObj = ifAssessmentTypeExists(assessTypeObj);
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
		return assessTypeObj;
	}

}
