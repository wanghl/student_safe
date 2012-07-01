package com.zephyr.studentsafe.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.CardException;
import com.zephyr.studentsafe.bo.Student;
import com.zephyr.studentsafe.bo.StudentExt;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.bo.Studenttimebook;
import com.zephyr.studentsafe.dao.StudentDAO;
import com.zephyr.studentsafe.dao.StudentTimeBookDAO;
import com.zephyr.studentsafe.mobilemessage.SendMessageNow;
import com.zephyr.studentsafe.util.StudentSafeUtil;

public class ProcessQueueDataExt{
	private final static Logger log = Logger.getLogger(ProcessQueueDataExt.class);
	private static StudentDAO dao = new StudentDAO();
	private static StudentTimeBookDAO tdao = new StudentTimeBookDAO();
	private static SendMessageNow sender = new SendMessageNow();
	static Studenttimebook timeBook = null;
	
	public  synchronized static void run(StudentExt s) {
		log.info("卡号" + s.getRfidCardID() + "进出点：" + s.getFristScanArea() + s.getLastScanArea());
		
		try{
		//由于阅读器灵敏度的问题，有可能存在某张卡只被某一探测区域检测到一次的情况。
		if(s.getLastScanArea() == null ){
			log.info("卡号为" + s.getRfidCardID() + "只被" + s.getFristScanArea() + "阅读器检测到一次，忽略。");
			return;
		}
		//进入点和离开点相同的情况下 不更新数据库记录
		if (s.getFristScanArea().equals(s.getLastScanArea()) || (!s.getFristScanArea().equals("A") && !s.getLastScanArea().equals("A"))){
			//StudentWaitQueue.put(s);
			
			//log.info("学生" + s.getRfidCardID() + "进入点和离开点相同，放入等待队列");
			log.info("学生" + s.getRfidCardID() + "进入点和离开点相同，不更新数据库记录");
			return ;
		}
		
		timeBook = tdao.getTimeBookByCardId(s.getRfidCardID());
		if (timeBook != null){
			
			//timeBook = new Studenttimebook();
			timeBook.setRfidCardID(s.getRfidCardID());
			timeBook.setEvent(s.getEvent());
			timeBook.setTime(Calendar.getInstance().getTime());
			//timeBook.setStudentName(dao.getStudentbyCardID(s.getRfidCardID()).getStudentName());
			dao.saveORupdate(timeBook);
		}else{
			Studentrfid sid = dao.getStudentbyCardID(s.getRfidCardID());
			 //卡号不存在
			if ( sid == null ){
				CardException e = new CardException();
				e.setEvent(s.getEvent());
				e.setCardid(s.getRfidCardID());
				e.setMemo("未在系统中注册!");
				e.setScanDate(Calendar.getInstance().getTime());
				dao.saveORupdate(e);
				log.error("卡号"+s.getRfidCardID()+"未在系统中注册!");
				return ;
			}
			timeBook = new Studenttimebook();
			timeBook.setRfidCardID(s.getRfidCardID());
			timeBook.setEvent(s.getEvent());
			timeBook.setTime(Calendar.getInstance().getTime());
			timeBook.setStudentName(sid.getStudentName());
			dao.saveORupdate(timeBook);
		}
		Studentrfid stud = dao.getStudentbyCardID(s.getRfidCardID());
		//刷新每天检测到的时间
		Calendar c = Calendar.getInstance(); 
		int h = c.get(Calendar.HOUR_OF_DAY);
		System.out.println(h);
		//每天早上8点 中午14点给老师发送班级考勤短信。
		//早上入校（8点之前）状态是1 下午上课之间（1点半之前）入校状态是2
		//这样8点统计考勤的时候查询当天lastscanstate=1 的数据，14点统计的时候查询=2的
		if ( h < 8 && s.getEvent().equals("入校")){
			//8点之前正常到校的
			stud.setLastScanState(1 );
		}else if ( (h < 15 && h >= 8) && s.getEvent().equals("入校")){
			//8点（包括8点）到下午2点之间到校的 （这其中可能有部分是迟到的)
			stud.setLastScanState(2);
			
		}else if (s.getEvent().equals("出校")){
			stud.setLastScanState(3);
		}
		stud.setLastScanDate(Calendar.getInstance().getTime());
		dao.saveORupdate(stud);
		log.info("卡号为" + s.getRfidCardID() + "的学生" + s.getEvent());
		//sender.sendMessage(s);
		}
		catch (Exception e){
			log.error("处理卡号为" + s.getRfidCardID() + "的学生信息时发生错误！");
			log.error(e.getLocalizedMessage());
		}

		
	}

	
public static void main(String[] argv){
	Calendar c = Calendar.getInstance(); 
	System.out.println(c.get(Calendar.HOUR_OF_DAY));
//	Timer t = new Timer();
//	t.schedule(new ProcessQueueDataExt(), 3*1000,2*1000);
}

}
