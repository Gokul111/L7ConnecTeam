package com.l7.connecteam.serviceImpl;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.l7.connecteam.daoImpl.AssessmentDaoImpl;
import com.l7.connecteam.dto.AssessmentDto;
import com.l7.connecteam.dto.AssessmentTypeDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.service.AssessmentService;

/**
 * Implements business logic for assessment related operations
 * @author soumya.raj
 */
public class AssessmentServiceImpl implements AssessmentService {
	Logger logger = Logger.getLogger(AssessmentServiceImpl.class.getName());
	private AssessmentDaoImpl assessImplObj=new AssessmentDaoImpl();
	
	/**
	 * Logical implementations involved in assessment creation
	 * @param assessDataObj
	 * @param userDataObj
	 * @param assessTypeObj
	 * @param marks
	 * @param status
	 * @param maxMarks
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public AssessmentDto createAssessment(AssessmentDto assessDataObj,UserDto userDataObj,AssessmentTypeDto assessTypeObj, int marks, String status,int maxMarks) throws UIException, SQLException {
		AssessmentDto assessData= assessImplObj.createAssessment(assessDataObj,userDataObj,assessTypeObj,marks,status,maxMarks);
		return assessData;
	}
	
	/**
	 * Logical implementations involved in determining if an assessment already exists
	 * @param assessDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public AssessmentDto ifAssessmentExists(AssessmentDto assessDataObj,UserDto userDataObj, AssessmentTypeDto assessTypeObj,
			int obtainedMarks, String status, int maxMarks) throws UIException, SQLException {
		assessDataObj=assessImplObj.ifAssessmentExists(assessDataObj);
		
		if (assessDataObj.getAssessmentID() != 0) {
			logger.info("Assessment with Assessment name " + assessDataObj.getAssessmentName() + " already exists");
			return assessDataObj;
		} else {
			assessDataObj = createAssessment(assessDataObj, userDataObj, assessTypeObj,
					obtainedMarks, status, maxMarks);
			return assessDataObj;
		}
	}
	
	/**
	 * Logical implementations involved in setting user assessment relation
	 * @param assessID
	 * @param userID
	 * @param marks
	 * @param status
	 * @param maxMarks
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public void setAssessUserRel(int assessID, int userID, int marks, String status, int maxMarks) throws UIException, SQLException {
		Boolean ifRelExists=assessImplObj.setAssessUserRel(assessID, userID, marks, status, maxMarks);
		if (ifRelExists == true) {
			logger.info("User assessment relation created");
		} else {
			logger.info("User assessment relation already exists");
		}
	}
}
