/**
 * SendMobileMessage.java
 * com.zephyr.studentsafe.mobilemessage
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2010-9-8 		lenovo
 *
 * Copyright (c) 2010, TNT All Rights Reserved.
*/

package com.zephyr.studentsafe.mobilemessage;

import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import com.jasson.im.api.APIClient;
import com.zephyr.studentsafe.bo.Constants;
import com.zephyr.studentsafe.bo.Messagelog;
import com.zephyr.studentsafe.bo.SendMessageResult;
import com.zephyr.studentsafe.dao.BaseDAO;
import com.zephyr.studentsafe.exception.StudentSafeException;

/**
 * ClassName:SendMobileMessage
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lenovo
 * @version  
 * @since    Ver 1.1
 * @Date	 2010-9-8		下午07:19:59
 *
 * @see 	 
 */
public class SendMobileMessage implements ISendMobileMessage{
	private final static Logger log = Logger.getLogger(SendMobileMessage.class);

	@Override
	public synchronized void sendMessage(Map<String,String> message) {
		SendMessageResult sendResult = new SendMessageResult();
		Random  random = new Random();
		try{
			//初始化MAS 
			APIClient handler = MobileMessageHandler.getInstance();
			String phoneNumber = message.get("phoneNumber");
			String content =  message.get("message");
			long smID = random.nextInt(99999999);
			int result = handler.sendSM(phoneNumber,content, smID) ;
			
			if(result == APIClient.IMAPI_SUCC)
	        {            
				log.info("短信发送成功");
				sendResult.setMemo("等待回执");
	        	sendResult.setState(Constants.SEND_MESSAGE_WAIT_RPT);
	        	sendResult.setSmid(Long.toString(smID));
	        }
	        else if(result == APIClient.IMAPI_CONN_ERR){
	        	log.info("数据库连接失败");
	        	sendResult.setMemo("短信服务器数据库连接失败");
	        	sendResult.setState(Constants.SEND_MESSAGE_FAIL);
	        }
	        else if(result == APIClient.IMAPI_DATA_ERR){
	        	log.info("参数错误");
	        	sendResult.setMemo("参数错误");
	        	sendResult.setState(Constants.SEND_MESSAGE_FAIL);
	        }
	        else if(result == APIClient.IMAPI_DATA_TOOLONG){
	        	log.info("消息内容太长");
	        	sendResult.setMemo("消息内容太长");
	        	sendResult.setState(Constants.SEND_MESSAGE_FAIL);
	        }
	        else if(result == APIClient.IMAPI_INS_ERR){
	        	log.info("数据库插入错误");
	        	sendResult.setMemo("数据库插入错误");
	        	sendResult.setState(Constants.SEND_MESSAGE_FAIL);
	        }
	        else{
	        	log.info("出现其他错误,返回错误号:" + result);
	        	sendResult.setMemo("出现其他错误");
	        	sendResult.setState(Constants.SEND_MESSAGE_FAIL);
	        }
		}
		catch(StudentSafeException e){
			log.error(e.getMessageDetail());
			sendResult.setMemo(e.getMessageDetail());
        	sendResult.setState(Constants.SEND_MESSAGE_FAIL);
		}
		saveLog(sendResult,message);
	}

	@Override
	public void saveLog(SendMessageResult sendResult,Map<String,String> map) {
		//记录日志
		BaseDAO dao = new BaseDAO();
		Messagelog log = new Messagelog();
		log.setSmid(sendResult.getSmid());
		log.setMemo(sendResult.getMemo());
		log.setState(sendResult.getState());
		log.setMessageDesc(map.get("message").toString());
		log.setSendPhone(map.get("phoneNumber").toString());
		log.setMessageType(Integer.parseInt(map.get("messageType")));
		log.setStudentName(map.get("studentName"));
		log.setClassName(map.get("className"));
		log.setTeacher(map.get("teacher"));
		log.setRfidcardid(map.get("rfidcardid"));
		log.setSendTime(new Date());
		dao.saveORupdate(log);
		
	}
	

}

