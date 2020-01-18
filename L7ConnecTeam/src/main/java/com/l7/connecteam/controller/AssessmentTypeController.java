package com.l7.connecteam.controller;

import java.sql.SQLException;

import com.l7.connecteam.dto.AssessmentTypeDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.serviceImpl.AssessmentTypeServiceImpl;

/**
 * Controls flow of assessment type related data 
 * @author soumya.raj
 */
public class AssessmentTypeController {

	/**
	 * Routes data needed to create an assessment type
	 * @param assessTypeObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public AssessmentTypeDto createAssessmentType(AssessmentTypeDto assessTypeObj) throws UIException, SQLException {
		AssessmentTypeServiceImpl assessServiceObj = new AssessmentTypeServiceImpl();

		assessTypeObj = assessServiceObj.ifAssessTypeExists(assessTypeObj);
		return assessTypeObj;
	}

}
