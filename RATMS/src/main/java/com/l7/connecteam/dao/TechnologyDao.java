package com.l7.connecteam.dao;

import java.sql.SQLException;

import com.l7.connecteam.dto.TechnologyDto;
import com.l7.connecteam.exception.UIException;

/**
 * @author soumya.raj
 * This class acts as DAO to technology DTO
 */
public interface TechnologyDao {
	public TechnologyDto ifTechnologyExists(TechnologyDto technologyObj, int trainID, int assessID) throws UIException, SQLException;
	public TechnologyDto createTechnology(TechnologyDto technologyObj, int trainID, int assessID, int maxMark, int minMarks) throws UIException, SQLException;
	public boolean setTrainTechnologyRel(int techID, int trainID, int assessID, int maxMark, int minMark) throws UIException, SQLException;
	public boolean ifTechTrainRelExists(int techID, int trainID, int assessID) throws UIException, SQLException;
	public int getGrpTechnologyID(int techID, int trainID, int assessID) throws UIException, SQLException;
}
