package com.l7.connecteam.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author soumya.raj
 * Reads query.properties file and returns sql string based on specified key
 */
public class QueryManager {
	static Logger logger = Logger.getLogger(QueryManager.class.getName());
	
	public static String getQuery(String queryKey) {
		String sql = null;
		File file = new File("E:\\programs\\L7ConnecTeam\\src\\main\\java\\com\\l7\\connecteam\\utility\\Query.properties");
		FileInputStream inStream;
		try {
			inStream = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(inStream);
			sql = properties.getProperty(queryKey);
		} catch (FileNotFoundException e) {
			logger.info(e.getMessage());
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
		return sql;
	}
}
