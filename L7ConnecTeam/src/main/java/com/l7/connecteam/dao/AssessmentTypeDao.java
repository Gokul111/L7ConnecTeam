package com.l7.connecteam.dao;

import java.sql.SQLException;

import com.l7.connecteam.dto.AssessmentTypeDto;
import com.l7.connecteam.exception.UIException;

/**
 * @author soumya.raj
 * This class acts as DAO to assessment type DTO
 *
 */
public interface AssessmentTypeDao {
	public AssessmentTypeDto ifAssessmentTypeExists(AssessmentTypeDto assessTypeObj) throws UIException, SQLException;
	public AssessmentTypeDto createAssessmentType(AssessmentTypeDto assessTypeObj) throws UIException, SQLException;
}
