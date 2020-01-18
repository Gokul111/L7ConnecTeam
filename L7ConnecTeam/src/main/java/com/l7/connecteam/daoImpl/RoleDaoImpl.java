package com.l7.connecteam.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.l7.connecteam.dao.RoleDao;
import com.l7.connecteam.dto.FeatureDto;
import com.l7.connecteam.exception.DBDownException;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.manager.ConnectionManager;
import com.l7.connecteam.utility.QueryManager;

/**
 * @author soumya.raj
 * This class implements the DAO class for role
 */
public class RoleDaoImpl implements RoleDao {
	Logger logger = Logger.getLogger(RoleDaoImpl.class.getName());

	/**
	 * Returns all features available to a role from DB
	 * 
	 * @param roleID 
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<FeatureDto> getFeatureByRole(int roleID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		List<FeatureDto> featureList = new ArrayList<FeatureDto>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result_s = null;
		
		try {
			con = connectionManager.getConnection();
			final String sql = QueryManager.getQuery("getFeatureByRole");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, roleID);
			result_s = stmt.executeQuery();
			while (result_s.next()) {
				FeatureDto featureObj = new FeatureDto();
				featureObj.setFeatureId(result_s.getInt("feature_id"));
				featureObj.setFeatureName(result_s.getString("feature_name"));
				featureObj.setModuleId(result_s.getInt("module_id"));
				featureObj.setCreationDate(result_s.getDate("creation_date"));
				featureObj.setActiveStatus(result_s.getInt("active_status"));
				featureList.add(featureObj);
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
			throw new UIException("Something went wrong");
		
		} catch (DBDownException e) {
			logger.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable");
		} finally {
			if (result_s != null) {
				result_s.close();
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
		return featureList;
	}

}
