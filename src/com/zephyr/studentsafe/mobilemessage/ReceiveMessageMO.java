package com.zephyr.studentsafe.mobilemessage;


import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.jasson.im.api.APIClient;
import com.jasson.im.api.MOItem;
import com.zephyr.studentsafe.bo.Constants;
import com.zephyr.studentsafe.dao.PrivateMessageDAO;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.studentsafe.util.SystemProperty;

/**
 * @author lenovo
 * 收MO短信 （手机回复）
 */
public class ReceiveMessageMO extends TimerTask{
	private final static Logger log = Logger.getLogger(ReceiveMessageMO.class);
	public void run() {
		PrivateMessageDAO dao = new PrivateMessageDAO() ;
		ISendMobileMessage sender = new SendMobileMessage() ;
		try {
			APIClient handler = MobileMessageHandler.getInstance();
			MOItem[] item = handler.receiveSM();
			if(item.length == 0)
			{
				log.info("无MO回执");
			}
			for (MOItem it : item){
				//保存到本地
				dao.receiveMO2Local(it);
				StringBuffer buffer = new StringBuffer();
				String content = new String(it.getContent().getBytes("iso8859-1"),"gbk") ;
				if ( content.equals(""))
				{
					continue ;
				}
				buffer.append("发送手机:" + it.getMobile() + "\n") ;
				buffer.append("内容:" + content + "\n");
				buffer.append("发送时间:" + it.getMoTime() + "\n");
				log.info(buffer.toString());
				if(SystemProperty.isReceiveMO())
				{
					sender.sendMessage(buffer.toString(), SystemProperty.getReceiveMOMobile()) ;
				}
			}
		} catch (StudentSafeException e) {
			// TODO Auto-generated catch block
			log.error(e);
			try {
				throw e;
			} catch (StudentSafeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
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
