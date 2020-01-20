package com.l7.connecteam.dao;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.dto.IndividualReportDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;

/**
 * @author soumya.raj
 * This class acts as DAO to individual report DTO
 */
public interface IndividualReportDao {
	public List<IndividualReportDto> getIndividualReport(UserDto userDataObj) throws UIException, SQLException;
}
