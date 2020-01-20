package com.l7.connecteam.excel.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.l7.connecteam.controller.AssessmentController;
import com.l7.connecteam.controller.AssessmentTypeController;
import com.l7.connecteam.controller.BatchController;
import com.l7.connecteam.controller.CriteriaController;
import com.l7.connecteam.controller.TechnologyController;
import com.l7.connecteam.controller.TrainingGroupController;
import com.l7.connecteam.controller.UserController;
import com.l7.connecteam.controller.UserCriteriaController;
import com.l7.connecteam.dto.AssessmentDto;
import com.l7.connecteam.dto.AssessmentTypeDto;
import com.l7.connecteam.dto.BatchDto;
import com.l7.connecteam.dto.CriteriaDto;
import com.l7.connecteam.dto.TechnologyDto;
import com.l7.connecteam.dto.TrainingGroupDto;
import com.l7.connecteam.dto.UserDto;
import com.l7.connecteam.excel.dto.CriteriaExcel;
import com.l7.connecteam.excel.dto.MasterExcel;
import com.l7.connecteam.exception.UIException;
import com.l7.connecteam.utility.InputFileManager;

/**
 * Bridges data read from excel to respective DB Controllers
 * @author soumya.raj 
 */
public class BulkUploadProcessor {
	String excelFilePath = null;
	BulkUploadService serviceObj = new BulkUploadService();

	Logger logger = Logger.getLogger(BulkUploadReader.class.getName());

	UserController userObj = new UserController();
	BatchController batchObj = new BatchController();
	TrainingGroupController trainObj = new TrainingGroupController();
	AssessmentTypeController assessObj = new AssessmentTypeController();
	AssessmentController assessContObj = new AssessmentController();
	TechnologyController techObj = new TechnologyController();

	UserDto userDataObj;
	List<UserDto> userList = new ArrayList<UserDto>();

	BatchDto batchDataObj;
	List<BatchDto> batchList = new ArrayList<BatchDto>();

	TrainingGroupDto trainDataObj;
	List<TrainingGroupDto> trainList = new ArrayList<TrainingGroupDto>();

	AssessmentTypeDto assessTypeObj;
	List<AssessmentTypeDto> assessTypeList = new ArrayList<AssessmentTypeDto>();

	AssessmentDto assessDataObj;
	List<AssessmentDto> assessList = new ArrayList<AssessmentDto>();
	List<Integer> maxMarks = new ArrayList<Integer>();
	List<Integer> marks = new ArrayList<Integer>();
	List<String> status = new ArrayList<String>();

	TechnologyDto techDataObj;
	List<TechnologyDto> techList = new ArrayList<TechnologyDto>();
	List<TechnologyDto> criteriaTechList = new ArrayList<TechnologyDto>();
	List<Integer> minMarks = new ArrayList<Integer>();

	List<String> assessNames = new ArrayList<String>();

	/**
	 * @throws UIException
	 * @throws SQLException 
	 * Transfers data read from Master_Data.xlsx to DB
	 *                      controllers
	 */
	public void uploadMasterExcel() throws UIException, SQLException {
		String excelFilePath = "E:\\programs\\L7ConnecTeam\\Assessment Details\\"
				+ InputFileManager.getFileName("masterFile");
		List<MasterExcel> MasterList = serviceObj.validateMasterExcel(excelFilePath);
		for (MasterExcel mObj : MasterList) {
			userDataObj = new UserDto();
			userDataObj.setEmployeeId(mObj.getEmpId());
			userDataObj.setUsername(mObj.getEmpName());
			userList.add(userDataObj);

			batchDataObj = new BatchDto();
			batchDataObj.setBatchName(mObj.getBatchName());
			batchDataObj.setStartDate(mObj.getBatchStartDate());
			batchDataObj.setEndDate(mObj.getBatchEndDate());
			batchList.add(batchDataObj);

			trainDataObj = new TrainingGroupDto();
			trainDataObj.setTrainGroupName(mObj.getTrainingGroupName());
			trainDataObj.setTrainStartDate(mObj.getBatchStartDate());
			trainDataObj.setTrainEndDate(mObj.getBatchEndDate());
			trainList.add(trainDataObj);

			assessTypeObj = new AssessmentTypeDto();
			assessTypeObj.setAssessmentTypeName(mObj.getAssessmentType());
			assessTypeList.add(assessTypeObj);

			assessDataObj = new AssessmentDto();
			assessDataObj.setAssessmentName(mObj.getAssessmentName());
			if (!assessTypeObj.getAssessmentTypeName().equals("MCQ")) {
				if (!assessNames.contains(mObj.getAssessmentName())) {
					assessNames.add(mObj.getAssessmentName());
				}
			}
			maxMarks.add(mObj.getAssessment_maxMarks());
			marks.add(mObj.getAssessment_score());
			status.add(mObj.getAssessment_status());
			assessList.add(assessDataObj);

			techDataObj = new TechnologyDto();
			techDataObj.setTechnologyName(mObj.getTechnologyName());
			minMarks.add(mObj.getAssessment_minMarks());
			techList.add(techDataObj);
		}
		for (UserDto userDto : userList) {
			userDto = userObj.createUser(userDto);
		}
		Iterator<UserDto> userDto = userList.iterator();
		for (BatchDto batchDto : batchList) {
			batchDto = batchObj.createBatch(batchDto, userDto.next());
		}
		userDto = userList.iterator();
		for (TrainingGroupDto train : trainList) {
			train = trainObj.createTrainingGroup(train, userDto.next());
		}
		for (AssessmentTypeDto assess : assessTypeList) {
			assess = assessObj.createAssessmentType(assess);
		}
		userDto = userList.iterator();
		Iterator<AssessmentTypeDto> assessType = assessTypeList.iterator();
		Iterator<Integer> mark = marks.iterator();
		Iterator<Integer> maxMark = maxMarks.iterator();
		Iterator<String> statusIterate = status.iterator();
		for (AssessmentDto assessmentDto : assessList) {
			assessContObj.createAssessment(assessmentDto, userDto.next(), assessType.next(), mark.next(),
					statusIterate.next(), maxMark.next());
		}
		Iterator<TrainingGroupDto> trainIterator = trainList.iterator();
		Iterator<AssessmentDto> assess = assessList.iterator();
		maxMark = maxMarks.iterator();
		Iterator<Integer> minMark = minMarks.iterator();
		AssessmentDto assessData = new AssessmentDto();
		int grpID = 0;
		for (TechnologyDto tech : techList) {
			assessData = assess.next();
			tech = techObj.createTechnology(tech, trainIterator.next(), assessData, (int) maxMark.next(),
					(int) minMark.next());
			if (assessNames.contains(assessData.getAssessmentName())) {
				if (grpID != tech.getGrpTechID()) {
					grpID = tech.getGrpTechID();
					criteriaTechList.add(tech);
				}
			}
		}

		uploadCriteriaExcel(techList);
		uploadEvaluationExcel();

		userDto = userList.iterator();
		assessType = assessTypeList.iterator();
		mark = marks.iterator();
		maxMark = maxMarks.iterator();
		statusIterate = status.iterator();
		for (AssessmentDto assessmentDto : assessList) {
			assessContObj.setAssessUserRel(assessmentDto, userDto.next(), assessType.next(), mark.next(),
					statusIterate.next(), maxMark.next());
		}

	}

	/**
	 * @param techList
	 * @throws SQLException
	 * @throws UIException  Transfers data read from *_Criteria.xlsx to DB
	 *                      controllers
	 * @throws SQLException
	 */
	public void uploadCriteriaExcel(List<TechnologyDto> techList) throws UIException, SQLException {
		Iterator<TechnologyDto> techIterator = criteriaTechList.iterator();

		while (techIterator.hasNext()) {
			int fileNumber = 1;
			for (String assessment : assessNames) {
				TechnologyDto criteriaTech = techIterator.next();
				excelFilePath = "E:\\programs\\L7ConnecTeam\\Assessment Details\\" + assessment.trim().replace(" ", "")
						+ InputFileManager.getFileName("criteriaFile");
				List<CriteriaExcel> criteriaList = serviceObj.validateCriteriaExcel(excelFilePath, fileNumber);
				fileNumber++;
				CriteriaController criteriaConObj = new CriteriaController();
				for (CriteriaExcel cObj : criteriaList) {
					CriteriaDto criteriaObj = new CriteriaDto();
					criteriaObj.setCriteriaName(cObj.getCriteriaName());
					criteriaObj.setCriteriaMaxMarks(cObj.getCriteria_maxscore());
					criteriaObj.setCriteriaMinMarks(cObj.getCriteria_minscore());
					criteriaObj = criteriaConObj.createCriteria(criteriaObj, criteriaTech);
				}

			}
		}

	}

	/**
	 * @throws IOException
	 * @throws UIException  Transfers data read from *_Evaluation.xlsx to DB
	 *                      controllers
	 * @throws SQLException
	 */
	public void uploadEvaluationExcel() throws UIException, SQLException {
		UserCriteriaController userCriteriaObj = new UserCriteriaController();
		int j = 0;
		for (String assessment : assessNames) {
			Map<String, Integer> criteriaMap = new HashMap<String, Integer>();
			Workbook workbook = null;
			FileInputStream inputStream = null;
			Sheet nextSheet = null;
			try {
				excelFilePath = "E:\\programs\\L7ConnecTeam\\Assessment Details\\" + assessment.trim().replace(" ", "")
						+ InputFileManager.getFileName("evaluationFile");
				inputStream = new FileInputStream(new File(excelFilePath));
				if (excelFilePath.endsWith("xlsx")) {
					workbook = new XSSFWorkbook(inputStream);
				} else if (excelFilePath.endsWith("xls")) {
					workbook = new HSSFWorkbook(inputStream);
				} else {
					throw new UIException("An evalution file is not in excel format");
				}
				Iterator<Sheet> iteratorSheet = workbook.iterator();
				while (iteratorSheet.hasNext()) {
					nextSheet = iteratorSheet.next();
					if (nextSheet.getLastRowNum() == 0 && nextSheet.getRow(0) == null) {
						throw new UIException("Empty evaluation file found among uploaded");
					}
					Iterator<Row> iteratorRow = nextSheet.iterator();
					Row firstRow = iteratorRow.next();
					Iterator<Cell> iteratorCellFirst = firstRow.iterator();
					List<String> headers = new ArrayList<String>();
					while (iteratorCellFirst.hasNext()) {
						headers.add(iteratorCellFirst.next().getStringCellValue());
					}
					while (iteratorRow.hasNext()) {
						Row nextRow = iteratorRow.next();
						Iterator<Cell> iteratorCell = nextRow.iterator();
						Iterator<String> headerIterator = headers.iterator();
						String empID = iteratorCell.next().getStringCellValue();
						headerIterator.next();
						while (iteratorCell.hasNext()) {
							Cell cell = iteratorCell.next();
							CellType type = cell.getCellType();
							if (type == CellType.NUMERIC && type != CellType.BLANK && cell != null) {
								int evalMark = (int) cell.getNumericCellValue();
								criteriaMap.put(headerIterator.next(), evalMark);
							}
						}
						if ((headers.size() - 1) != criteriaMap.size()) {
							String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
							String fileContent = timeStamp + "\t : Value mismatch in " + assessment
									+ " evaluation file. Assessment marks not entered. \n";
							FileOutputStream outputStream = new FileOutputStream(
									"E:\\Assessment upload survey\\ExcelUploadInfo.txt", true);
							byte[] strToBytes = fileContent.getBytes();
							outputStream.write(strToBytes);
							outputStream.close();
							throw new UIException(
									"Upload incomplete. Please check ExcelUploadInfo.txt in E:/Assessment upload survey folder for more info");
						}
						userCriteriaObj.createUserCriteriaRel(empID, criteriaMap, assessment);
					}
					j = j + 1;
				}

			} catch (FileNotFoundException e) {
				logger.info(e.getMessage());
				throw new UIException("Evaluation file missing. Please upload again");
			} catch (IOException e) {
				logger.info(e.getMessage());
				throw new UIException("Upload Action failed. Try again");
			} finally {
				if (workbook != null) {
					try {
						workbook.close();
						inputStream.close();
					} catch (IOException e) {
						logger.info(e.getMessage());
						throw new UIException("Upload Action failed. Try again");

					}

				}

			}

		}
	}

}
