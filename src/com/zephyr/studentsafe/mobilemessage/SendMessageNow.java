package com.zephyr.studentsafe.mobilemessage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.CardException;
import com.zephyr.studentsafe.bo.Messagelog;
import com.zephyr.studentsafe.bo.StudentExt;
import com.zephyr.studentsafe.bo.Studentfamily;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.dao.StudentDAO;
import com.zephyr.studentsafe.impl.StudentQueue2DB;
import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.impl.StudentQueue2DB;
import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.studentsafe.bo.*;
/**
 * @author lenovo
 *发送短信
 */
public class SendMessageNow {
	private final static Logger log = Logger.getLogger(SendMessageNow.class);
	private StudentDAO dao = new StudentDAO();
	private ISendMobileMessage sender = new SendMobileMessage() ;
	public void sendMessage(StudentExt student){
		log.info("开始执行发送短信任务....");
		Studentrfid rfid = dao.getStudentbyCardID(student.getRfidCardID());
		Studentfamily family = null ;
		Map<String,String> map = new HashMap<String,String>();
		if ( ! rfid.getStudentFamily().isEmpty()){
			for(Iterator it = rfid.getStudentFamily().iterator(); it.hasNext();){
				family = (Studentfamily) it.next();
				try{
				map = StudentSafeUtil.makeMessageData(family, rfid.getStudentName(), Calendar.getInstance(), student.getEvent());
				if (student.getEvent().equals("入校")){
					map.put("messageType", Constants.MESSAGE_TYPE_IN_SCHOOLE);
				}else {
					map.put("messageType", Constants.MESSAGE_TYPE_OUT_SCHOOLE);
				}
				map.put("studentName", rfid.getStudentName());
				map.put("rfidcardid", rfid.getRfidCardID());
				map.put("className", rfid.getClassUID());
				map.put("teacher", rfid.getTeacherUID());
				sender.sendMessage(map);
				StudentQueue2DB.put(new StudentExt());
			
				}catch(StudentSafeException e){
					//发送失败
					Messagelog log = new Messagelog();
					log.setSendPhone(family.getFamilyPhone());
					log.setSendTime(Calendar.getInstance().getTime());
					log.setMemo(e.getLocalizedMessage());
					log.setState(0);
					log.setMessageType(Integer.parseInt(map.get("messageType")));
					log.setStudentName(map.get("studentName"));
					log.setClassName(map.get("className"));
					log.setTeacher(map.get("teacher"));
					log.setRfidcardid(map.get("rfidcardid"));
					dao.saveORupdate(log);
					continue ;
				}
			}
		}else {
			//未填写有效家长信息
			CardException e = new CardException();
			e.setEvent("短信发送");
			e.setCardid(rfid.getRfidCardID());
			e.setMemo("未填写家长信息，无法发送短信");
			e.setScanDate(Calendar.getInstance().getTime());
			dao.saveORupdate(e);
			log.error("卡号"+rfid.getRfidCardID()+"无家长信息!");
		}
	}

}
