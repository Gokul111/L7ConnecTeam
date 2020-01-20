package com.l7.connecteam.serviceImpl;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.l7.connecteam.daoImpl.TrainingGroupDaoImpl;
import com.l7.connecteam.dto.TrainingGroupDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.service.TrainingGroupService;

/**
 * @author soumya.raj
 * Implements business logic for training group related operations
 */
public class TrainingGroupServiceImpl implements TrainingGroupService{
	Logger logger = Logger.getLogger(TrainingGroupServiceImpl.class.getName());
	private TrainingGroupDaoImpl trainImplObj=new TrainingGroupDaoImpl();
	
	/**
	 * Logical implementations involved in training group creation
	 * @param trainDataObj
	 * @param userDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public TrainingGroupDto createTrainingGroup(TrainingGroupDto trainDataObj,UserDto userDataObj) throws UIException, SQLException {
		TrainingGroupDto trainData=trainImplObj.createTrainingGroup(trainDataObj,userDataObj);
		return trainData;
	}
	
	/**
	 * Logical implementations involved in determining if a training group already exists
	 * @param trainDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public TrainingGroupDto ifTrainingGroupExists(TrainingGroupDto trainDataObj, UserDto userDataObj) throws UIException, SQLException {
		trainDataObj=trainImplObj.ifTrainingGroupExists(trainDataObj);
		if (trainDataObj.getTrainGroupID() != 0) {
			logger.info(
					"TrainingGroup with TrainingGroupname " + trainDataObj.getTrainGroupName() + " already exists");
			
			Boolean ifRelExists = setUserTrainingRel(userDataObj, trainDataObj);
			if (ifRelExists == true) {
				logger.info("User training group relation created");
			} else {
				logger.info("User training group already exists");
			}

			return trainDataObj;
		} else {
			trainDataObj = createTrainingGroup(trainDataObj, userDataObj);
			return trainDataObj;
		}
	}
	
	/**
	 * Logical implementations involved in setting user training group relation 
	 * @param userDataObj
	 * @param trainDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public boolean setUserTrainingRel(UserDto userDataObj, TrainingGroupDto trainDataObj) throws UIException, SQLException {
		Boolean isRelSet=trainImplObj.setUserTrainingRel(userDataObj,trainDataObj.getTrainGroupID());
		return isRelSet;
	}
}
