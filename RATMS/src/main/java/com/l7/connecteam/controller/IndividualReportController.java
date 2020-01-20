package com.l7.connecteam.controller;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.dto.IndividualReportDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.serviceImpl.IndividualReportServiceImpl;

/**
 * Controls flow of individual report related data of a user
 * @author soumya.raj
 */
public class IndividualReportController {
	IndividualReportServiceImpl serviceObj = new IndividualReportServiceImpl();

	/**
	 * Routes data used to get individual report
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<IndividualReportDto> getIndividualReport(UserDto userDataObj) throws UIException, SQLException{
		List<IndividualReportDto> indRepostList = serviceObj.getIndividualReport(userDataObj);
		return indRepostList;
	}
}
