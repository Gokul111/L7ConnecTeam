package com.l7.connecteam.serviceImpl;

import java.sql.SQLException;
import java.util.Map;

import com.l7.connecteam.daoImpl.UserCriteriaDaoImpl;
import com.l7.connecteam.dto.UserCriteriaDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.service.UserCriteriaService;

/**
 * @author soumya.raj
 * Implements business logic for assessment related operations
 */
public class UserCriteriaServiceImpl implements UserCriteriaService{
	private UserCriteriaDaoImpl criteriaImplObj=new UserCriteriaDaoImpl();
	
	/**
	 * Logical implementations involved in user criteria relation creation
	 * @param empID
	 * @param criteriaMap
	 * @param assess
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public UserCriteriaDto createUserCriteriaRel(String empID, Map<String,Integer> criteriaMap,String assess) throws UIException, SQLException {
		UserCriteriaDto criteriaObj=criteriaImplObj.createUserCriteriaRel(empID,criteriaMap,assess);
		return criteriaObj;
	}
}
