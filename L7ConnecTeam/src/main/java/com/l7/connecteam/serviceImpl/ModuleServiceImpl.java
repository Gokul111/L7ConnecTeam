package com.l7.connecteam.serviceImpl;

import java.sql.SQLException;

import com.l7.connecteam.daoImpl.ModuleDaoImpl;
import com.l7.connecteam.dto.ModuleDto;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.service.ModuleService;

/**
 * Implements business logic for module related operations
 * @author soumya.raj 
 */
public class ModuleServiceImpl implements ModuleService{
	private ModuleDaoImpl moduleImplObj = new ModuleDaoImpl();

	/**
	 * Logical implementations involved in retrieving module related data
	 * @param moduleObj
	 * @return
	 * @throws UIException
	 * @throws SQLException
	 */
	public ModuleDto getModuleByID(ModuleDto moduleObj) throws UIException, SQLException {
		moduleObj = moduleImplObj.getModuleByID(moduleObj);
		return moduleObj;
	}
}
