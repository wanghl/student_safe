package com.zephyr.studentsafe.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.CardException;
import com.zephyr.studentsafe.bo.Constants;
import com.zephyr.studentsafe.bo.StudentExt;
import com.zephyr.studentsafe.bo.StudentProperty;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.bo.Studenttimebook;
import com.zephyr.studentsafe.dao.StudentDAO;
import com.zephyr.studentsafe.dao.StudentTimeBookDAO;
import com.zephyr.studentsafe.mobilemessage.SendMessageNow;
import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.studentsafe.util.SystemProperty;

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
				//log.info("学生" + s.getRfidCardID() + "进入点和离开点相同，不更新数据库记录");
				return;
			}
			Studentrfid student = dao.getStudentbyCardID(s.getRfidCardID());
			
			// 发短信
			// 判断是否启用了重复短信过滤功能
			if (SystemProperty.isMessageFilterEnabled())
			{
				//先拿到这个学生最近一次的进出校记录
				StudentTimeBookDAO d = new StudentTimeBookDAO();
				Studenttimebook tbk = d.getMaxMessageSendTime(s.getRfidCardID());
				//setup1 如果拿到的studenttimbeook是空，直接发短信。
				if(tbk == null)
				{
					saveStudentTimeBook(s,student);
					sender.sendMessage(student, s);
					return ;
				}
				//setup2 如果本次进出方向和上次不同，直接发短信
				if (! tbk.getEvent().equals(s.getEvent()))
				{
					saveStudentTimeBook(s,student);
					sender.sendMessage(student, s);
				}else 
				{
					// 判断上次发送短信的时间 距离现在是否大于设定的过滤时间
					// 过滤在一段时间内，重复发送相同进出校短信。
					//如果学生在校内外触发器的区域内长时间停留或走动有可能会出现这种情况
					//所以，在设定的时间段内， 将相同的进出校记录屏蔽不发。
					//*************我是牢骚分割线 *******************
					// 这项目到底能做到什么地步。。。老子有点儿扛不住了 。
					// 不过已经这份儿了 咬牙撑着
					//妈了个逼的，当男人真难啊。。。 
					if (isSendMessage(tbk.getTime(), Calendar.getInstance().getTime()))
					{
						saveStudentTimeBook(s,student);
						sender.sendMessage(student, s);
					} else
					{
						log.info("卡号为" + s.getRfidCardID() + "的学生重复进出，过滤短信");
						saveStudentTimeBook(s,student);
					}
					
				}
			} else
			{
				//没有启用短信过滤 ，直接发短息
				saveStudentTimeBook(s,student);
				sender.sendMessage(student, s);
			}
			
		} catch (Exception e)
		{
			log.error("处理卡号为" + s.getRfidCardID() + "的学生信息时发生错误！");
			log.error(e);
		}

	}

	private static boolean isSendMessage(Date lastDate, Date now) throws ParseException {

		Locale loc = new Locale("zh", "CN");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", loc);

		String max = sdf.format(now);
		String min = sdf.format(lastDate);
		Date maxd = sdf.parse(max);
		Date mind = sdf.parse(min);

		long between = (maxd.getTime() - mind.getTime()) / 1000;
		long checkTime = SystemProperty.getMessageBetweenTime();
		long day1 = between / (24 * 3600);
		long hour1 = between % (24 * 3600) / 3600;
		long minute1 = between % 3600 / 60;
		if (day1 > 0)
		{
			return true;
		} else if (hour1 > 0)
		{
			return true;

		} else
		{
			return minute1 >= checkTime;
		}

	}

	private static void saveStudentTimeBook(StudentExt s, Studentrfid student) throws Exception {

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

	}

	public static void main(String[] argvs) throws ParseException {
		Locale loc = new Locale("zh", "CN");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", loc);
		ProcessQueueData p = new ProcessQueueData();
		Date d = sdf.parse("2012-12-03 19:20:00");
		Date ds = sdf.parse("2012-12-03 16:49:00");
		try
		{
			System.out.println(p.isSendMessage(d,Calendar.getInstance().getTime()));
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
