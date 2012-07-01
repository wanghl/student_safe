/**
 * SendMessageTask.java
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

package com.zephyr.studentsafe.mobilemessage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.Messagelog;
import com.zephyr.studentsafe.bo.Schooleinfor;
import com.zephyr.studentsafe.bo.SendMessageResult;
import com.zephyr.studentsafe.bo.Student;
import com.zephyr.studentsafe.bo.Studentfamily;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.bo.Studenttimebook;
import com.zephyr.studentsafe.dao.StudentDAO;
import com.zephyr.studentsafe.util.StudentSafeUtil;

/**
 * ClassName:SendMessageTask Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 * 
 * @author lenovo
 * @version
 * @since Ver 1.1
 * @Date 2010-9-9 上午10:51:39
 * 
 * @see
 */
public class SendMessageTask  {

	private final static Logger log = Logger.getLogger(SendMessageTask.class);

	private static String carid = null;
	private static StudentDAO dao = new StudentDAO();
	private static ISendMobileMessage msgSender = new SendMobileMessage4Dev();

	@SuppressWarnings("unchecked")
	public  static void startSendMessage() {

		log.info("开始执行发送短信任务....");
		Studentrfid studentRfid = null;
		Student student = null ;
		SendMessageResult sendResult;
		Studenttimebook timebook = null;
		List list = dao.getReadyForSendMessage();
		for (Iterator it = list.iterator(); it.hasNext();) {
			try {
				
				Object o =  it.next();
				if ( o instanceof Studenttimebook ){
					timebook = (Studenttimebook) o;
				}
				studentRfid = dao.getStudentbyCardID(timebook.getRfidCardID());
				if (studentRfid != null && studentRfid.getStudentFamily() != null) {
					//学生和家长是一对多的关系。
					for (Iterator fits = studentRfid.getStudentFamily().iterator(); fits
							.hasNext();) {
						//组发短信map
						//Map map = StudentSafeUtil.makeMessageData((Studentfamily) fits.next(),studentRfid.getStudentName(), Calendar.getInstance(),timebook.getEvent());
						//发送短信
						//msgSender.sendMessage(map);
						
					}
					
				} else {
					log.error("发送短消息失败！请检查卡号" + carid
							+ "是否存在或是否设置对应学生的家长联系电话。");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getLocalizedMessage());
				//continue;
			}
		}//}

	}

	
	public static void main(String[] argvs) throws IOException {
		SendMessageTask t = new SendMessageTask();
		t.startSendMessage();
	}

	

}
