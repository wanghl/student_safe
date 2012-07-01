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

import com.zephyr.studentsafe.bo.Student;
import com.zephyr.studentsafe.bo.Studenttimebook;
import com.zephyr.studentsafe.dao.StudentDAO;
import com.zephyr.studentsafe.util.StudentSafeUtil;

public class ProcessQueueData extends TimerTask {

//	public void run() {
//		Student s = null ;
//		Studenttimebook tbk = null ;
//		StudentDAO dao = new StudentDAO();
//		List<Student> list = StudentQueue.getList();
//		for (Iterator it = list.iterator(); it.hasNext();){
//			s = (Student) it.next();
//			tbk.setInSchooleName(s.getInSchooleTime().getTime());
//			tbk.setOutSchooleName(s.getOutSchooleTime().getTime());
//			tbk.setRfidCardID(s.getCardID());
//			tbk.setSendMessage(0);
//			tbk.setStudentName(dao.getStudentbyCardID(s.getCardID()).getStudentName());
//			dao.saveORupdate(tbk);
//			StudentQueue.pop(s);
//		}
//		
//	}

	
public static void main(String[] argv){
	Timer t = new Timer();
	t.schedule(new ProcessQueueData(), 3*1000,2*1000);
}

@Override
public void run() {
	// TODO Auto-generated method stub
	
}

}
