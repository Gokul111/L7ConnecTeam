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
 * 
 * @author soumya.raj
 */
public class BulkUploadService {
	Logger logger = Logger.getLogger(BulkUploadService.class.getName());
	Sheet nextSheet = null;

	/**
	 * @param excelFilePath
	 * @return
	 * @throws UIException Critical validations for excel sheet checked
	 */
	public Iterator<Row> validateExcel(String excelFilePath) throws UIException {
		Workbook workbook = null;

		Sheet nextSheet = null;
		try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));) {

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
				boolean isValid = validateByHeader(excelFilePath, headers);
				if (!isValid) {
					throw new UIException("Columns missing or header mismatch among uploaded files");
				}
				return iteratorRow;
			}
		} catch (IOException e) {
			logger.info(e.getMessage());
			throw new UIException("Missing required files among uploaded. Please upload again", e);
		} finally {
			if (workbook != null) {
				try {
					workbook.close();

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
	 * @throws UIException Validates master excel and bridges master reader in
	 *                     BulkUploadReader
	 */
	public List<MasterExcel> validateMasterExcel(String excelFilePath) throws UIException {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		BulkUploadReader readerObj = new BulkUploadReader();
		List<MasterExcel> masterList = null;
		Iterator<Row> iteratorRow = validateExcel(excelFilePath);
		masterList = readerObj.readFromMasterExcel(iteratorRow);
		validateMasterExcelData();
		new File("assessmentUploadArchivePath").mkdir();
		String fileContent = "\n \n" + timeStamp + "\t: " + masterList.size() + " rows read out of total "
				+ (nextSheet.getPhysicalNumberOfRows() - 1) + " rows from master file\n";

		try (FileOutputStream outputStream = new FileOutputStream("assementUploadLogFileName", true);) {
			byte[] strToBytes = fileContent.getBytes();
			outputStream.write(strToBytes);

		} catch (IOException e) {
			logger.info(e.getMessage());
			throw new UIException("Error writing ExcelUploadInfo.txt log file", e);
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
	public List<CriteriaExcel> validateCriteriaExcel(String excelFilePath, int fileNumber, String timeStamp)
			throws UIException {
		BulkUploadReader readerObj = new BulkUploadReader();

		List<CriteriaExcel> criteriaList = null;
		Iterator<Row> iteratorRow = validateExcel(excelFilePath);
		criteriaList = readerObj.readFromCriteriaExcel(iteratorRow);
		validateCriteriaExcelData();
		String fileContent = timeStamp + "\t: " + criteriaList.size() + " rows read out of total "
				+ (nextSheet.getPhysicalNumberOfRows() - 1) + " rows from criteria file " + fileNumber + "\n";

		try (FileOutputStream outputStream = new FileOutputStream("assementUploadLogFileName", true);) {

			byte[] strToBytes = fileContent.getBytes();
			outputStream.write(strToBytes);

		} catch (IOException e) {
			logger.info(e.getMessage());
			throw new UIException("Error writing ExcelUploadInfo.txt log file", e);
		}
		return criteriaList;
	}

	public void validateMasterExcelData() throws UIException {
		MasterExcel master = new MasterExcel();

		if (least(master.getBatchStartDate(), master.getBatchEndDate()).equals(master.getBatchEndDate())) {
			throw new UIException(
					"Start date greater than end date  master_data.xlsx at row number " + master.getRowNumber());

		}
		if (Integer.toString((int) master.getAssessment_maxMarks()) == null || master.getAssessment_maxMarks() <= 0) {

			throw new UIException(
					"Assessment Max marks is invalid and minimum should be greater than 0 in  master_data.xlsx at row number "
							+ master.getRowNumber());

		}
		if (Integer.toString((int) master.getAssessment_minMarks()) == null || master.getAssessment_minMarks() <= 0) {

			throw new UIException(
					"Assessment Min marks is invalid and minimum should be greater than 0 in  master_data.xlsx at row number "
							+ master.getRowNumber());

		}
		if (Integer.toString((int) master.getAssessment_score()) == null || master.getAssessment_score() < 0) {

			throw new UIException(
					"Assessment score is invalid and minimum should be greater than or equal to 0 in  master_data.xlsx at row number "
							+ master.getRowNumber());

		}
		if (master.getAssessment_score() > master.getAssessment_maxMarks()) {

			throw new UIException("Assessment score is greater than max score in master_data.xlsx at row number "
					+ master.getRowNumber());

		}
		if (master.getAssessment_minMarks() > master.getAssessment_maxMarks()) {

			throw new UIException(
					"Assessment minimum score is greater than max score in master_data.xlsx at row number "
							+ master.getRowNumber());

		}

	}

	public void validateCriteriaExcelData() throws UIException {
		CriteriaExcel criteriaObj = new CriteriaExcel();

		if (criteriaObj.getCriteriaName().trim() != null
				&& criteriaObj.getCriteriaName().trim().toLowerCase().equals("penalty")) {

			if (Integer.toString((int) criteriaObj.getCriteria_maxscore()) == null
					|| criteriaObj.getCriteria_maxscore() > 0) {

				throw new UIException("Criteria max score is should be lower in at row number " + criteriaObj.getCount()
						+ " for criteria " + criteriaObj.getCriteriaName().trim());

			}
			if (Integer.toString((int) criteriaObj.getCriteria_minscore()) == null
					|| criteriaObj.getCriteria_minscore() > 0) {

				throw new UIException("Criteria min score is should be lower in at row number " + criteriaObj.getCount()
						+ " for criteria" + criteriaObj.getCriteriaName().trim());

			}

			if (criteriaObj.getCriteria_minscore() > criteriaObj.getCriteria_maxscore()) {

				throw new UIException("Criteria min score should not be greater than max score at row number "
						+ criteriaObj.getCount());

			}

		} else {

			if (Integer.toString((int) criteriaObj.getCriteria_maxscore()) == null
					|| criteriaObj.getCriteria_maxscore() <= 0) {

				throw new UIException("Criteria max Score is invalid in at row number " + criteriaObj.getCount()
						+ " for criteria " + criteriaObj.getCriteriaName().trim());

			}
			if (Integer.toString((int) criteriaObj.getCriteria_minscore()) == null) {

				throw new UIException("Criteria min score is invalid in at row number " + criteriaObj.getCount()
						+ " for criteria " + criteriaObj.getCriteriaName().trim());

			}

			if (criteriaObj.getCriteria_minscore() > criteriaObj.getCriteria_maxscore()) {

				throw new UIException("Criteria min score should not be greater than max score at row number "
						+ criteriaObj.getCount());

			}

		}
	}

	public static Date least(Date a, Date b) {
		return a == null ? b : (b == null ? a : (a.before(b) ? a : b));
	}
}
