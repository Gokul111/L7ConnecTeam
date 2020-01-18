package com.l7.connecteam.dao;

import java.sql.SQLException;

import com.l7.connecteam.dto.ModuleDto;
import com.l7.connecteam.exception.UIException;

/**
 * @author soumya.raj
 * This class acts as DAO to module DTO
 */
public interface ModuleDao {
	public ModuleDto getModuleByID(ModuleDto moduleObj) throws UIException, SQLException;
}
