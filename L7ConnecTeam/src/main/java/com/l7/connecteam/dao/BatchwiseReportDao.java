package com.l7.connecteam.dao;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.dto.BatchDto;
import com.l7.connecteam.dto.BatchwiseReportDto;
import com.l7.connecteam.exception.UIException;

/**
 * @author soumya.raj
 * This class acts as DAO to batchwise report DTO
 */
public interface BatchwiseReportDao {
	public List<BatchwiseReportDto> getBatchReport(BatchDto batchDataObj) throws UIException, SQLException;
	public List<String> getAllTrainees(BatchDto batchDataObj) throws UIException, SQLException;
}
