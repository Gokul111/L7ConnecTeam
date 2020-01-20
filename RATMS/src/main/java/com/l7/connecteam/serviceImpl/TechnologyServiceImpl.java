package com.l7.connecteam.serviceImpl;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.l7.connecteam.daoImpl.TechnologyDaoImpl;
import com.l7.connecteam.dto.AssessmentDto;
import com.l7.connecteam.dto.TechnologyDto;
import com.l7.connecteam.dto.TrainingGroupDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.service.TechnologyService;

/**
 * @author soumya.raj 
 * Implements business logic for technology related
 *         operations
 */
public class TechnologyServiceImpl implements TechnologyService{
	Logger logger = Logger.getLogger(TechnologyServiceImpl.class.getName());
	private TechnologyDaoImpl technologyImplObj = new TechnologyDaoImpl();

	/**
	 * Logical implementations involved in technology creation
	 * @param technologyObj
	 * @param trainID
	 * @param assessID
	 * @param maxMark
	 * @param minMarks
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public TechnologyDto createTechnology(TechnologyDto technologyObj, int trainID, int assessID, int maxMark,
			int minMarks) throws UIException, SQLException {
		TechnologyDto techData = technologyImplObj.createTechnology(technologyObj, trainID, assessID, maxMark,
				minMarks);
		return techData;
	}

	/**
	 * Logical implementations involved in determining if a technology already exists
	 * @param technologyObj
	 * @param trainObj
	 * @param assessObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public TechnologyDto ifTechnologyExists(TechnologyDto technologyObj, TrainingGroupDto trainObj,
			AssessmentDto assessObj, int maxMarks, int minMarks) throws UIException, SQLException {
		technologyObj = technologyImplObj.ifTechnologyExists(technologyObj, trainObj.getTrainGroupID(),
				assessObj.getAssessmentID());
		if (technologyObj.getTechnologyID() != 0) {
			logger.info("Technology with technology name " + technologyObj.getTechnologyName() + " already exists");

			boolean ifRelExists = setTrainTechnologyRel(technologyObj.getTechnologyID(),
					trainObj.getTrainGroupID(), assessObj.getAssessmentID(), maxMarks, minMarks);
			if (ifRelExists == true) {
				logger.info("Technology training group Relation created");
			} else {
				logger.info("Technology training group relation already exists");
			}
			return technologyObj;

		} else {
			technologyObj = createTechnology(technologyObj, trainObj.getTrainGroupID(),
					assessObj.getAssessmentID(), maxMarks, minMarks);
			return technologyObj;
		}
	}

	/**
	 * Logical implementations involved in setting user technology relation
	 * @param techID
	 * @param trainID
	 * @param assessID
	 * @param maxMark
	 * @param minMarks
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public Boolean setTrainTechnologyRel(int techID, int trainID, int assessID, int maxMark, int minMarks)
			throws UIException, SQLException {
		boolean isRelSet = technologyImplObj.setTrainTechnologyRel(techID, trainID, assessID, maxMark, minMarks);
		return isRelSet;
	}
}
