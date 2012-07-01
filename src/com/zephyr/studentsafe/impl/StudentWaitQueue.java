package com.zephyr.studentsafe.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.zephyr.studentsafe.bo.Student;
import com.zephyr.studentsafe.bo.StudentExt;

public class StudentWaitQueue implements Job{

	/**
	 * 
	 */
	private static List<StudentExt> studentList = new ArrayList<StudentExt>();
	private static List<String> list = null;
	public  StudentWaitQueue() {

	}

	public static void put(StudentExt s) {
		studentList.add(s);
	}
	
	public static int getSize(){
		return studentList.size();
	}
	
	public static StudentExt get(int i){
		return studentList.get(i);
	}
	public static void pop(int i){
		studentList.remove(i);
	}

	// 查找给定卡号的学生信息
	public static StudentExt get(String rfidCardID) {
		StudentExt s = null;
		StudentExt tmp = null ;
		for (Iterator<StudentExt> it = studentList.iterator(); it.hasNext();) {
			tmp = null ;
			tmp = it.next();
			if (rfidCardID.equals(tmp.getRfidCardID())) {
				s = tmp;
				break;
			}
		}
		return s;
	}
	
	public static List<String> getRfidList(){
		list = null ;
		list = new ArrayList<String>();
		for(Iterator<StudentExt> it = studentList.iterator(); it.hasNext();){
			list.add(it.next().getRfidCardID());
		}
		return list ;
	}
	
	public static Iterator<StudentExt> iterator(){
		return studentList.iterator();
	}

	public static List<StudentExt> getList() {
		return studentList;
	}

	
	public static void pop(StudentExt s) {
		for (int i = 0; i < studentList.size(); i++) {
			if ( s.getRfidCardID().equals(studentList.get(i).getRfidCardID())){
				studentList.remove(i);
				break;
				
			}
		}
	}
	
	public static void clearMyself(){
		if ( !studentList.isEmpty()){
			for (int i = 0 ; i < studentList.size(); i++ ){
				studentList.remove(i);
			}
		}
		System.out.println(getSize());
	}

	public static void main(String[] argvs) throws ParseException, SchedulerException {
		StudentExt t = new StudentExt();
		put(t);
		JobDetail jobdw = new JobDetail("job1w","group1w",StudentWaitQueue.class);
		CronTrigger cronTriggerw = new CronTrigger("tr1w","tg1w");
		CronExpression cexpw = new CronExpression("40 32 22 ? * MON-FRI");
		cronTriggerw.setCronExpression(cexpw);
		SchedulerFactory sfw = new StdSchedulerFactory();
		Scheduler ttw = sfw.getScheduler();
		ttw.scheduleJob(jobdw, cronTriggerw);
		ttw.start() ;
		

	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		clearMyself();
		
	}

}
