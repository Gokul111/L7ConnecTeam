package com.l7.connecteam.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import com.l7.connecteam.daoImpl.AssessmentDaoImpl;

public class InputFileManager {
static Logger logger = Logger.getLogger(AssessmentDaoImpl.class.getName());
	
	public static String getFileName(String fileNameKey) {
		String fileName = null;
		File file = new File("E:\\programs\\L7ConnecTeam\\src\\main\\java\\com\\l7\\connecteam\\utility\\InputFile.properties");
		FileInputStream inStream;
		try {
			inStream = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(inStream);
			fileName = properties.getProperty(fileNameKey);
		} catch (FileNotFoundException e) {
			logger.info(e.getMessage());
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
		return fileName;
	}
}
