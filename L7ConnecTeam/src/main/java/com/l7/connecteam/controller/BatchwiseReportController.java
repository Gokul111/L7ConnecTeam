package com.l7.connecteam.controller;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.dto.BatchDto;
import com.l7.connecteam.dto.BatchwiseReportDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.serviceImpl.BatchwiseReportServiceImpl;

/**
 * Controls flow of batchwise report related data
 * @author soumya.raj
 */
public class BatchwiseReportController {
	BatchwiseReportServiceImpl reportServiceObj=new BatchwiseReportServiceImpl();
	
	/**
	 * Routes data needed to create report of a batch
	 * @param batchDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<BatchwiseReportDto> getBatchReport(BatchDto batchDataObj) throws UIException, SQLException{
		
		List<BatchwiseReportDto> batchReportList=reportServiceObj.getBatchReport(batchDataObj);
		return batchReportList;
	}
}
