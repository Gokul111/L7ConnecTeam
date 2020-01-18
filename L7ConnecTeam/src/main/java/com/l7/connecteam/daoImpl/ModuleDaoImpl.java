package com.l7.connecteam.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.l7.connecteam.dao.ModuleDao;
import com.l7.connecteam.dto.ModuleDto;
import com.l7.connecteam.exception.DBDownException;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.manager.ConnectionManager;
import com.l7.connecteam.utility.QueryManager;

/**
 * @author soumya.raj
 * This class implements the DAO class for module 
 */
public class ModuleDaoImpl implements ModuleDao {
	Logger logger = Logger.getLogger(ModuleDaoImpl.class.getName());

	/**
	 * Returns all module names in DB by its moduleID
	 * 
	 * @param moduleObj 
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public ModuleDto getModuleByID(ModuleDto moduleObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			con = connectionManager.getConnection();
			final String sql = QueryManager.getQuery("getModuleByID");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, moduleObj.getModuleID());
			rs = stmt.executeQuery();
			while (rs.next()) {
				moduleObj.setModuleName(rs.getString("module_name"));
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
		return moduleObj;
	}
}
