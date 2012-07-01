/**
 * MainApp.java
 * com.zephyr.studentsafe.impl
 *
 * Function�� TODO 
 *
 *   ver     date      		author
 * ��������������������������������������������������������������������
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
 * @Date	 2010-9-9		����08:27:21
 *
 * @see 	 
 */
public class MainApp {
	private final static Logger log = Logger.getLogger(MainApp.class);
	
	/**
	 * �������������ʼ���������ݣ�
	 * 1�����ݿ�����
	 * 2��MAS����
	 * 3��ProcessReceiveDataExt
	 * 4���ջ�ִ��ʱ��
	 * @param argvs
	 * @throws PortInUseException 
	 * @throws StudentSafeException 
	 * @throws com.zephyr.sudentsafe.exception.StudentSafeException 
	 */
	public static void main(String[] argvs) throws PortInUseException, StudentSafeException, com.zephyr.sudentsafe.exception.StudentSafeException  {
		
		//��ʼ�����ݿ�����
		HibernateUtil.getSession();
		//��ʼ�����Ͷ�������
		MobileMessageHandler.getInstance();
		//ProcessReceiveDataExt��Ĺ����Ǵ����Ķ��������ı�ǩ���롣
		//��Ϊ����ѯ���Էŵ��������߳���ִ�У��������Ῠ�����ﲻ����ִ��
		ThreadPoolManage.getThreadPool().execute(new ProcessReceiveDataExt());
//		
//		//��ʼ�����Ķ�������
		ThreadPoolManage.getThreadPool().execute(new SerialReaderExt());
		//�������ջ�ִ�߳�
		ThreadPoolManage.getThreadPool().execute(new ReceiveMessageRPT());
		
		log.info("���������ɹ���");
	}

}

