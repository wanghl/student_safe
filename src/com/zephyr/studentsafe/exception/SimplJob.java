package com.zephyr.studentsafe.exception;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimplJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(arg0.getFireTime() + " " + arg0.getNextFireTime()) ;
		
		
	}

}
