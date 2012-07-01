package com.zephyr.sudentsafe.exception;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.zephyr.studentsafe.mobilemessage.SendMessage2Teacher;


public class T1 {

	
	public static void main(String[] argvs){
		try{
			JobDetail jobd = new JobDetail("job1","group1",SimplJob.class);
			CronTrigger cronTrigger = new CronTrigger("tr1","tg1");
			//周一到周五每天早上8点给班主任发送班级考勤情况
			CronExpression cexp = new CronExpression("0 0 8,14 * * ?");
			cronTrigger.setCronExpression(cexp);
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler tt = sf.getScheduler();
			tt.scheduleJob(jobd, cronTrigger);
			tt.start() ;
			
		}catch (Exception e ){
			e.printStackTrace();
		}
	}

}
