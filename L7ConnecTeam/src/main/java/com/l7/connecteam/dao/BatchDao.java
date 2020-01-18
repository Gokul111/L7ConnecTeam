package com.l7.connecteam.dao;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.dto.BatchDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;

/**
 * @author soumya.raj
 * This class acts as DAO to batch DTO
 */
public interface BatchDao {
	public BatchDto ifBatchExists(BatchDto batchDataObj) throws UIException, SQLException;
	public BatchDto createBatch(BatchDto batchDataObj, UserDto userDataObj) throws UIException, SQLException;
	public boolean setUserBatchRel(int userID, int batchID) throws UIException,SQLException;
	public boolean ifUserBatchExists(int userID, int batchID) throws UIException, SQLException;
	public List<String> getAllBatchNames() throws UIException, SQLException;
}
