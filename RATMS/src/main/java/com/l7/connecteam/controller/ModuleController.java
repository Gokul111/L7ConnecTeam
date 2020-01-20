package com.l7.connecteam.controller;

import java.sql.SQLException;

import com.l7.connecteam.dto.ModuleDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.serviceImpl.ModuleServiceImpl;

/**
 * @author soumya.raj
 * Controls flow of authorised module data for a user
 */
public class ModuleController {
	ModuleServiceImpl moduleServObj=new ModuleServiceImpl();
	
	/**
	 * Routes flow of data to get modules by its ID
	 * @param moduleObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public ModuleDto getModuleByID(ModuleDto moduleObj) throws UIException, SQLException {
		moduleObj=moduleServObj.getModuleByID(moduleObj);
		return moduleObj;
	}
}
