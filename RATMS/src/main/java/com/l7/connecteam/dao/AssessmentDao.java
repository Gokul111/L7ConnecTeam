package com.l7.connecteam.dao;

import java.sql.SQLException;

import com.l7.connecteam.dto.AssessmentDto;
import com.l7.connecteam.dto.AssessmentTypeDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;

/**
 * This class acts as DAO to assessment DTO
 * @author soumya.raj
 */
public interface AssessmentDao {
	public AssessmentDto ifAssessmentExists(AssessmentDto assessDataObj) throws UIException, SQLException;
	public AssessmentDto createAssessment(AssessmentDto assessDataObj, UserDto userDataObj, AssessmentTypeDto assessTypeObj, int marks, String status, int maxMarks) throws UIException, SQLException;
	public boolean setAssessUserRel(int assessID, int userID, int marks, String status, int maxMarks) throws UIException, SQLException;
	public boolean ifUserAssessRelExists(int userID, int assessID) throws UIException, SQLException;
	public int getTrainingGrpIDByAssessID(int assessID) throws UIException, SQLException;
}
