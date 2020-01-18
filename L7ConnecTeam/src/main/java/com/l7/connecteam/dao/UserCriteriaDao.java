package com.l7.connecteam.dao;

import java.util.Map;

import java.sql.SQLException;

import com.l7.connecteam.dto.UserCriteriaDto;
import com.l7.connecteam.exception.UIException;

/**
 * @author soumya.raj
 * This class acts as DAO to user criteria DTO
 */
public interface UserCriteriaDao {
	public UserCriteriaDto createUserCriteriaRel(String empID, Map<String, Integer> criteriaMap, String assess) throws UIException,SQLException;
	public UserCriteriaDto ifUserCriteriaRelExists(int userID, int criteriaID) throws UIException,SQLException;
}
