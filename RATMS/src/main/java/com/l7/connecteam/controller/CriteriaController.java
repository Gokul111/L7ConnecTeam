package com.l7.connecteam.controller;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.l7.connecteam.dto.CriteriaDto;
import com.l7.connecteam.dto.TechnologyDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.serviceImpl.CriteriaServiceImpl;

/**
 * Controls flow of an assessment's criteria related data
 * @author soumya.raj
 */
public class CriteriaController {
	Logger logger = Logger.getLogger(CriteriaController.class.getName());
	CriteriaServiceImpl crServObj=new CriteriaServiceImpl();

	/** Routes data used to create an assessment criteria  
	 * @param criteriaObj
	 * @param techDataObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public CriteriaDto createCriteria(CriteriaDto criteriaObj, TechnologyDto techDataObj) throws UIException, SQLException {
		criteriaObj = crServObj.ifCriteriaExists(criteriaObj,techDataObj);
		return criteriaObj;
	}
}
