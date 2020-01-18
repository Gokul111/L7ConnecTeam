package com.l7.connecteam.service;

import java.sql.SQLException;

import com.l7.connecteam.dto.CriteriaDto;
import com.l7.connecteam.dto.TechnologyDto;
import com.l7.connecteam.exception.UIException;

public interface CriteriaService {
	public CriteriaDto createCriteria(CriteriaDto criteriaObj, TechnologyDto techDataObj) throws UIException, SQLException;
	public CriteriaDto ifCriteriaExists(CriteriaDto criteriaObj,TechnologyDto techDataObj) throws UIException, SQLException;
	

}
