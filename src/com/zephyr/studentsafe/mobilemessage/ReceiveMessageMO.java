package com.zephyr.studentsafe.mobilemessage;

import java.io.UnsupportedEncodingException;
import java.util.TimerTask;

import com.jasson.im.api.APIClient;
import com.jasson.im.api.MOItem;
import com.zephyr.studentsafe.dao.MessageLogDAO;
import com.zephyr.studentsafe.dao.PrivateMessageDAO;

import com.zephyr.sudentsafe.exception.StudentSafeException;


/**
 * @author lenovo
 * 收MO短信 （手机回复）
 */
public class ReceiveMessageMO {

	public void run() {
		PrivateMessageDAO dao = new PrivateMessageDAO() ;
		try {
			APIClient handler = MobileMessageHandler.getInstance();
			MOItem[] item = handler.receiveSM();
			for (MOItem it : item){
				dao.receiveMO2Local(it);
			}
		} catch (StudentSafeException e) {
			// TODO Auto-generated catch block
			try {
				throw e;
			} catch (StudentSafeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				throw  e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		
	}
	
	public static void main(String[] argvs){
		ReceiveMessageMO mo = new ReceiveMessageMO();
		mo.run();
	}

}
