package com.l7.connecteam.service;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.dto.TrainingGroupDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;

public interface TrainingGroupService {
	public TrainingGroupDto createTrainingGroup(TrainingGroupDto trainDataObj,UserDto userDataObj) throws UIException, SQLException;
	public TrainingGroupDto createTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException;
	public TrainingGroupDto ifTrainingGroupExists(TrainingGroupDto trainDataObj, UserDto userDataObj) throws UIException, SQLException;
	public boolean setUserTrainingRel(UserDto userDataObj, TrainingGroupDto trainDataObj) throws UIException, SQLException;
	public TrainingGroupDto updateTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException;
	public boolean deleteTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException;
	public List<TrainingGroupDto> viewAllTrainingGroup() throws UIException, SQLException;
	public TrainingGroupDto viewTrainingGroup(TrainingGroupDto trainDataObj) throws UIException, SQLException;
}
