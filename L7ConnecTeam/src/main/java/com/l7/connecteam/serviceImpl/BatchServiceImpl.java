package com.l7.connecteam.serviceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.l7.connecteam.daoImpl.BatchDaoImpl;
import com.l7.connecteam.dto.BatchDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.service.BatchService;

/**
 * Implements business logic for batch related operations
 * @author soumya.raj 
 */
public class BatchServiceImpl implements BatchService{
	private BatchDaoImpl batchImplObj = new BatchDaoImpl();
	Logger logger = Logger.getLogger(BatchServiceImpl.class.getName());

	/**
	 * Logical implementations involved in batch creation
	 * 
	 * @param batchDataObj
	 * @param userDataObj
	 * @return
	 * @throws SQLException
	 * @throws UIException
	 */
	public BatchDto createBatch(BatchDto batchDataObj, UserDto userDataObj) throws UIException, SQLException {
		BatchDto batchData = batchImplObj.createBatch(batchDataObj, userDataObj);
		return batchData;
	}

	/**
	 * Logical implementations involved in determining if a batch already exists
	 * 
	 * @param batchDataObj
	 * @return
	 * @throws SQLException
	 * @throws UIException
	 */
	public BatchDto ifBatchExists(BatchDto batchDataObj, UserDto userDataObj) throws UIException, SQLException {
		batchDataObj = batchImplObj.ifBatchExists(batchDataObj);
		if (batchDataObj.getBatchID() != 0) {
			logger.info("Batch with Batchname " + batchDataObj.getBatchName() + " already exists");
			return batchDataObj;
		} else {
			batchDataObj = createBatch(batchDataObj, userDataObj);
			Boolean ifRelCreated = setUserBatchRel(userDataObj, batchDataObj);
			if (ifRelCreated == true) {
				logger.info("User batch relation created");
			} else {
				logger.info("User batch relation already exists");
			}
			return batchDataObj;
		}
	}

	/**
	 * Logical implementations involved in setting user batch relation
	 * 
	 * @param userDataObj
	 * @param batchDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public boolean setUserBatchRel(UserDto userDataObj, BatchDto batchDataObj) throws UIException, SQLException {
		Boolean isRelSet = batchImplObj.setUserBatchRel(userDataObj.getUserId(), batchDataObj.getBatchID());
		return isRelSet;
	}

	/**
	 * Logical implementations involved in getting batch related data
	 * 
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<String> getAllBatchNames() throws UIException, SQLException {
		List<String> batchNames = batchImplObj.getAllBatchNames();
		return batchNames;
	}
}
