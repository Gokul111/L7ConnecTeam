package com.l7.connecteam.service;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.dto.BatchDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;

public interface BatchService {
	public BatchDto createBatch(BatchDto batchDataObj, UserDto userDataObj) throws UIException, SQLException;
	public BatchDto ifBatchExists(BatchDto batchDataObj, UserDto userDataObj) throws UIException, SQLException;
	public boolean setUserBatchRel(UserDto userDataObj, BatchDto batchDataObj) throws UIException, SQLException;
	public List<String> getAllBatchNames() throws UIException, SQLException;
}
