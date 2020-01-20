package com.l7.connecteam.serviceImpl;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.l7.connecteam.daoImpl.AssessmentTypeDaoImpl;
import com.l7.connecteam.dto.AssessmentTypeDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.service.AssessmentTypeService;

/**
 * Implements business logic for assessment type related operations
 * @author soumya.raj 
 */
public class AssessmentTypeServiceImpl implements AssessmentTypeService {
	private AssessmentTypeDaoImpl assessTypeImplObj = new AssessmentTypeDaoImpl();
	Logger logger = Logger.getLogger(AssessmentTypeServiceImpl.class.getName());

	/**
	 * Logical implementations involved in assessment type creation
	 * 
	 * @param assessTypeObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public AssessmentTypeDto createAssessType(AssessmentTypeDto assessTypeObj) throws UIException, SQLException {
		AssessmentTypeDto assessTypeData = assessTypeImplObj.createAssessmentType(assessTypeObj);
		return assessTypeData;
	}

	/**
	 * Logical implementations involved in in determining if an assessment type
	 * already exists
	 * 
	 * @param assessTypeObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public AssessmentTypeDto ifAssessTypeExists(AssessmentTypeDto assessTypeObj) throws UIException, SQLException {
		AssessmentTypeDto assessTypeData = assessTypeImplObj.ifAssessmentTypeExists(assessTypeObj);
		if (assessTypeData.getAssessmentTypeID() != 0) {
			logger.info("Assessment type with assessment type name " + assessTypeData.getAssessmentTypeName()
					+ " already exists");
			return assessTypeObj;
		} else {
			assessTypeData = createAssessType(assessTypeObj);
			return assessTypeObj;
		}
	}
}
