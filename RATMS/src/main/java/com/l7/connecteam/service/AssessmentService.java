package com.l7.connecteam.service;

import java.sql.SQLException;

import com.l7.connecteam.dto.AssessmentDto;
import com.l7.connecteam.dto.AssessmentTypeDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;

public interface AssessmentService {
	public AssessmentDto createAssessment(AssessmentDto assessDataObj, UserDto userDataObj,
			AssessmentTypeDto assessTypeObj, int marks, String status, int maxMarks) throws UIException, SQLException;

	public AssessmentDto ifAssessmentExists(AssessmentDto assessDataObj, UserDto userDataObj,
			AssessmentTypeDto assessTypeObj, int obtainedMarks, String status, int maxMarks)
			throws UIException, SQLException;
	
	public void setAssessUserRel(int assessID, int userID, int marks, String status, int maxMarks) throws UIException, SQLException;
}
