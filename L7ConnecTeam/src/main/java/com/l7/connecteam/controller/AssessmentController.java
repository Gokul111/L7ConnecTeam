package com.l7.connecteam.controller;

import java.sql.SQLException;

import com.l7.connecteam.dto.AssessmentDto;
import com.l7.connecteam.dto.AssessmentTypeDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.serviceImpl.AssessmentServiceImpl;

/**
 * Controls flow of assessment related data
 * @author soumya.raj 
 */
public class AssessmentController {
	AssessmentServiceImpl assessmentServiceObj = new AssessmentServiceImpl();

	/**
	 * Routes data needed to create an assessment 
	 * @param assessDataObj
	 * @param userDataObj
	 * @param assessTypeObj
	 * @param obtainedMarks
	 * @param status
	 * @param maxMarks
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public AssessmentDto createAssessment(AssessmentDto assessDataObj, UserDto userDataObj, AssessmentTypeDto assessTypeObj,
			int obtainedMarks, String status, int maxMarks) throws UIException, SQLException {

		assessDataObj = assessmentServiceObj.ifAssessmentExists(assessDataObj, userDataObj, assessTypeObj,
				obtainedMarks, status, maxMarks);
		return assessDataObj;
	}

	/**
	 * Routes data needed to set assessment details of a user
	 * @param assessDataObj
	 * @param userDataObj
	 * @param assessTypeObj
	 * @param obtainedMarks
	 * @param status
	 * @param maxMarks
	 * @throws UIException
	 * @throws SQLException
	 */
	public void setAssessUserRel(AssessmentDto assessDataObj, UserDto userDataObj, AssessmentTypeDto assessTypeObj,
			int obtainedMarks, String status, int maxMarks) throws UIException, SQLException {
		
		assessmentServiceObj.setAssessUserRel(assessDataObj.getAssessmentID(), userDataObj.getUserId(), obtainedMarks,
				status, maxMarks);
	}
}
