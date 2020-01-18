package com.l7.connecteam.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.daoImpl.IndividualReportDaoImpl;
import com.l7.connecteam.dto.IndividualReportDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.service.IndividualReportService;

/**
 * Implements business logic for individual report related operations
 * @author soumya.raj
 *
 */
public class IndividualReportServiceImpl implements IndividualReportService {
	private IndividualReportDaoImpl implObj=new IndividualReportDaoImpl();
	
	/**
	 * Logical implementations involved in individual report creation
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<IndividualReportDto> getIndividualReport(UserDto userDataObj) throws UIException, SQLException {
		List<IndividualReportDto> indReportList=implObj.getIndividualReport(userDataObj);
		return indReportList;
	}
}
