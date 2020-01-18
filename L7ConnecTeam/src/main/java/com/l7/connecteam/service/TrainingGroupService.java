package com.l7.connecteam.service;

import java.sql.SQLException;

import com.l7.connecteam.dto.TrainingGroupDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;

public interface TrainingGroupService {
	public TrainingGroupDto createTrainingGroup(TrainingGroupDto trainDataObj,UserDto userDataObj) throws UIException, SQLException;
	public TrainingGroupDto ifTrainingGroupExists(TrainingGroupDto trainDataObj, UserDto userDataObj) throws UIException, SQLException;
	public boolean setUserTrainingRel(UserDto userDataObj, TrainingGroupDto trainDataObj) throws UIException, SQLException;
}
