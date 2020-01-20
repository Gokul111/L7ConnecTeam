package com.l7.connecteam.dao;

import java.sql.SQLException;

import com.l7.connecteam.dto.CriteriaDto;
import com.l7.connecteam.dto.TechnologyDto;
import com.l7.connecteam.exception.UIException;

/**
 * @author soumya.raj
 * This class acts as DAO to criteria DTO
 */
public interface CriteriaDao {
	public CriteriaDto ifCriteriaExists(CriteriaDto criteriaObj, TechnologyDto techDataObj) throws UIException, SQLException;
	public CriteriaDto createCriteria(CriteriaDto criteriaObj, TechnologyDto techDataObj) throws UIException, SQLException;
}
