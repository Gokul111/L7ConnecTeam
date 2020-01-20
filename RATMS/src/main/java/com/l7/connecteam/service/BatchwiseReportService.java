package com.l7.connecteam.service;

import java.sql.SQLException;
import java.util.List;

import com.l7.connecteam.dto.BatchDto;
import com.l7.connecteam.dto.BatchwiseReportDto;
import com.l7.connecteam.exception.UIException;

public interface BatchwiseReportService {
	public List<BatchwiseReportDto> getBatchReport(BatchDto batchDataObj) throws UIException, SQLException;
}
