package com.l7.connecteam.controller;

import java.sql.SQLException;
import com.l7.connecteam.dto.AssessmentDto;
import com.l7.connecteam.dto.TechnologyDto;
import com.l7.connecteam.dto.TrainingGroupDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.serviceImpl.TechnologyServiceImpl;

/**
 * Controls flow of technology related data
 * @author soumya.raj
 */
public class TechnologyController {
	TechnologyServiceImpl technologyServiceObj = new TechnologyServiceImpl();

	/**
	 * Routes data needed to create a technology
	 * @param technologyObj
	 * @param trainObj
	 * @param assessObj
	 * @param maxMarks
	 * @param minMarks
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public TechnologyDto createTechnology(TechnologyDto technologyObj, TrainingGroupDto trainObj, AssessmentDto assessObj,
			int maxMarks, int minMarks) throws UIException, SQLException {
		technologyObj = technologyServiceObj.ifTechnologyExists(technologyObj, trainObj, assessObj, maxMarks, minMarks);
		return technologyObj;
	}
}
