package com.zephyr.studentsafe.mobilemessage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
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

import com.zephyr.studentsafe.bo.ClassInfo;
import com.zephyr.studentsafe.bo.Constants;
import com.zephyr.studentsafe.bo.Schooleinfor;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.dao.ClassInfoDAO;
import com.zephyr.studentsafe.dao.StudentDAO;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.util.StudentSafeUtil;

/**
 * @author lenovo 在工作日给班主任老师发送班级考勤 。
 */
public class SendMessage2Teacher implements Job{
	private Logger log = Logger.getLogger(SendMessage2Teacher.class);
	ISendMobileMessage sender = new SendMobileMessage();
	StudentDAO dao = new StudentDAO();
	ClassInfoDAO cd = new ClassInfoDAO();
	Date curdate = null ;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(arg0.getNextFireTime());
		List l = dao.getObjList(ClassInfo.class);
		List<Studentrfid> s = new ArrayList<Studentrfid>();
		List<Studentrfid> cl = new ArrayList<Studentrfid>();
		int studentCount;
		int currentCount;
		int sum = 0; 
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer buffer = new StringBuffer();
		try {
			for (Iterator it = l.iterator(); it.hasNext();) {
				ClassInfo c = (ClassInfo) it.next();
				s = dao.getStudentAttendance(c.getClassUID(), true);
				// 当前该班级到校学生数量
				currentCount = s.size();
				cl = dao.getStudentbyClassUID(c.getClassUID());
				if (cl != null) {
					// 该班级学生总数
					studentCount = cl.size();
					// 全勤，直接发短信
					if (studentCount == currentCount) {
						map.put("phoneNumber", cd.getTeacherMobile(c
								.getTeacher()));
						map.put("message", cd.getTeacherName(c.getTeacher())
								+ "老师您好!" + StudentSafeUtil.getCurrentDateFormat() + "考勤情况:" + c.getClassName() + "应到"
								+ studentCount + "人实到" + studentCount
								+ "人，所有学生均已到校！");
						sender.sendMessage(map);
					} else {
						s = dao.getStudentAttendance(c.getClassUID(), false);
						String str = "";
						for (Iterator<Studentrfid> its = s.iterator(); its
								.hasNext();) {
							str += its.next().getStudentName() + ",";
						}
						map.put("teacherName", cd
								.getTeacherName(c.getTeacher()));
						map.put("className", c.getClassName());
						map.put("studentCount", Integer.toString(studentCount));
						map.put("currentCount", Integer.toString(currentCount));
						map.put("studentList", str);
						map.put("date", StudentSafeUtil.getCurrentDateFormat());
						Schooleinfor sch = (Schooleinfor) dao.get(Schooleinfor.class);
						String msgCount = StudentSafeUtil.makeMessage(sch.getMsgModel1(), map);
						Map<String,String> sendMap = new HashMap<String,String>();
						sendMap.put("phoneNumber", cd.getTeacherMobile(c.getTeacher()));
						sendMap.put("message", msgCount);
						sendMap.put("messageType", Constants.MESSAGE_TYPE_KQ) ;
						sendMap.put("className", s.get(0).getClassInfo().getClassUID()) ;
						sendMap.put("teacher",s.get(0).getClassInfo().getTeacher()) ;
						sender.sendMessage(sendMap) ;
					//	buffer.append(msgCount + "\n\n");
					//	sum += currentCount;
					}

				}
			}
			//SendMail.execute(buffer.toString(),sum);
		} catch (StudentSafeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] argvs) throws ParseException, SchedulerException{
		JobDetail jobd = new JobDetail("job1","group1",SendMessage2Teacher.class);
		CronTrigger cronTrigger = new CronTrigger("tr1","tg1");
		CronExpression cexp = new CronExpression("50 32 10  ? * *");
		cronTrigger.setCronExpression(cexp);
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler tt = sf.getScheduler();
		tt.scheduleJob(jobd, cronTrigger);
		tt.start() ;
		
	}

}
