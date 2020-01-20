package com.l7.connecteam.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.l7.connecteam.dao.TechnologyDao;
import com.l7.connecteam.dto.TechnologyDto;
import com.l7.connecteam.exception.DBDownException;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.manager.ConnectionManager;
import com.l7.connecteam.utility.QueryManager;

/**
 * @author soumya.raj
 * This class implements the DAO class for technology
 */
public class TechnologyDaoImpl implements TechnologyDao{
	Logger logger = Logger.getLogger(TechnologyDaoImpl.class.getName());
	/**
	 * Checks if technology exists in DB or not
	 * 
	 * @param technologyObj 
	 * @param trainID 
	 * @param assessID
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public TechnologyDto ifTechnologyExists(TechnologyDto technologyObj, int trainID, int assessID)
			throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int grpTechID = 0;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("ifTechnologyExists");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, technologyObj.getTechnologyName());
			rs = stmt.executeQuery();
			if (rs.next()) {
				technologyObj.setTechnologyID(rs.getInt("technology_id"));
				technologyObj.setTechnologyName(rs.getString("technology_name"));
				grpTechID = getGrpTechnologyID(rs.getInt("technology_id"), trainID, assessID);
				if (grpTechID != 0) {
					technologyObj.setGrpTechID(grpTechID);
				}
			} else {
				return technologyObj;
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
		return technologyObj;
	}

	/**
	 *  Writes technology's data to DB if not already exists
	 * 
	 * @param technologyObj 
	 * @param trainID 
	 * @param assessID
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public TechnologyDto createTechnology(TechnologyDto technologyObj, int trainID, int assessID, int maxMark, int minMarks)
			throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		int ifCreated = 0;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("createTechnology");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, technologyObj.getTechnologyName());
			ifCreated = stmt.executeUpdate();
			if (ifCreated == 1) {
				logger.info("Technology with technology name" + technologyObj.getTechnologyName() + "created");
			} else {
				logger.info("Failed to create technology with technology name" + technologyObj.getTechnologyName());
			}
			technologyObj = ifTechnologyExists(technologyObj, trainID, assessID);
			Boolean ifRelExist = setTrainTechnologyRel(technologyObj.getTechnologyID(), trainID, assessID, maxMark,
					minMarks);
			if (ifRelExist == true) {
				logger.info("Technology training group relation created");
			} else {
				logger.info("Technology training group relation creation failed");
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
		return technologyObj;
	}

	/**
	 * Sets relation between technology and respective training group in DB
	 * @param techID
	 * @param trainID
	 * @param assessID
	 * @param maxMark
	 * @param minMark
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 * 
	 */
	public boolean setTrainTechnologyRel(int techID, int trainID, int assessID, int maxMark, int minMark)
			throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		int ifCreated = 0;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("setTechnologyTrainingGroupRel");
			stmt = con.prepareStatement(sql);
			Boolean ifRelExists = ifTechTrainRelExists(techID, trainID, assessID);
			if (ifRelExists == false) {
				stmt.setInt(1, techID);
				stmt.setInt(2, trainID);
				stmt.setInt(3, assessID);
				stmt.setInt(4, minMark);
				stmt.setInt(5, maxMark);
				ifCreated = stmt.executeUpdate();
			}
			else {
				logger.info("TechnologyDto training group relation already exists");
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
	 * Checks if technology training group relation exists in DB or not
	 * @param techID
	 * @param trainID
	 * @param assessID
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public boolean ifTechTrainRelExists(int techID, int trainID, int assessID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean ifRelExists = false;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("ifTechnologyTrainingGroupRelExist");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, techID);
			stmt.setInt(2, trainID);
			stmt.setInt(3, assessID);
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
		return ifRelExists;
	}

	/**
	 * Returns group technologyID for an assessment in a particular technology and training group
	 * @param techID
	 * @param trainID
	 * @param assessID
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public int getGrpTechnologyID(int techID, int trainID, int assessID) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int grpTechID = 0;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("getTrainingGroupID");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, techID);
			stmt.setInt(2, trainID);
			stmt.setInt(3, assessID);
			rs = stmt.executeQuery();
			while (rs.next()) {
				grpTechID = rs.getInt("grp_technology_id");
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

		return grpTechID;
	}
}
