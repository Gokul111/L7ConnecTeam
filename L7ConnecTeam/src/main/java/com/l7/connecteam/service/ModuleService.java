package com.l7.connecteam.service;

import java.sql.SQLException;

import com.l7.connecteam.dto.ModuleDto;
import com.l7.connecteam.exception.UIException;

public interface ModuleService {
	public ModuleDto getModuleByID(ModuleDto moduleObj) throws UIException, SQLException;
}
