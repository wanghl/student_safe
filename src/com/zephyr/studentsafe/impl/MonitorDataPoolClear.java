package com.zephyr.studentsafe.impl;

import java.text.ParseException;
import java.util.Iterator;
import java.util.Set;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

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

import com.zephyr.studentsafe.bo.MonitorData;
import com.zephyr.studentsafe.bo.StudentExt;


public class MonitorDataPoolClear implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		Set set = MonitorDataPool.getKeySet();
		MonitorData data = null ;
		for(Iterator it = set.iterator() ;it.hasNext(); )
		{
			data = MonitorDataPool.getMonitorData(it.next().toString());
			data.clear();
		}
	}
	
	public static void main(String[] argvs) throws ParseException, SchedulerException{
		MonitorData d = new MonitorData();
		MonitorDataPool.put("127.0.0.1", d);
		
		
		StudentExt ss = new StudentExt();
		ss.setRfidCardID("132868"); 
		ss.setRemoteIp("127.0.0.1");
		ss.setFirstArea("12");
		
		MonitorDataPool.addMonitorData(ss); 
		
		JobDetail j = new JobDetail("clear", "group2", MonitorDataPoolClear.class);
		CronTrigger c = new CronTrigger("t1", "t1");
		CronExpression ce = new CronExpression("0 21 12,23 * * ?");
		c.setCronExpression(ce);
		SchedulerFactory s = new StdSchedulerFactory();
		Scheduler t = s.getScheduler();
		t.scheduleJob(j, c);
		t.start();
		
	}

}
