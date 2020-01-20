package com.l7.connecteam.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import com.l7.connecteam.exception.DBDownException;

/**
 * @author soumya.raj 
 * Acts as a manager for creating and closing a connection
 *         instance
 */
public class ConnectionManager {
	Logger logger = Logger.getLogger(ConnectionManager.class.getName());
	private static final String CONNECTION_FILE = "com\\l7\\connecteam\\manager\\Connection_Dev.properties";
	
	private static String DBUrl=null;
	private static String userName=null;
	private static String password=null;
	private static Connection connection = null;

	/**
	 * Method to create and return a connection
	 * @return
	 * @throws DBDownException
	 */
	public Connection getConnection() throws DBDownException {
		setDatabaseParameters(CONNECTION_FILE);
		try {
			connection = DriverManager.getConnection(DBUrl, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			throw new DBDownException("Connection temporarily unavailable");
		}
		return connection;
	}

	/**
	 * Method to close a connection 
	 * @param connection
	 * @throws DBDownException
	 */
	public void closeConnection(Connection connection) throws DBDownException {
		try {
			connection.close();
		} catch (SQLException e) {
			logger.info(e.getMessage());
			throw new DBDownException("Connection temporarily unavailable");
		}
	}

	/**
	 * Method to set all DB parameters from properties file required to get connection
	 * @param connectionFile
	 * @throws DBDownException
	 */
	private void setDatabaseParameters(String connectionFile) throws DBDownException {
		try {
			InputStream inStream  = getClass().getClassLoader().getResourceAsStream(connectionFile);
			Properties connectionProperties = new Properties();
			connectionProperties.load(inStream);
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				logger.info(e.getMessage());
				throw new DBDownException("Connection temporarily unavailable");
			}
			DBUrl= "jdbc:mysql://localhost/" + connectionProperties.getProperty("dbName");
			userName = connectionProperties.getProperty("userName");
			password = connectionProperties.getProperty("password");
		} catch (FileNotFoundException e) {
			logger.info(e.getMessage());
			throw new DBDownException("Connection temporarily unavailable");
		} catch (IOException e) {
			logger.info(e.getMessage());
			throw new DBDownException("Connection temporarily unavailable");
		}
	}

}
