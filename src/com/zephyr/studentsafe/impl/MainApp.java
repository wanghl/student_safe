/**
 * MainApp.java
 * com.zephyr.studentsafe.impl
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2010-9-9 		lenovo
 *
 * Copyright (c) 2010, TNT All Rights Reserved.
*/

package com.zephyr.studentsafe.impl;

import java.util.Calendar;
import java.util.Timer;

import javax.comm.PortInUseException;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.dao.HibernateUtil;

import com.zephyr.studentsafe.exception.StudentSafeException;

import com.zephyr.studentsafe.mobilemessage.MobileMessageHandler;
import com.zephyr.studentsafe.mobilemessage.ReceiveMessageRPT;
import com.zephyr.studentsafe.serialport.SerialReaderExt;
import com.zephyr.studentsafe.util.ThreadPoolManage;


/**
 * ClassName:MainApp
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lenovo
 * @version  
 * @since    Ver 1.1
 * @Date	 2010-9-9		下午08:27:21
 *
 * @see 	 
 */
public class MainApp {
	private final static Logger log = Logger.getLogger(MainApp.class);
	
	/**
	 * 程序启动必须初始化以下内容：
	 * 1、数据库连接
	 * 2、MAS连接
	 * 3、ProcessReceiveDataExt
	 * 4、收回执定时器
	 * @param argvs
	 * @throws PortInUseException 
	 * @throws StudentSafeException 
	 * @throws com.zephyr.sudentsafe.exception.StudentSafeException 
	 */
	public static void main(String[] argvs) throws PortInUseException, StudentSafeException, com.zephyr.sudentsafe.exception.StudentSafeException  {
		
		//初始化数据库连接
		HibernateUtil.getSession();
		//初始化发送短信连接
		MobileMessageHandler.getInstance();
		//ProcessReceiveDataExt类的功能是处理阅读器读到的标签号码。
		//因为是轮询所以放到独立的线程中执行，否则程序会卡在这里不继续执行
		ThreadPoolManage.getThreadPool().execute(new ProcessReceiveDataExt());
//		
//		//开始接收阅读器数据
		ThreadPoolManage.getThreadPool().execute(new SerialReaderExt());
		//启动接收回执线程
		ThreadPoolManage.getThreadPool().execute(new ReceiveMessageRPT());
		
		log.info("服务启动成功！");
	}

}

