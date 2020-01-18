package com.l7.connecteam.controller;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.dto.BatchDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.serviceImpl.BatchServiceImpl;

/**
 * Controls flow of batch related data
 * @author soumya.raj
 */ 
public class BatchController {
	BatchServiceImpl batchServiceObj = new BatchServiceImpl();

	/**
	 * Routes data needed to create a batch 
	 * @param batchDataObj
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public BatchDto createBatch(BatchDto batchDataObj, UserDto userDataObj) throws UIException, SQLException {
		batchDataObj = batchServiceObj.ifBatchExists(batchDataObj,userDataObj);
		return batchDataObj;
	}
	
	/**
	 * Routes data needed to get names of all batches
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<String> getAllBatchNames() throws UIException, SQLException{
		List<String> batchNames=batchServiceObj.getAllBatchNames();
		return batchNames;
	}
}
