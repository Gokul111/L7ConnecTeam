package com.l7.connecteam.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import com.l7.connecteam.exception.UIException;

/**
 * @author soumya.raj Reads query.properties file and returns sql string based
 *         on specified key
 */
public class QueryManager {
	static Logger logger = Logger.getLogger(QueryManager.class.getName());

	public String getQuery(String queryKey) throws UIException {
		String sql = null;
		/*
		 * File file = new File("com\\l7\\connecteam\\utility\\Query.properties");
		 * FileInputStream inStream;
		 */
		InputStream inStream = getClass().getClassLoader()
				.getResourceAsStream("com\\l7\\connecteam\\utility\\Query.properties");

		try {
			if (inStream != null) {
				Properties properties = new Properties();
				properties.load(inStream);
				sql = properties.getProperty(queryKey);
			} else {

				throw new UIException("Something went wrong" );
			}
		} catch (FileNotFoundException e) {
			logger.info("QueryManager:" + e.getMessage());
			throw new UIException("Could not retrieve the query string from file", e);
		} catch (IOException e) {
			logger.info("QueryManager" + e.getMessage());
			throw new UIException("Could not retrieve the query");
		}
		return sql;
	}
}
