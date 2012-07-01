package com.zephyr.studentsafe.ui;

import java.text.ParseException;

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

import com.zephyr.studentsafe.mobilemessage.SendMessage2Teacher;

public class FrameClear implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		MainFrame.clear("");
		
	}
	
	public static void main(String[] argvs) throws ParseException, SchedulerException{
		
	}

}
