/**
 * ISendMobileMessage.java
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

import com.zephyr.studentsafe.bo.SendMessageResult;


/**
 * ClassName:ISendMobileMessage
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lenovo
 * @version  
 * @since    Ver 1.1
 * @Date	 2010-9-8		����07:19:00
 *
 * @see 	 
 */
public interface ISendMobileMessage {
	
	/**
	 * sendMessage:
	 * 
	 * @param  @param message
	 * @param  @return    �趨�ļ�
	 * @return int    DOM����
	 * @throws 
	 * @since  CodingExample��Ver 1.1
	*/
	public void sendMessage(Map<String,String> message);
	
	public void saveLog(SendMessageResult sendResult,Map<String,String> map);
	
	public void sendMessage(String content ,String mobile) ;

}

