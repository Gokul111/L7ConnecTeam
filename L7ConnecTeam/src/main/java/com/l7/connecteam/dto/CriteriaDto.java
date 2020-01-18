package com.l7.connecteam.dto;

/**
 * @author soumya.raj
 * Acts as data layer for criteria of an assessment
 */
public class CriteriaDto {
	private int criteriaID;
	private int grpTechID;
	private String criteriaName;
	private int criteriaMinMarks;
	private int criteriaMaxMarks;
	public int getCriteriaID() {
		return criteriaID;
	}
	public void setCriteriaID(int criteriaID) {
		this.criteriaID = criteriaID;
	}
	public int getGrpTechID() {
		return grpTechID;
	}
	public void setGrpTechID(int grpTechID) {
		this.grpTechID = grpTechID;
	}
	public String getCriteriaName() {
		return criteriaName;
	}
	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}
	public int getCriteriaMinMarks() {
		return criteriaMinMarks;
	}
	public void setCriteriaMinMarks(int criteriaMinMarks) {
		this.criteriaMinMarks = criteriaMinMarks;
	}
	public int getCriteriaMaxMarks() {
		return criteriaMaxMarks;
	}
	public void setCriteriaMaxMarks(int criteriaMaxMarks) {
		this.criteriaMaxMarks = criteriaMaxMarks;
	}
	@Override
	public String toString() {
		return "Criteria [criteriaID=" + criteriaID + ", grpTechID=" + grpTechID + ", criteriaName=" + criteriaName
				+ ", criteriaMinMarks=" + criteriaMinMarks + ", criteriaMaxMarks=" + criteriaMaxMarks + "]";
	}
	
	
}
