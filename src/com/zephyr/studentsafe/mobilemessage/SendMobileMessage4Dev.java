/**
 * SendMobileMessage4Dev.java
 * com.zephyr.studentsafe.mobilemessage
 *
 * Function�� TODO 
 *
 *   ver     date      		author
 * ��������������������������������������������������������������������
 *   		 2010-9-8 		lenovo
 *
 * Copyright (c) 2010, TNT All Rights Reserved.
*/

package com.zephyr.studentsafe.mobilemessage;

import java.util.Map;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.SendMessageResult;


/**
 * ClassName:SendMobileMessage4Dev
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lenovo
 * @version  
 * @since    Ver 1.1
 * @Date	 2010-9-8		����07:22:26
 *
 * @see 	 
 */
public class SendMobileMessage4Dev implements ISendMobileMessage{
	private final static Logger log = Logger.getLogger(SendMobileMessage4Dev.class);

	@Override
	public void sendMessage(Map<String, String> message) {
		// TODO Auto-generated method stub
	}

	@Override
	public void saveLog(SendMessageResult sendResult,Map<String,String> map){
		// TODO Auto-generated method stub
		
	}

	//@Override
//	public int sendMessage(Map message) {
//		
//		log.info("��ʼ���Ͷ���Ϣ....");
//		log.info("���룺" + message.get("phoneNumber"));
//		log.info("��Ϣ����: " + message.get("message"));
//		log.info("��Ϣ���ͳɹ���");
//		return 1;
//		
//	}

	
}

