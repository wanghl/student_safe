package com.zephyr.studentsafe.util;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zephyr.studentsafe.dao.ReaderDebugDAO;


public class SendMail  {
	private static Logger log = Logger.getLogger(SendMail.class);
	public static void execute(String file) throws JobExecutionException {
		
		// Create the attachment
		  EmailAttachment attachment = new EmailAttachment();
		  attachment.setPath(file);//指定附件在本地的路径
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		//  attachment.setDescription("Picture of John");//附件描述
		  attachment.setName("rfidsum.html");//附件名称
		
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("www.zephyr.com.cn");
		// email.setHostName("smtp.163.com");
		email.setSmtpPort(25);
		String date = StudentSafeUtil.getCurrentDate();
		email.setAuthentication("wanghongliang", "zhaohong");
		email.setTLS(true);
	
		try {
			email.setFrom("wanghongliang@zephyr.com.cn");
			email.setCharset("GBK");
			email.setMsg("此邮件为自动发送");
			email.addTo("wanghongliang@zephyr.com.cn");
			email.setSubject("西都学校RFID卡跟踪统计" + StudentSafeUtil.getCurrentDateAllformated());
			email.attach(attachment);
			email.send();
		} catch (EmailException e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
	}
	
	public static void main(String...strings) throws JobExecutionException{
		SendMail.execute("c.html");
	}

}
