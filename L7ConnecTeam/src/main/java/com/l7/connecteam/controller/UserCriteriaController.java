package com.l7.connecteam.controller;

import java.sql.SQLException;
import java.util.Map;

import com.l7.connecteam.dto.UserCriteriaDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.serviceImpl.UserCriteriaServiceImpl;

/**
 * Controls flow of a user's assessment criteria data
 * @author soumya.raj
 */
public class UserCriteriaController {
	UserCriteriaServiceImpl criteriaServObj=new UserCriteriaServiceImpl();
	
	/**
	 * @param empID
	 * @param criteriaMap
	 * @param assess
	 * @return
	 * @throws SQLException 
	 * @throws UIException 
	 */
	public UserCriteriaDto createUserCriteriaRel(String empID,Map<String,Integer> criteriaMap,String assess) throws UIException, SQLException {
		UserCriteriaDto criteriaObj=criteriaServObj.createUserCriteriaRel(empID,criteriaMap,assess);
		return criteriaObj;
	}
}
