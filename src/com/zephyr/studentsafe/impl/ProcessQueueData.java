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

			// �������뿪����ͬ������� ���������ݿ��¼
			if (s.getEvent() != "��У" && s.getEvent() != "��У")
			{
				// StudentWaitQueue.put(s);

				// log.info("ѧ��" + s.getRfidCardID() + "�������뿪����ͬ������ȴ�����");
				log.info("ѧ��" + s.getRfidCardID() + "�������뿪����ͬ�����������ݿ��¼");
				return;
			}
			long x = System.currentTimeMillis();
			Studentrfid student = dao.getStudentbyCardID(s.getRfidCardID());
			long y = System.currentTimeMillis();
			System.out.println(x);
			System.out.println(y);
			System.out.println("------------------------>"+(y-x) % 1000);
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
			
			StudentProperty property = new StudentProperty();
			property.setLinkStudent(student.getStudentUID());
			List l  = dao.getByExample(StudentProperty.class, property) ;
			if(! l.isEmpty())
			{
				property =  (StudentProperty) l.get(0);
			}
			// ˢ��ÿ���⵽��ʱ��
			Calendar c = Calendar.getInstance();
			int h = c.get(Calendar.HOUR_OF_DAY);
			System.out.println(h);
			// ÿ������8�� ����14�����ʦ���Ͱ༶���ڶ��š�
			// ������У��8��֮ǰ��״̬��1 �����Ͽ�֮�䣨1���֮ǰ����У״̬��2
			// ����8��ͳ�ƿ��ڵ�ʱ���ѯ����lastscanstate=1 �����ݣ�14��ͳ�Ƶ�ʱ���ѯ=2��
			if (h < 8 && s.getEvent().equals("��У"))
			{
				// 8��֮ǰ������У��
				property.setLastScanState(1);
			} else if ((h < 15 && h >= 8) && s.getEvent().equals("��У"))
			{
				// 8�㣨����8�㣩������2��֮�䵽У�� �������п����в����ǳٵ���)
				property.setLastScanState(2);

			} else if (s.getEvent().equals("��У"))
			{
				student.setLastScanState(3);
			}
			property.setLastScanDate(Calendar.getInstance().getTime());
			//dao.saveORupdate(student);
			dao.saveORupdate(property);
			// ������
			
			sender.sendMessage(student,s);
		} catch (Exception e)
		{
			log.error("������Ϊ" + s.getRfidCardID() + "��ѧ����Ϣʱ��������");
			log.error(e.getLocalizedMessage());
		}

	}

}
