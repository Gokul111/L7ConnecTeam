package com.l7.connecteam.service;

import java.sql.SQLException;
import java.util.Map;

import com.l7.connecteam.dto.UserCriteriaDto;
import com.l7.connecteam.exception.UIException;

public interface UserCriteriaService {
	public UserCriteriaDto createUserCriteriaRel(String empID, Map<String,Integer> criteriaMap,String assess) throws UIException, SQLException;
}
