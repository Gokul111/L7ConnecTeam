package com.l7.connecteam.dto;

public class TechnologyDto {
	private int technologyID;
	private String technologyName;
	private int grpTechID;
	public int getTechnologyID() {
		return technologyID;
	}
	public void setTechnologyID(int technologyID) {
		this.technologyID = technologyID;
	}
	public String getTechnologyName() {
		return technologyName;
	}
	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}
	public int getGrpTechID() {
		return grpTechID;
	}
	public void setGrpTechID(int grpTechID) {
		this.grpTechID = grpTechID;
	}
	@Override
	public String toString() {
		return "Technology [technologyID=" + technologyID + ", technologyName=" + technologyName + ", grpTechID="
				+ grpTechID + "]";
	}
	
}
