package com.l7.connecteam.dto;

/**
 * @author soumya.raj
 * Data layer for authorised module details for a user
 */
public class ModuleDto {
	private int moduleID;
	private String moduleName;
	public int getModuleID() {
		return moduleID;
	}
	public void setModuleID(int moduleID) {
		this.moduleID = moduleID;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	@Override
	public String toString() {
		return "Module [moduleID=" + moduleID + ", moduleName=" + moduleName + "]";
	}
	
}
