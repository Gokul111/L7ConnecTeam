package com.l7.connecteam.excel.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.l7.connecteam.excel.dto.CriteriaExcel;
import com.l7.connecteam.excel.dto.MasterExcel;
import com.l7.connecteam.exception.UIException;

/**
 * Acts as validation layer for BulkUploadReader
 * @author soumya.raj 
 */
public class BulkUploadService {
	Logger logger = Logger.getLogger(BulkUploadService.class.getName());
	String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	int fileNumber = 0;
	Sheet nextSheet = null;
	List<String> noOfRowsRead = new ArrayList<String>();

	/**
	 * @param excelFilePath
	 * @return
	 * @throws UIException Critical validations for excel sheet checked
	 */
	public Iterator<Row> validateExcel(String excelFilePath) throws UIException {
		Workbook workbook = null;
		FileInputStream inputStream = null;
		try {
			fileNumber++;
			inputStream = new FileInputStream(new File(excelFilePath));
			if (excelFilePath.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(inputStream);
			} else if (excelFilePath.endsWith("xls")) {
				workbook = new HSSFWorkbook(inputStream);
			} else {
				throw new UIException("The specified file is not excel format");
			}
			Iterator<Sheet> iteratorSheet = workbook.iterator();
			while (iteratorSheet.hasNext()) {
				nextSheet = iteratorSheet.next();

				if (nextSheet.getLastRowNum() == 0 && nextSheet.getRow(0) == null) {
					throw new UIException("Empty excel file found among uploaded");
				}
				Iterator<Row> iteratorRow = nextSheet.iterator();
				Row firstRow = iteratorRow.next();
				Iterator<Cell> iteratorCellRow1 = firstRow.iterator();
				List<String> headers = new ArrayList<String>();
				while (iteratorCellRow1.hasNext()) {
					String header = iteratorCellRow1.next().getStringCellValue();
					if (!header.equals("")) {
						headers.add(header);
					}
				}
				Boolean ifValid = validateByHeader(excelFilePath, headers);
				if (!ifValid) {
					throw new UIException("Columns missing or header mismatch among uploaded files");
				}
				return iteratorRow;
			}
		} catch (IOException e) {
			logger.info(e.getMessage());
			throw new UIException("Missing required files among uploaded. Please upload again");
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
					inputStream.close();
				} catch (IOException e) {
					logger.info(e.getMessage());
				}
			}

		}
		return null;
	}

	/**
	 * @param excelFilePath
	 * @param headers
	 * @return Validates if uploaded excel headers are similar to required headers
	 */
	public boolean validateByHeader(String excelFilePath, List<String> headers) {
		boolean ifValid = true;
		List<String> actualHeaders = new ArrayList<String>();
		if (excelFilePath.endsWith("_Data.xlsx")) {
			actualHeaders = Arrays.asList("Emp ID", "Emp Name", "Batch", "Start Date", "End Date", "Training Group",
					"Assessment Name", "Assessment Type", "Assessment Technology", "Assessment Max Mrks",
					"Assessment Min Mrks", "Assessment Score", "Assessment Status");
			if (!actualHeaders.equals(headers) | headers.size() != 13) {
				ifValid = false;
			}
		}
		if (excelFilePath.endsWith("_Criteria.xlsx")) {
			actualHeaders = Arrays.asList("Criteria", "Max Score", "Min Score");
			if (!actualHeaders.equals(headers) | headers.size() != 3) {
				ifValid = false;
			}
		}
		return ifValid;
	}

	/**
	 * @param excelFilePath
	 * @return
	 * @throws UIException 
	 * Validates master excel and bridges master reader in
	 *                     BulkUploadReader
	 */
	public List<MasterExcel> validateMasterExcel(String excelFilePath) throws UIException {
		BulkUploadReader readerObj = new BulkUploadReader();
		List<MasterExcel> masterList = null;
		Iterator<Row> iteratorRow = validateExcel(excelFilePath);
		masterList = readerObj.readFromMasterExcel(iteratorRow);

		new File("E:\\Assessment upload survey").mkdir();
		String fileContent = "\n \n"+ timeStamp + "\t: "+ masterList.size() + " rows read out of total " + (nextSheet.getPhysicalNumberOfRows() - 1)
				+ " rows from master file\n";
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream("E:\\Assessment upload survey\\ExcelUploadInfo.txt", true);
			byte[] strToBytes = fileContent.getBytes();
			outputStream.write(strToBytes);
			outputStream.close();
		} catch (IOException e) {
			logger.info(e.getMessage());
			throw new UIException("Error writing ExcelUploadInfo.txt log file");
		}
		return masterList;
	}

	/**
	 * @param excelFilePath
	 * @param fileNumber
	 * @return
	 * @throws UIException Validates criteria excel and bridges criteria reader in
	 *                     BulkUploadReader
	 */
	public List<CriteriaExcel> validateCriteriaExcel(String excelFilePath, int fileNumber) throws UIException {
		BulkUploadReader readerObj = new BulkUploadReader();
		List<CriteriaExcel> criteriaList = null;
		Iterator<Row> iteratorRow = validateExcel(excelFilePath);
		criteriaList = readerObj.readFromCriteriaExcel(iteratorRow);
		String fileContent =  timeStamp + "\t: "+ criteriaList.size() + " rows read out of total "+ (nextSheet.getPhysicalNumberOfRows() - 1) + " rows from criteria file " + fileNumber+"\n";
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream("E:\\Assessment upload survey\\ExcelUploadInfo.txt", true);
			byte[] strToBytes = fileContent.getBytes();
			outputStream.write(strToBytes);
			outputStream.close();
		} catch (IOException e) {
			logger.info(e.getMessage());
			throw new UIException("Error writing ExcelUploadInfo.txt log file");
		}
		return criteriaList;
	}
}
