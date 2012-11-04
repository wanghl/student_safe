package com.zephyr.studentsafe.impl;

import java.util.Calendar;
import java.util.List;


import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.CardException;
import com.zephyr.studentsafe.bo.StudentExt;
import com.zephyr.studentsafe.bo.StudentProperty;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.bo.Studenttimebook;
import com.zephyr.studentsafe.dao.StudentDAO;
import com.zephyr.studentsafe.mobilemessage.SendMessageNow;

public class ProcessQueueData {
	private final static Logger log = Logger.getLogger(ProcessQueueData.class);
	private static StudentDAO dao = new StudentDAO();
	private static SendMessageNow sender = new SendMessageNow();
	static Studenttimebook timeBook = null;

	public synchronized static void run(StudentExt s) {

		try
		{

			// 进入点和离开点相同的情况下 不更新数据库记录
			if (s.getEvent() != "出校" && s.getEvent() != "入校")
			{
				// StudentWaitQueue.put(s);

				// log.info("学生" + s.getRfidCardID() + "进入点和离开点相同，放入等待队列");
				log.info("学生" + s.getRfidCardID() + "进入点和离开点相同，不更新数据库记录");
				return;
			}
			long x = System.currentTimeMillis();
			Studentrfid student = dao.getStudentbyCardID(s.getRfidCardID());
			long y = System.currentTimeMillis();
			System.out.println(x);
			System.out.println(y);
			System.out.println("------------------------>"+(y-x) % 1000);
			// 卡号不存在
			if (student == null)
			{
				CardException e = new CardException();
				e.setEvent(s.getEvent());
				e.setCardid(s.getRfidCardID());
				e.setMemo("未在系统中注册!");
				e.setScanDate(Calendar.getInstance().getTime());
				dao.saveORupdate(e);
				log.error("卡号" + s.getRfidCardID() + "未在系统中注册!");
				return;
			}
			timeBook = new Studenttimebook();
			timeBook.setRfidCardID(s.getRfidCardID());
			timeBook.setEvent(s.getEvent());
			timeBook.setTime(Calendar.getInstance().getTime());
			timeBook.setStudentName(student.getStudentName());
			dao.saveORupdate(timeBook);
			
			StudentProperty property = new StudentProperty();
			property.setLinkStudent(student.getStudentUID());
			List l  = dao.getByExample(StudentProperty.class, property) ;
			if(! l.isEmpty())
			{
				property =  (StudentProperty) l.get(0);
			}
			// 刷新每天检测到的时间
			Calendar c = Calendar.getInstance();
			int h = c.get(Calendar.HOUR_OF_DAY);
			System.out.println(h);
			// 每天早上8点 中午14点给老师发送班级考勤短信。
			// 早上入校（8点之前）状态是1 下午上课之间（1点半之前）入校状态是2
			// 这样8点统计考勤的时候查询当天lastscanstate=1 的数据，14点统计的时候查询=2的
			if (h < 8 && s.getEvent().equals("入校"))
			{
				// 8点之前正常到校的
				property.setLastScanState(1);
			} else if ((h < 15 && h >= 8) && s.getEvent().equals("入校"))
			{
				// 8点（包括8点）到下午2点之间到校的 （这其中可能有部分是迟到的)
				property.setLastScanState(2);

			} else if (s.getEvent().equals("出校"))
			{
				student.setLastScanState(3);
			}
			property.setLastScanDate(Calendar.getInstance().getTime());
			//dao.saveORupdate(student);
			dao.saveORupdate(property);
			// 发短信
			
			sender.sendMessage(student,s);
		} catch (Exception e)
		{
			log.error("处理卡号为" + s.getRfidCardID() + "的学生信息时发生错误！");
			log.error(e.getLocalizedMessage());
		}

	}

}
