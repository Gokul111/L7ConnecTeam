package com.l7.connecteam.controller;

import java.sql.SQLException;

import com.l7.connecteam.dto.TrainingGroupDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.serviceImpl.TrainingGroupServiceImpl;

/**
 * Controls flow of training group related data
 * @author soumya.raj
 */
public class TrainingGroupController {
	TrainingGroupServiceImpl trainServiceObj = new TrainingGroupServiceImpl();
	
	/** Routes data needed to create a training group
	 * @param trainDataObj
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public TrainingGroupDto createTrainingGroup(TrainingGroupDto trainDataObj, UserDto userDataObj) throws UIException, SQLException {
		trainDataObj = trainServiceObj.ifTrainingGroupExists(trainDataObj, userDataObj);
		return trainDataObj;

	}

}
