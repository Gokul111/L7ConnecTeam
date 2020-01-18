package com.l7.connecteam.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.daoImpl.BatchwiseReportDaoImpl;
import com.l7.connecteam.dto.BatchDto;
import com.l7.connecteam.dto.BatchwiseReportDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.service.BatchwiseReportService;

/**
 * Implements business logic for batchwise report related operations
 * @author soumya.raj
 */
public class BatchwiseReportServiceImpl implements BatchwiseReportService {
	private BatchwiseReportDaoImpl reportImplObj=new BatchwiseReportDaoImpl();
	
	/**
	 * Logical implementations involved in batchwise report creation
	 * @param batchDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<BatchwiseReportDto> getBatchReport(BatchDto batchDataObj) throws UIException, SQLException{
		List<BatchwiseReportDto> batchReportList=reportImplObj.getBatchReport(batchDataObj);
		return batchReportList;
	}

}
