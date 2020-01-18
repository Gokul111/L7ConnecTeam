package com.l7.connecteam.service;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.dto.IndividualReportDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;

public interface IndividualReportService {
	public List<IndividualReportDto> getIndividualReport(UserDto userDataObj) throws UIException, SQLException;
}
