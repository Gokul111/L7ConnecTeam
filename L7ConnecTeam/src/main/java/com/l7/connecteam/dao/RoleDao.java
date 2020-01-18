package com.l7.connecteam.dao;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.dto.FeatureDto;
import com.l7.connecteam.exception.UIException;

/**
 * @author soumya.raj
 * This class acts as DAO to role DTO
 */
public interface RoleDao {
	public List<FeatureDto> getFeatureByRole(int roleID) throws UIException, SQLException;
}
