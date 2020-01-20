package com.l7.connecteam.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.l7.connecteam.dao.CriteriaDao;
import com.l7.connecteam.dto.CriteriaDto;
import com.l7.connecteam.dto.TechnologyDto;
import com.l7.connecteam.exception.DBDownException;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.manager.ConnectionManager;
import com.l7.connecteam.utility.QueryManager;

/**
 * @author soumya.raj
 * This class implements the DAO class for criteria
 */
public class CriteriaDaoImpl implements CriteriaDao{
	Logger logger = Logger.getLogger(CriteriaDao.class.getName());
	
	/**
	 * Checks if criteria exists in DB or not
	 * @param criteriaObj
	 * @return
	 * @throws UIException
	 * @throws SQLException 
	 */
	public CriteriaDto ifCriteriaExists(CriteriaDto criteriaObj, TechnologyDto techDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con=null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("ifCriteriaExists");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, criteriaObj.getCriteriaName());
			stmt.setInt(2, techDataObj.getGrpTechID());
			rs = stmt.executeQuery();
			if (rs.next()) {
				criteriaObj.setCriteriaID(rs.getInt("criteria_id"));
				criteriaObj.setGrpTechID(rs.getInt("grp_technology_id"));
				criteriaObj.setCriteriaName(rs.getString("criteria_name"));
				criteriaObj.setCriteriaMinMarks(rs.getInt("criteria_minmarks"));
				criteriaObj.setCriteriaMaxMarks(rs.getInt("criteria_maxmarks"));
			} else {
				return criteriaObj;
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
		return criteriaObj;
	}

	/**
	 * Writes criteria's data to DB if not already exists
	 * @param criteriaObj
	 * @return
	 * @throws UIException
	 * @throws SQLException 
	 */
	public CriteriaDto createCriteria(CriteriaDto criteriaObj, TechnologyDto techDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con=null;
		PreparedStatement stmt = null;
		int ifCreated=0;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("createCriteria");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, techDataObj.getGrpTechID());
			stmt.setString(2, criteriaObj.getCriteriaName());
			stmt.setInt(3, criteriaObj.getCriteriaMinMarks());
			stmt.setInt(4, criteriaObj.getCriteriaMaxMarks());
			ifCreated = stmt.executeUpdate();
			if (ifCreated == 1) {
				logger.info("Criteria with name "+criteriaObj.getCriteriaName()+" created");
			} else {
				logger.info("Failed to create "+criteriaObj.getCriteriaName()+" criteria");
			}
			criteriaObj = ifCriteriaExists(criteriaObj,techDataObj);
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
			if (con != null) {
				try {
					connectionManager.closeConnection(con);
				} catch (DBDownException e) {
					logger.info(e.getMessage());
					throw new UIException("Connection temporarily unavailable");
				}
			}
		}
		return criteriaObj;
	}
}
