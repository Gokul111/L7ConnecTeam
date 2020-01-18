package com.l7.connecteam.service;

import java.sql.SQLException;

import com.l7.connecteam.dto.AssessmentDto;
import com.l7.connecteam.dto.TechnologyDto;
import com.l7.connecteam.dto.TrainingGroupDto;
import com.l7.connecteam.exception.UIException;

public interface TechnologyService {
	public TechnologyDto createTechnology(TechnologyDto technologyObj, int trainID, int assessID, int maxMark,
			int minMarks) throws UIException, SQLException;
	public TechnologyDto ifTechnologyExists(TechnologyDto technologyObj, TrainingGroupDto trainObj,
			AssessmentDto assessObj, int maxMarks, int minMarks) throws UIException, SQLException;
	public Boolean setTrainTechnologyRel(int techID, int trainID, int assessID, int maxMark, int minMarks)
			throws UIException, SQLException;
}
