package com.zephyr.studentsafe.ui.action.button;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;

import javax.comm.PortInUseException;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.jasson.im.api.APIClient;
import com.jasson.im.api.RPTItem;
import com.zephyr.studentsafe.bo.Constants;
import com.zephyr.studentsafe.dao.HibernateUtil;
import com.zephyr.studentsafe.exception.SimplJob;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.exception.T1;
import com.zephyr.studentsafe.impl.ProcessReceiveDataExt;
import com.zephyr.studentsafe.impl.StudentWaitQueue;
import com.zephyr.studentsafe.mobilemessage.MobileMessageHandler;
import com.zephyr.studentsafe.mobilemessage.ReceiveMessageRPT;
import com.zephyr.studentsafe.mobilemessage.SendMessage2Teacher;
import com.zephyr.studentsafe.serialport.SerialReaderExt;
import com.zephyr.studentsafe.ui.FrameClear;
import com.zephyr.studentsafe.ui.HelpFrame;
import com.zephyr.studentsafe.ui.MainFrame;
import com.zephyr.studentsafe.ui.MessageWindow;
import com.zephyr.studentsafe.ui.SettingWindow;
import com.zephyr.studentsafe.util.ReaderLogPaserFromFile;
import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.studentsafe.util.ThreadPoolManage;

/**
 * @author lenovo
 *实现所有按钮action
 */
public class ButtonsAction implements IButtonsAction{
	private Logger log = Logger.getLogger(ButtonsAction.class);
	private APIClient handler = null ;
	@Override
	public void helpButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				HelpFrame inst = new HelpFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
		
	}

	@Override
	public void initButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		//初始化连接，异常向上抛
		String IP = MainFrame.ipAddInput.getText();
		String USER = MainFrame.userNameInput.getText();
		String PSWD = MainFrame.passwordInput.getText();
		String APPID = MainFrame.apiInput.getText();
		String DBNAME = MainFrame.dbNameInput.getText();
		handler = new APIClient() ;
		int connectRe = handler.init(IP, USER, PSWD, APPID, DBNAME) ;
		if(connectRe == APIClient.IMAPI_SUCC)
        	log.info("短信发送服务初始化成功");
        else if(connectRe == APIClient.IMAPI_CONN_ERR){
        	log.info("连接MAS服务器失败(短信发送测试模块)");
        	MessageWindow.show("连接MAS服务器失败",null);
        }
        else if(connectRe == APIClient.IMAPI_API_ERR){
        	log.info("MAS apiID不存在(短信发送测试模块)");
        	MessageWindow.show("MAS apiID不存在",null);
        }else{
        	MessageWindow.show("MAS服务返回错误号：" + connectRe, null);
        }

		MainFrame.sendMsgButton.setEnabled(true);
		MainFrame.receiveRPTButton.setEnabled(true);
		MainFrame.relaseButton.setEnabled(true);
		MessageWindow.show("初始化成功", null);
	}

	@Override
	public void receiveRPTButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		StringBuffer sb = new StringBuffer();
		
		RPTItem[] items = handler.receiveRPT();
		if ( items != null && items.length < 0){
			MessageWindow.show("无回执信息", null);
		}else {
			for(RPTItem r : items){
				sb.append("手机: ");
                sb.append(r.getMobile() + " ");
                sb.append("回执编码: ");
                sb.append(r.getCode() + " ");
                sb.append("回执描述: ");
                try {
                	//对方是iso8859-1编码
					sb.append(new String(r.getDesc().getBytes("ISO-8859-1"),"GB2312") + " ");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                sb.append("回执时间: ");
                sb.append(r.getRptTime() + " ");
                sb.append("\n");
			}
			MessageWindow.show(sb.toString(), null);
		}
		
	}

	@Override
	public void relaseButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		handler.release() ;
		MainFrame.initButton.setEnabled(true);
		MainFrame.sendMsgButton.setEnabled(false);
		MainFrame.receiveRPTButton.setEnabled(false);
		MainFrame.relaseButton.setEnabled(false);
		MessageWindow.show("成功", null);
	}

	@Override
	public void sendButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		String mobiles = MainFrame.mobilesInput.getText();
		String content = MainFrame.msgContentInput.getText();
		if ("".equals(mobiles)){
			MessageWindow.show("请输入手机号码", null);
			return ;
		}else if ("".equals(content)){
			MessageWindow.show("短信内容不能为空", null);
			return ;
		}else{
			APIClient handler = MobileMessageHandler.getInstance();
			Random random = new Random();
			long smID = random.nextInt(99999);//Long.parseLong(phoneNumber.substring(6, phoneNumber.length())) ;
			int result = handler.sendSM(mobiles.split(","),content, smID) ;
			
			if(result == APIClient.IMAPI_SUCC)
	        {            
				log.info("短信发送成功");
				MessageWindow.show("短信发送成功", null);
	        }
	        else if(result == APIClient.IMAPI_CONN_ERR){
	        	log.info("短信服务器数据库连接失败");
	        	MessageWindow.show("短信服务器数据库连接失败", null);
	        }
	        else if(result == APIClient.IMAPI_DATA_ERR){
	        	log.info("参数错误");
	        	MessageWindow.show("参数错误", null);
	        }
	        else if(result == APIClient.IMAPI_DATA_TOOLONG){
	        	log.info("消息内容太长");
	        	MessageWindow.show("消息内容太长", null);
	        }
	        else if(result == APIClient.IMAPI_INS_ERR){
	        	log.info("数据库插入错误");
	        	MessageWindow.show("数据库插入错误", null);
	        }
	        else{
	        	log.info("出现其他错误,返回错误号:" + result);
	        	MessageWindow.show("出现其他错误,返回错误号:" + result, null);
	        }
		}

		
	}

	@Override
	public void settingButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try{
				SettingWindow inst = new SettingWindow();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				}catch(StudentSafeException e){
					log.error(e.getLocalizedMessage());
					MessageWindow.show(e.getLocalizedMessage(), null);
				}catch(Exception el){
					log.error(el.getLocalizedMessage());
					MessageWindow.show(el.getLocalizedMessage(), null);
				}
			}
		});
		
	}

	@Override
	public void startButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		try {
			jcp.setEnabled(false);
			log.info("开始启动程序...");
			log.info("初始化数据库连接...");
			HibernateUtil.getSession();

			log.info("连接MAS服务器...");
			MobileMessageHandler.getInstance();
			
			// ProcessReceiveDataExt类的功能是处理阅读器读到的标签号码。
			// 因为是轮询所以放到独立的线程中执行，否则程序会卡在这里不继续执行
			ThreadPoolManage.getThreadPool().execute(new ProcessReceiveDataExt());
			
			//连接阅读器，准备接收数据
			ThreadPoolManage.getThreadPool().execute(new SerialReaderExt());
			
			//程序启动30秒后开始执行收回执任务 
			Timer t1 = new Timer();
			ReceiveMessageRPT task1 = new ReceiveMessageRPT();
			t1.schedule(task1, 30 * 1000);
		
			//周一到周五每天早上8点、下午15点给班主任发送班级考勤情况
			JobDetail jobd = new JobDetail("job1","group1",SendMessage2Teacher.class);
			CronTrigger cronTrigger = new CronTrigger("tr1","tg1");
			CronExpression cexp = new CronExpression("0 0 8,15 ? * MON-FRI");
			cronTrigger.setCronExpression(cexp);
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler tt = sf.getScheduler();
			tt.scheduleJob(jobd, cronTrigger);
			tt.start() ;
			

				
		
			
		
			
			
		} catch (StudentSafeException e) {
			MessageWindow.show(e.getLocalizedMessage(), null);
			log.error(e.getMessage());
			jcp.setEnabled(true);
		} catch (ParseException e) {
			log.error(e.getMessage());
			jcp.setEnabled(true);
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			jcp.setEnabled(true);
		}

		
	}
	
	
}
