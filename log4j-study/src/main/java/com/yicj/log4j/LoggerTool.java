package com.yicj.log4j;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;

public class LoggerTool {

	private Logger logger;
	
	private String logDir = "E:\\data\\log\\myaap1" ;

	public LoggerTool(String filepath) {
		PropertyConfigurator.configureAndWatch("/log4j.properties");
		logger = Logger.getLogger(filepath);
		logger.removeAllAppenders();
		logger.setAdditivity(false);
		//appender
		DailyRollingFileAppender appender = new DailyRollingFileAppender();
		appender.setFile(logDir + "/" + filepath + "/chat.log");
		appender.setDatePattern("'.'yyyy-MM-dd");
		PatternLayout layout = new PatternLayout("%-d{yyyy-MM-dd HH:mm:ss}  %m%n");
		appender.setLayout(layout);
		appender.setAppend(true);
		appender.activateOptions();
		logger.addAppender(appender);
	}

	public Logger getLogger() {
		return logger;
	}
}
