package com.l7.connecteam.excel.reader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import com.l7.connecteam.excel.dto.CriteriaExcel;
import com.l7.connecteam.excel.dto.MasterExcel;
import com.l7.connecteam.exception.UIException;

/**
 * Reads the uploaded excel files
 * @author soumya.raj
 */
public class BulkUploadReader {
	BulkUploadService validObj = new BulkUploadService();
	
	Logger logger = Logger.getLogger(BulkUploadReader.class.getName());
	String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	/**
	 * @param iteratorRow
	 * @return
	 * @throws UIException
	 * Reads uploaded master excel file validating each cell
	 */
	public List<MasterExcel> readFromMasterExcel(Iterator<Row> iteratorRow) throws UIException {
		List<MasterExcel> MasterList = new ArrayList<MasterExcel>();
		try {
			while (iteratorRow.hasNext()) {
				Row nextRow = iteratorRow.next();
				Iterator<Cell> iteratorCell = nextRow.iterator();
				MasterExcel MasterObj = new MasterExcel();
				while (iteratorCell.hasNext()) {
					Boolean recordValid = true;
					int cellNo = 0;
					Cell cell = iteratorCell.next();
					CellType type = cell.getCellType();
					if (type == CellType.STRING && cell != null) {
						MasterObj.setEmpId(cell.getStringCellValue().trim());
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					
					cell = iteratorCell.next();
					type = cell.getCellType();
					if (type == CellType.STRING  && cell != null) {
						MasterObj.setEmpName(cell.getStringCellValue().trim());
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					
					cell = iteratorCell.next();
					type = cell.getCellType();
					if (type == CellType.STRING && cell != null) {
						MasterObj.setBatchName(cell.getStringCellValue().trim());
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					
					cell = iteratorCell.next();
					type = cell.getCellType();
					if (type == CellType.NUMERIC && cell != null) {
						java.sql.Date sqlDate = new java.sql.Date(cell.getDateCellValue().getTime());
						MasterObj.setBatchStartDate(sqlDate);
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					cell = iteratorCell.next();
					type = cell.getCellType();
					if (type == CellType.NUMERIC && cell != null) {
						java.sql.Date sqlDate = new java.sql.Date(cell.getDateCellValue().getTime());
						MasterObj.setBatchEndDate(sqlDate);
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					cell = iteratorCell.next();
					type = cell.getCellType();
					if (type == CellType.STRING && cell != null) {
						MasterObj.setTrainingGroupName(cell.getStringCellValue().trim());
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					cell = iteratorCell.next();
					type = cell.getCellType();
					if (type == CellType.STRING && cell != null) {
						MasterObj.setAssessmentName(cell.getStringCellValue().trim());
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					cell = iteratorCell.next();
					type = cell.getCellType();
					if (type == CellType.STRING && cell != null) {
						MasterObj.setAssessmentType(cell.getStringCellValue().trim());
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					cell = iteratorCell.next();
					type = cell.getCellType();
					if (type == CellType.STRING && cell != null) {
						MasterObj.setTechnologyName(cell.getStringCellValue().trim());
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					cell = iteratorCell.next();
					type = cell.getCellType();
					if (type == CellType.NUMERIC && cell != null) {
						MasterObj.setAssessment_maxMarks((int) cell.getNumericCellValue());
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					cell = iteratorCell.next();
					type = cell.getCellType();
					if (type == CellType.NUMERIC && cell != null) {
						MasterObj.setAssessment_minMarks((int) cell.getNumericCellValue());
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					cell = iteratorCell.next();
					type = cell.getCellType();
					if (type == CellType.NUMERIC && cell != null) {
						MasterObj.setAssessment_score((int) cell.getNumericCellValue());
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					cell = iteratorCell.next();
					type = cell.getCellType();
					if (type == CellType.STRING && cell != null) {
						MasterObj.setAssessment_status(cell.getStringCellValue().trim());
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					if (recordValid == false) {
						String fileContent=timeStamp+"\t : Record value mismatch in master file at row "+nextRow.getRowNum()+" cell "+cellNo+". \n";
						FileOutputStream outputStream = new FileOutputStream("E:\\Assessment upload survey\\ExcelUploadInfo.txt",true);
					    byte[] strToBytes = fileContent.getBytes();
					    outputStream.write(strToBytes);
					    outputStream.close();
					}
					else {
					MasterList.add(MasterObj);
					}
				}
			}
		} catch (NoSuchElementException e) {
			logger.info(e.getMessage());
			throw new UIException("Manipulated Excel sheet");
		} catch (IOException e) {
			logger.info(e.getMessage());
			throw new UIException("Error writing ExcelUploadInfo.txt log file");
		} 
		return MasterList;
	}
	
	/**
	 * @param iteratorRow
	 * @return
	 * @throws UIException
	 * Reads uploaded criteria excel files validating each cell
	 */
	public List<CriteriaExcel> readFromCriteriaExcel(Iterator<Row> iteratorRow) throws UIException {
		List<CriteriaExcel> criteriaList = new ArrayList<CriteriaExcel>();
		try {
			while (iteratorRow.hasNext()) {
				Row nextRow = iteratorRow.next();
				Iterator<Cell> iteratorCell = nextRow.iterator();
				CriteriaExcel criteriaObj = new CriteriaExcel();
				while (iteratorCell.hasNext()) {
					Boolean recordValid = true;
					int cellNo = 0;
					Cell cell = iteratorCell.next();
					CellType type = cell.getCellType();
					if (type == CellType.STRING && cell != null) {
						criteriaObj.setCriteriaName(cell.getStringCellValue().trim());
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					cell = iteratorCell.next();
					type = cell.getCellType();
					if (type == CellType.NUMERIC && cell != null) {
						criteriaObj.setCriteria_maxscore((int)cell.getNumericCellValue());
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					cell = iteratorCell.next();
					type = cell.getCellType();
					if (type == CellType.NUMERIC && cell != null) {
						criteriaObj.setCriteria_minscore((int)cell.getNumericCellValue());
					} else {
						recordValid = false;
						cellNo = cell.getColumnIndex() + 1;
					}
					if (recordValid == false) {
						String fileContent=timeStamp+"\t : Record value mismatch in criteria file at row "+nextRow.getRowNum()+" cell "+cellNo+". \n";
						FileOutputStream outputStream = new FileOutputStream("E:\\Assessment upload survey\\ExcelUploadInfo.txt",true);
					    byte[] strToBytes = fileContent.getBytes();
					    outputStream.write(strToBytes);
					    outputStream.close();
					}
					else {
					criteriaList.add(criteriaObj);
					}
				}
			}
//		} catch (IllegalStateException e) {
//			logger.info(e.getMessage());
//			throw new UIException("Manipulated Excel sheet");
		} catch (IOException e) {
			logger.info(e.getMessage());
			throw new UIException("Upload complete. Log file writing failed.");
		}
		return criteriaList; 
	}
}
