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

public class ProcessQueueData{
	private final static Logger log = Logger.getLogger(ProcessQueueData.class);
	private static StudentDAO dao = new StudentDAO();
	private static StudentTimeBookDAO tdao = new StudentTimeBookDAO();
	private static SendMessageNow sender = new SendMessageNow();
	static Studenttimebook timeBook = null;
	
	public  synchronized static void run(StudentExt s) {
		
		try{
			
			//�������뿪����ͬ������� ���������ݿ��¼
			if (s.getEvent() != "��У"&& s.getEvent() != "��У"){
				//StudentWaitQueue.put(s);
				
				//log.info("ѧ��" + s.getRfidCardID() + "�������뿪����ͬ������ȴ�����");
				log.info("ѧ��" + s.getRfidCardID() + "�������뿪����ͬ�����������ݿ��¼");
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
			 //���Ų�����
			if ( sid == null ){
				CardException e = new CardException();
				e.setEvent(s.getEvent());
				e.setCardid(s.getRfidCardID());
				e.setMemo("δ��ϵͳ��ע��!");
				e.setScanDate(Calendar.getInstance().getTime());
				dao.saveORupdate(e);
				log.error("����"+s.getRfidCardID()+"δ��ϵͳ��ע��!");
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
		//ˢ��ÿ���⵽��ʱ��
		Calendar c = Calendar.getInstance(); 
		int h = c.get(Calendar.HOUR_OF_DAY);
		System.out.println(h);
		//ÿ������8�� ����14�����ʦ���Ͱ༶���ڶ��š�
		//������У��8��֮ǰ��״̬��1 �����Ͽ�֮�䣨1���֮ǰ����У״̬��2
		//����8��ͳ�ƿ��ڵ�ʱ���ѯ����lastscanstate=1 �����ݣ�14��ͳ�Ƶ�ʱ���ѯ=2��
		if ( h < 8 && s.getEvent().equals("��У")){
			//8��֮ǰ������У��
			stud.setLastScanState(1 );
		}else if ( (h < 15 && h >= 8) && s.getEvent().equals("��У")){
			//8�㣨����8�㣩������2��֮�䵽У�� �������п����в����ǳٵ���)
			stud.setLastScanState(2);
			
		}else if (s.getEvent().equals("��У")){
			stud.setLastScanState(3);
		}
		stud.setLastScanDate(Calendar.getInstance().getTime());
		dao.saveORupdate(stud);
		//������
		sender.sendMessage(s);
		}
		catch (Exception e){
			log.error("������Ϊ" + s.getRfidCardID() + "��ѧ����Ϣʱ��������");
			log.error(e.getLocalizedMessage());
		}

		
	}

}
