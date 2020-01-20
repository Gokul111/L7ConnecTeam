package com.l7.connecteam.dao;

import java.sql.SQLException;

import com.l7.connecteam.dto.TrainingGroupDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;

/**
 * @author soumya.raj
 * This class acts as DAO to training group DTO
 */
public interface TrainingGroupDao {
	public TrainingGroupDto ifTrainingGroupExists(TrainingGroupDto trainDataObj) throws UIException, SQLException;
	public TrainingGroupDto createTrainingGroup(TrainingGroupDto trainDataObj, UserDto userDataObj) throws UIException, SQLException;
	public boolean setUserTrainingRel(UserDto userDataObj, int trainID) throws UIException, SQLException;
	public boolean ifUserTrainRelExists(int userID, int trainID) throws UIException, SQLException;
	public int getTrainTypeID() throws UIException, SQLException;

}
