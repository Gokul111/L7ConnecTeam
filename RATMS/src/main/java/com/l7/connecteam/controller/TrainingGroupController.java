package com.l7.connecteam.controller;

import java.sql.SQLException;
import java.util.List;

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
	/**
	 * Routes data needed to create training group
	 * @param trainDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public TrainingGroupDto createTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException{
		trainDataObj = trainServiceObj.createTrainingGroup(trainDataObj);
		return trainDataObj;
	}
	/**
	 * Routes data to delete a training group
	 * @param trainDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public boolean deleteTrainingGroup(TrainingGroupDto trainDataObj) throws UIException,SQLException{
		boolean deleteStatus=trainServiceObj.deleteTrainingGroup(trainDataObj);
		return deleteStatus;
	}
	/**
	 * routes data to update training group
	 * @param trainDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public TrainingGroupDto updateTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException{
		trainDataObj = trainServiceObj.updateTrainingGroup(trainDataObj);
		return trainDataObj;
	}
	/** 
	 * routes data to view all the training group details
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public List<TrainingGroupDto> viewAllTrainingGroup() throws UIException, SQLException{
		List<TrainingGroupDto> trainDataObj=trainServiceObj.viewAllTrainingGroup();
		return trainDataObj;
	}
	public TrainingGroupDto viewTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException{
		trainDataObj=trainServiceObj.viewTrainingGroup(trainDataObj);
		return trainDataObj;
	}
	

}
