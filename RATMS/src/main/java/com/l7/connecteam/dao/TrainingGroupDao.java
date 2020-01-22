package com.l7.connecteam.dao;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.dto.TrainingGroupDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;

/**
 * @author Litmus7
 * This class acts as DAO to training group DTO
 */
public interface TrainingGroupDao {
	public TrainingGroupDto ifTrainingGroupExists(TrainingGroupDto trainDataObj) throws UIException, SQLException;
	public TrainingGroupDto createTrainingGroup(TrainingGroupDto trainDataObj, UserDto userDataObj) throws UIException, SQLException;
	public TrainingGroupDto createTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException;
	public TrainingGroupDto updateTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException;
	public boolean deleteTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException;
	public List<TrainingGroupDto> viewAllTrainingGroup() throws UIException,SQLException;
	public TrainingGroupDto viewTrainingGroup(TrainingGroupDto trainDataObj) throws UIException,SQLException;
	public boolean setUserTrainingRel(UserDto userDataObj, int trainID) throws UIException, SQLException;
	public boolean ifUserTrainRelExists(int userID, int trainID) throws UIException, SQLException;
	public int getTrainTypeID() throws UIException, SQLException;
	public int getTrainTypeID(String trainType) throws UIException, SQLException;

}
