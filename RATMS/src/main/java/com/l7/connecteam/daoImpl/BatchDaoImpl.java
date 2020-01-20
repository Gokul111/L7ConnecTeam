package com.l7.connecteam.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

import com.l7.connecteam.dao.BatchDao;
import com.l7.connecteam.dto.BatchDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.DBDownException;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.manager.ConnectionManager;
import com.l7.connecteam.utility.QueryManager;

/**
 * @author soumya.raj
 * This class implements the DAO class for batch
 */
public class BatchDaoImpl implements BatchDao{
	Logger logger = Logger.getLogger(BatchDaoImpl.class.getName());

	/**
	 * Checks if a batch exists in DB or not
	 * @param batchDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public BatchDto ifBatchExists(BatchDto batchDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("ifBatchExists");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, batchDataObj.getBatchName());
			rs = stmt.executeQuery();
			if (rs.next()) {
				batchDataObj.setBatchID(rs.getInt("batch_id"));
				batchDataObj.setBatchName(rs.getString("batch_name"));
				batchDataObj.setStartDate(rs.getDate("start_date"));
				batchDataObj.setEndDate(rs.getDate("end_date"));
				batchDataObj.setBatchDesc(rs.getString("batch_description"));
			} else {
				return batchDataObj;
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
		return batchDataObj;
	}

	/**
	 * Writes a batch's data to DB if not already exists
	 * @param batchDataObj
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public BatchDto createBatch(BatchDto batchDataObj, UserDto userDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		final String batchDesc = "Retail Academy Batches";
		Connection con = null;
		PreparedStatement stmt = null;
		int ifCreated = 0;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("createBatch");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, batchDataObj.getBatchName());
			stmt.setDate(2, batchDataObj.getStartDate());
			stmt.setDate(3, batchDataObj.getEndDate());
			stmt.setString(4, batchDesc);
			ifCreated = stmt.executeUpdate();
			batchDataObj = ifBatchExists(batchDataObj);
			if (ifCreated == 1) {
				logger.info("Batch with name " + batchDataObj.getBatchName() + " created");
			} else {
				logger.info("Failed to create batch with name" + batchDataObj.getBatchName());
			}
//			Boolean ifRelCreated = setUserBatchRel(userDataObj.getUserId(), batchDataObj.getBatchID());
//			if (ifRelCreated == true) {
//				logger.info("User batch relation created");
//			} else {
//				logger.info("User batch relation creation failed");
//			}

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
		return batchDataObj;
	}

	/**
	 * Sets relation between user and respective batch in DB 
	 * @param userID
	 * @param batchID
	 * @return
	 * @throws UIException
	 * @throws SQLException 
	 */
	public boolean setUserBatchRel(int userID, int batchID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		int isRelSet = 0;
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("setUserBatchRel");
			stmt = con.prepareStatement(sql);
			boolean ifExists = ifUserBatchExists(userID, batchID);
			if (ifExists == false) {
				stmt.setInt(1, userID);
				stmt.setInt(2, batchID);
				isRelSet = stmt.executeUpdate();
			} else {
				logger.info("User batch relation already exists");
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
	 * Checks if user batch relation exists in DB or not
	 * @param userID
	 * @param batchID
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public boolean ifUserBatchExists(int userID, int batchID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean ifExists = false;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("ifUserBatchExists");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.setInt(2, batchID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				ifExists = true;
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
		return ifExists;
	}

	/**
	 * Returns all batch names that exist in DB
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<String> getAllBatchNames() throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		List<String> batchNames = new ArrayList<String>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("getAllBatches");
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				batchNames.add(rs.getString("batch_name"));
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
		return batchNames;
	}
}
