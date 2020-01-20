package com.l7.connecteam.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.l7.connecteam.dao.BatchwiseReportDao;
import com.l7.connecteam.dto.BatchDto;
import com.l7.connecteam.dto.BatchwiseReportDto;
import com.l7.connecteam.exception.DBDownException;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.manager.ConnectionManager;
import com.l7.connecteam.utility.QueryManager;

/**
 * @author soumya.raj 
 * This class implements the DAO class for batch wise report
 */
public class BatchwiseReportDaoImpl implements BatchwiseReportDao {
	Logger logger = Logger.getLogger(BatchwiseReportDaoImpl.class.getName());

	/**
	 * Gives required data from DB to produce batch wise report
	 * 
	 * @param batchDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<BatchwiseReportDto> getBatchReport(BatchDto batchDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BatchwiseReportDto batchReportObj = null;
		List<BatchwiseReportDto> batchReportList = new ArrayList<BatchwiseReportDto>();
		
		try {
			con = connectionManager.getConnection();
			List<String> trainees = getAllTrainees(batchDataObj);
			for (String trainee : trainees) {
				final String sql = new QueryManager().getQuery("getBatchReport");
				stmt = con.prepareStatement(sql);
				stmt.setString(1, batchDataObj.getBatchName());
				stmt.setString(2, trainee);
				rs = stmt.executeQuery();
				int percentMarks = 0;
				int assessCount = 0;
				while (rs.next()) {
					batchReportObj = new BatchwiseReportDto();
					batchReportObj.setTrainingGrp(rs.getString("training_group_name"));
					percentMarks = percentMarks + rs.getInt("percentage_marks");
					assessCount++;
					batchReportObj.setUserName(rs.getString("username"));
				}
				float avrPercentage = (float) percentMarks / assessCount;
				batchReportObj.setavgPercentMarks(avrPercentage);
				batchReportList.add(batchReportObj);			
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
			throw new UIException("Something went wrong. Try again");
		} catch (DBDownException e) {
			logger.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable");
		} catch (NullPointerException e){
			logger.info(e.getMessage());
			throw new UIException("Batch assessment data incomplete. Report cannot be generated");
		}finally {
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
		return batchReportList;
	}

	/**
	 * Returns the details of trainees existing in DB
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<String> getAllTrainees(BatchDto batchDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> trainees=new ArrayList<String>();
		final int roleID = 4;
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("getAllTrainees");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, roleID);
			stmt.setString(2, batchDataObj.getBatchName());
			rs=stmt.executeQuery();
			while(rs.next()) {
				trainees.add(rs.getString("username"));
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
		return trainees;
	}

}
