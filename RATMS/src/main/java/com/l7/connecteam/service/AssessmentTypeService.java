package com.l7.connecteam.service;

import java.sql.SQLException;

import com.l7.connecteam.dto.AssessmentTypeDto;
import com.l7.connecteam.exception.UIException;

public interface AssessmentTypeService {
	public AssessmentTypeDto createAssessType(AssessmentTypeDto assessTypeObj) throws UIException, SQLException;
	public AssessmentTypeDto ifAssessTypeExists(AssessmentTypeDto assessTypeObj) throws UIException, SQLException;

}
