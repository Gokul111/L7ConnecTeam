package com.l7.connecteam.excel.dto;

/**
 * @author soumya.raj 
 * This class acts as DTO for *_criteria.xlsx files
 */
public class CriteriaExcel {
	private String criteriaName;
	private int criteria_maxscore;
	private int criteria_minscore;
	private int count;

	/**
	 * @return Returns an occurrence of constructed criteria name
	 */
	public String getCriteriaName() {
		return criteriaName;
	}

	/**
	 * @param criteriaName 
	 * Constructs a criteria name
	 */
	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}

	/**
	 * @return Returns maximum score for a criteria
	 */
	public int getCriteria_maxscore() {
		return criteria_maxscore;
	}

	/**
	 * @param criteria_maxscore 
	 * Sets the maximum score for a criteria
	 */
	public void setCriteria_maxscore(int criteria_maxscore) {
		this.criteria_maxscore = criteria_maxscore;
	}

	/**
	 * @return Returns minimum score of a criteria
	 */
	public int getCriteria_minscore() {
		return criteria_minscore;
	}

	/**
	 * @param criteria_minscore 
	 * Sets the minimum score for a criteria
	 */
	public void setCriteria_minscore(int criteria_minscore) {
		this.criteria_minscore = criteria_minscore;
	}
	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Prints the content of an instance of this class
	 */
	@Override
	public String toString() {
		return "CriteriaExcel [criteriaName=" + criteriaName + ", criteria_maxscore=" + criteria_maxscore
				+ ", criteria_minscore=" + criteria_minscore + "]";
	}

}
