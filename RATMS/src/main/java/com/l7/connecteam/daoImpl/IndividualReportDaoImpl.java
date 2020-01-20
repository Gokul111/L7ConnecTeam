package com.l7.connecteam.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

import com.l7.connecteam.dao.IndividualReportDao;
import com.l7.connecteam.dto.IndividualReportDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.DBDownException;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.manager.ConnectionManager;
import com.l7.connecteam.utility.QueryManager;

/**
 * @author soumya.raj
 * This class implements the DAO class for Individual report
 */
public class IndividualReportDaoImpl implements IndividualReportDao{
	Logger logger = Logger.getLogger(IndividualReportDaoImpl.class.getName());

	/**
	 * Gives required data from DB to produce individual report
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<IndividualReportDto> getIndividualReport(UserDto userDataObj) throws UIException, SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtReport = null;
		ResultSet rs = null;
		ResultSet rsReport = null;
		int userID = 0;
		IndividualReportDto individualReportObj = null;
		List<IndividualReportDto> indReportList = new ArrayList<IndividualReportDto>();
		
		try {
			con = connectionManager.getConnection();
			final String sql = new QueryManager().getQuery("getUserID");
			stmt = con.prepareStatement(sql);
			stmt.setString(1, userDataObj.getUsername());
			rs = stmt.executeQuery();
			while (rs.next()) {
				userID = rs.getInt("user_id");
			}
			final String sqlReport = new QueryManager().getQuery("getIndividualReport");
			stmtReport = con.prepareStatement(sqlReport);
			stmtReport.setInt(1, userID);
			rsReport = stmtReport.executeQuery();
			while (rsReport.next()) {
				individualReportObj = new IndividualReportDto();
				individualReportObj.setAssessment(rsReport.getString("assessment_name"));
				individualReportObj.setTrainingGrpName(rsReport.getString("training_group_name"));
				individualReportObj.setAssessMaxMark(rsReport.getInt("assessment_maxmarks"));
				individualReportObj.setAssessMark(rsReport.getInt("obtained_marks"));
				individualReportObj.setAssessMinMark(rsReport.getInt("assessment_minmarks"));
				individualReportObj.setAssessPercentage(rsReport.getInt("percentage_marks"));
				if (rsReport.getInt("status") == 1) {
					individualReportObj.setStatus("Pass");
				} else {
					individualReportObj.setStatus("Failed");
				}
				indReportList.add(individualReportObj);
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
			throw new UIException("Something went wrong. Try again");
		} catch (DBDownException e) {
			logger.info(e.getMessage());
			throw new UIException("Connection temporarily unavailable");
		} finally {
			if (rs != null ) {
				rs.close();
			}
			if (rsReport != null ) {
				rsReport.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (stmtReport != null) {
				stmtReport.close();
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
		return indReportList;
	}

}
