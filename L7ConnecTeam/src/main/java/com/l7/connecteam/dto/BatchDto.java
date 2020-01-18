package com.l7.connecteam.dto;

import java.sql.Date;

/**
 * @author soumya.raj
 * Data layer for batch
 *
 */
public class BatchDto {
	private int batchID;
	private String batchName;
	private Date startDate;
	private Date endDate;
	private String batchDesc;
	public int getBatchID() {
		return batchID;
	}
	public void setBatchID(int batch_id) {
		this.batchID = batch_id;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batch_name) {
		this.batchName = batch_name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date start_date) {
		this.startDate = start_date;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date end_date) {
		this.endDate = end_date;
	}
	public String getBatchDesc() {
		return batchDesc;
	}
	public void setBatchDesc(String batch_Desc) {
		batchDesc = batch_Desc;
	}
	@Override
	public String toString() {
		return "batch [batchID=" + batchID + ", batchName=" + batchName + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", batchDesc=" + batchDesc + "]";
	}
}
