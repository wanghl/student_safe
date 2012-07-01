package com.zephyr.studentsafe.dao;


import java.util.Calendar;

import org.apache.log4j.Logger;

/**
 * @author lenovo
 *
 */
public class DatabaseBackup {
	
	private static final Logger log = Logger.getLogger(DatabaseBackup.class);
	/**
	 * 以周为单位轮询覆盖备份
	 * @param strings
	 */
	public static void main(String...strings ){
		String fileName = "d:/studentsafe-" + Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + ".sql";
		String sqlCommand = "cmd /c mysqldump  -uroot -p1 --add-drop-table studentsafe > " + fileName;
		try {
			Runtime.getRuntime().exec(sqlCommand) ;
			log.info("mysql数据库备份完成");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
