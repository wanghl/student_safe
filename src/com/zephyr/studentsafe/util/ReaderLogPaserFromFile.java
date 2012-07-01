package com.zephyr.studentsafe.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.dao.ReaderDebugDAO;
import com.zephyr.studentsafe.ui.MainFrame;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ReaderLogPaserFromFile implements Job {
	private Logger log = Logger.getLogger(ReaderLogPaserFromFile.class);
	public List resultlist = new ArrayList();
	public Map resultmap = new HashMap();
	public List list  ;
	public Map map ;
	public String tmp  ;
	public StringBuffer sb = new StringBuffer();
	
	
	public ReaderLogPaserFromFile() {
		
	}
	
	
	public void pase(){
		
		String content = MainFrame.getJTextArea().getText() ;
		
		try {
			//String str = null ;
			String[] p = null; 
			int evtflag =  0 ;
			Calendar c = Calendar.getInstance(); 
			int h = c.get(Calendar.HOUR_OF_DAY);
			if (h == 8){
				
				resultmap.put("event", "入校(早上上学)");
				evtflag = 1; 
			}
			if (h == 15){
				resultmap.put("event", "入校(下午上学)");
				evtflag = 2; 
			}
			if (h == 19){
				resultmap.put("event", "出校(下午放学)");
				evtflag  = 3 ;
			}
			
			ReaderDebugDAO dao = new ReaderDebugDAO();
			List<Studentrfid> l = dao.getExceptionDate(evtflag ) ;
			System.out.println(l.size());
			boolean flag ;
			StringBuffer bf;
			Set set = new HashSet();
			int i =0 ;
			String[] array = content.split("\r");
			for (Studentrfid s : l){
				//FileInputStream  in = new FileInputStream(file);
				//BufferedReader bin = new BufferedReader(new InputStreamReader(in));
				flag =  false ;
				list = new ArrayList();
				map = new HashMap<String,List> ();
				bf =  new StringBuffer ();
			for ( String str : array) {
				
				try{
			//	System.out.println(s.getRfidCardID());
				if ( str.indexOf(s.getRfidCardID()) > 0 ){
					
					p = str.split(" INFO - ");
					if (  p[1].substring(1, 2).equals("A") ||  p[1].substring(1, 2).equals("B") ||  p[1].substring(1, 2).equals("C")){
						bf.append(p[0]);
						bf.append("\t卡号" + s.getRfidCardID() + "读头 : " + p[1].substring(1, 2) + "\n");
						list.add(p[0] + "\t卡号" + s.getRfidCardID() + "读头 : " + p[1].substring(1, 2) + "\n");
					}else if (p[1].indexOf("进出点") > 0) {
						tmp = p[1];
						bf.append(p[0]);
						bf.append(p[1] + "\n");
						list.add(p[0] + p[1] + "\n");
					}else {
						bf.append(p[0]);
						bf.append(p[1] + "\n");
						list.add(p[0] + p[1] + "\n");
					}
					
					flag  =  true ;
				}
				
				}catch(Exception e){
					System.out.println(str);
				}
			}
			if (flag){
				i ++ ;
				
				//map.put("list", list);
				map.put("rfid", s.getRfidCardID());
				//map.put("result", tmp);
				pase(tmp,map);
				map.put("detail", list);
				resultlist.add( map);
				bf.append("==================================================================\n");
				sb.append(bf.toString());
				bf = null ;
			}
			//bin.close();
			//in.close();
			}
//			FileOutputStream out = new FileOutputStream("d:/result5.txt");
//			out.write(sb.toString().getBytes());
//			out.close();
		//	System.out.println(bf.toString());
			System.out.println(i);
			resultmap.put("rowcount", i);
			resultmap.put("datamodel", resultlist);
			resultmap.put("date", StudentSafeUtil.getCurrentDateAllformated());
			
			process(resultmap);
			SendMail.execute("c.html");
		} catch (JobExecutionException e) {
			log.error(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void pase(String str ,Map map){
		String s = str.split("：")[1] ;
		String tmp = s.substring(0,s.length() -1); 
		map.put("result", tmp);
	}
	
	public  void process(Map resultmap){
		try {
			StringWriter sw = new StringWriter();
			Configuration cfg = new Configuration();
			//cfg.setSharedVariable("datamodel", list);
			Template temp;
				temp = cfg.getTemplate("resource/model.ftl", "gbk");
			
			temp.process(resultmap, sw);
			FileOutputStream out = new FileOutputStream(new File("c.html"));
			out.write(sw.toString().getBytes("UTF-8"));
			out.close();
			//System.out.println(sw.toString());
		} catch (IOException e) {
			log.error(e.getLocalizedMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			log.error(e.getLocalizedMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String...strings) throws ParseException, SchedulerException, IOException{
		FileInputStream in = new FileInputStream("d:/33.txt");
		byte[] b = new byte[in.available()];
		in.read(b);
		//String[] s = new String(b).split("\n");
//		JobDetail jobd = new JobDetail("job1","group1",ReaderLogPaserFromFile.class);
//		CronTrigger cronTrigger = new CronTrigger("tr1","tg1");
//		CronExpression cexp = new CronExpression("0 0 8,15 ? * MON-FRI");
//		cronTrigger.setCronExpression(cexp);
//		SchedulerFactory sf = new StdSchedulerFactory();
//		Scheduler tt = sf.getScheduler();
//		tt.scheduleJob(jobd, cronTrigger);
//		tt.start() ;
//		ReaderLogPaserFromFile fp = new ReaderLogPaserFromFile(new String(b));
//		fp.pase() ;
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//String content = arg0.getJobDetail().getJobDataMap().get("content").toString();
		pase();
		
	}

}
