package com.l7.connecteam.serviceImpl;

import java.sql.SQLException;
import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
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
	//private static final Logger LOGGER = LogManager.getLogger(TrainingGroupServiceImpl.class);

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
			//LOGGER.info(
				//	"TrainingGroup with TrainingGroupname " + trainDataObj.getTrainGroupName() + " already exists");
			
			Boolean ifRelExists = setUserTrainingRel(userDataObj, trainDataObj);
			if (ifRelExists == true) {
				//LOGGER.info("User training group relation created");
			} else {
				//LOGGER.info("User training group already exists");
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

	public TrainingGroupDto updateTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException {
		TrainingGroupDto trainData=trainImplObj.updateTrainingGroup(trainDataObj);
		return trainData;
	}

	public TrainingGroupDto createTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException {
		TrainingGroupDto trainData=trainImplObj.createTrainingGroup(trainDataObj);
		return trainData;
	}

	public boolean deleteTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException {
		Boolean deleteStatus=trainImplObj.deleteTrainingGroup(trainDataObj);
		return deleteStatus;
	}

	public List<TrainingGroupDto> viewAllTrainingGroup() throws UIException, SQLException {
		List<TrainingGroupDto> trainData=trainImplObj.viewAllTrainingGroup();
		return trainData;
	}

	public TrainingGroupDto viewTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException {
		TrainingGroupDto trainData=trainImplObj.viewTrainingGroup(trainDataObj);
		return trainData;
	}
}
