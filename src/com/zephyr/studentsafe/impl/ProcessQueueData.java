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

			// �������뿪����ͬ������� ���������ݿ��¼
			if (s.getEvent() != "��У" && s.getEvent() != "��У")
			{
				//log.info("ѧ��" + s.getRfidCardID() + "�������뿪����ͬ�����������ݿ��¼");
				return;
			}
			Studentrfid student = dao.getStudentbyCardID(s.getRfidCardID());
			
			// ������
			// �ж��Ƿ��������ظ����Ź��˹���
			if (SystemProperty.isMessageFilterEnabled())
			{
				//���õ����ѧ�����һ�εĽ���У��¼
				StudentTimeBookDAO d = new StudentTimeBookDAO();
				Studenttimebook tbk = d.getMaxMessageSendTime(s.getRfidCardID());
				//setup1 ����õ���studenttimbeook�ǿգ�ֱ�ӷ����š�
				if(tbk == null)
				{
					saveStudentTimeBook(s,student);
					sender.sendMessage(student, s);
					return ;
				}
				//setup2 ������ν���������ϴβ�ͬ��ֱ�ӷ�����
				if (! tbk.getEvent().equals(s.getEvent()))
				{
					saveStudentTimeBook(s,student);
					sender.sendMessage(student, s);
				}else 
				{
					// �ж��ϴη��Ͷ��ŵ�ʱ�� ���������Ƿ�����趨�Ĺ���ʱ��
					// ������һ��ʱ���ڣ��ظ�������ͬ����У���š�
					//���ѧ����У���ⴥ�����������ڳ�ʱ��ͣ�����߶��п��ܻ�����������
					//���ԣ����趨��ʱ����ڣ� ����ͬ�Ľ���У��¼���β�����
					//*************������ɧ�ָ��� *******************
					// ����Ŀ����������ʲô�ز������������е������ס�� ��
					// �����Ѿ���ݶ��� ҧ������
					//���˸��Ƶģ����������Ѱ������� 
					if (isSendMessage(tbk.getTime(), Calendar.getInstance().getTime()))
					{
						saveStudentTimeBook(s,student);
						sender.sendMessage(student, s);
					} else
					{
						log.info("����Ϊ" + s.getRfidCardID() + "��ѧ���ظ����������˶���");
						saveStudentTimeBook(s,student);
					}
					
				}
			} else
			{
				//û�����ö��Ź��� ��ֱ�ӷ���Ϣ
				saveStudentTimeBook(s,student);
				sender.sendMessage(student, s);
			}
			
		} catch (Exception e)
		{
			log.error("������Ϊ" + s.getRfidCardID() + "��ѧ����Ϣʱ��������");
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

		// ���Ų�����
		if (student == null)
		{
			CardException e = new CardException();
			e.setEvent(s.getEvent());
			e.setCardid(s.getRfidCardID());
			e.setMemo("δ��ϵͳ��ע��!");
			e.setScanDate(Calendar.getInstance().getTime());
			dao.saveORupdate(e);
			log.error("����" + s.getRfidCardID() + "δ��ϵͳ��ע��!");
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
