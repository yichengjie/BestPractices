package com.yicj.log4j;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LoggerUtils {
	/**
	 * 创建Logger实例
	 * 
	 * @param clazz     事件日志发生类
	 * @param ifConsole 是否输出到控制台
	 * @param ifFile    是否输出到文件
	 * @param logFile   日志文件地址（路径分割使用“/”）
	 * @param ifLocate  是否定位事件日志发生位置（类.方法 line）
	 * @return Logger
	 */
	public static Logger getLogger(Class<?> clazz, boolean ifConsole, boolean ifFile, String logFile,
			boolean ifLocate) {

		if (ifConsole == false && ifFile == false)
			return null;

		if (ifFile == true && logFile.trim().length() < 1)
			return null;

		String conversionPattern;
		if (ifLocate == true) {
			// 日志内容形式如：2014-03-11 01:34:45.572 [DEBUG]
			// com.aliyun.qa.utils.LoggerUtilsTest.main(Line:15): 测试DEBUG日志
			conversionPattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%-5p] %c.%M(Line:%L): %m %n";
		} else {
			// 日志内容形式如：2014-03-11 01:34:45.572 [DEBUG] : 测试DEBUG日志
			conversionPattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%-5p] : %m %n";
		}
		PatternLayout layout = new PatternLayout();
		layout.setConversionPattern(conversionPattern);

		Logger logger = Logger.getLogger(clazz);
		logger.removeAllAppenders();
		logger.setLevel(Level.DEBUG);
		logger.setAdditivity(false); // Logger不会在父Logger的appender里输出，默认为true

		if (ifConsole == true) { // 日志输出到控制台
			ConsoleAppender consoleAppender = new ConsoleAppender();
			consoleAppender.setLayout(layout);
			consoleAppender.setThreshold(Level.INFO); // ConsoleAppender日志级别为DEBUG
			consoleAppender.activateOptions();
			logger.addAppender(consoleAppender);
		}

		if (ifFile == true) { // 日志输出到文件
			FileAppender fileAppender = new FileAppender();
			fileAppender.setLayout(layout);
			fileAppender.setFile(logFile);
			fileAppender.setEncoding("UTF-8");
			fileAppender.setAppend(true);
			fileAppender.setThreshold(Level.INFO); // FileAppender日志级别为INFO
			fileAppender.activateOptions();
			logger.addAppender(fileAppender);
		}

		return logger;
	}

	/**
	 * 创建Logger实例（仅输出到控制台）
	 * 
	 * @param clazz    事件日志发生类
	 * @param ifLocate 是否定位事件日志发生位置（类.方法 line）
	 * @return Logger
	 */
	public static Logger getLogger(Class<?> clazz, boolean ifLocate) {
		return getLogger(clazz, true, false, null, ifLocate);
	}

	/**
	 * 创建Logger实例（仅输出到文件）
	 * 
	 * @param clazz    事件日志发生类
	 * @param logFile  日志文件地址（路径分割使用“/”）
	 * @param ifLocate 是否定位事件日志发生位置（类.方法 line）
	 * @return Logger
	 */
	public static Logger getLogger(Class<?> clazz, String logFile, boolean ifLocate) {
		return getLogger(clazz, false, true, logFile, ifLocate);
	}

}
