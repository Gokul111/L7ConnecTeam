
package com.l7.connecteam.utility;

import java.util.logging.Logger;


/**
 * @author soumya.raj
 * Moves the contents uploaded to another static location for audit purposes
 */
public class MoveDirectory {
	Logger logger = Logger.getLogger(MoveDirectory.class.getName());

	/**
	 * Method to move a directory and rename it to current timestamp
	 */
	public void archiveUpload() {
		/*
		 * String source = "E:\\programs\\L7ConnecTeam\\Assessment Details"; File
		 * sourceDir = new File(source); DateTime now = DateTime.now(DateTimeZone.UTC);
		 * String output = now.toString().replace(":", "-"); String destination =
		 * "E:\\Assessment upload survey\\" +output; File targetDir = new
		 * File(destination); try { FileUtils.moveDirectory(sourceDir, targetDir); }
		 * catch (IOException e) { logger.info(e.getMessage()); }
		 */}
}
