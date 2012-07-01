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
 *ʵ�����а�ťaction
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
		//��ʼ�����ӣ��쳣������
		String IP = MainFrame.ipAddInput.getText();
		String USER = MainFrame.userNameInput.getText();
		String PSWD = MainFrame.passwordInput.getText();
		String APPID = MainFrame.apiInput.getText();
		String DBNAME = MainFrame.dbNameInput.getText();
		handler = new APIClient() ;
		int connectRe = handler.init(IP, USER, PSWD, APPID, DBNAME) ;
		if(connectRe == APIClient.IMAPI_SUCC)
        	log.info("���ŷ��ͷ����ʼ���ɹ�");
        else if(connectRe == APIClient.IMAPI_CONN_ERR){
        	log.info("����MAS������ʧ��(���ŷ��Ͳ���ģ��)");
        	MessageWindow.show("����MAS������ʧ��",null);
        }
        else if(connectRe == APIClient.IMAPI_API_ERR){
        	log.info("MAS apiID������(���ŷ��Ͳ���ģ��)");
        	MessageWindow.show("MAS apiID������",null);
        }else{
        	MessageWindow.show("MAS���񷵻ش���ţ�" + connectRe, null);
        }

		MainFrame.sendMsgButton.setEnabled(true);
		MainFrame.receiveRPTButton.setEnabled(true);
		MainFrame.relaseButton.setEnabled(true);
		MessageWindow.show("��ʼ���ɹ�", null);
	}

	@Override
	public void receiveRPTButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		StringBuffer sb = new StringBuffer();
		
		RPTItem[] items = handler.receiveRPT();
		if ( items != null && items.length < 0){
			MessageWindow.show("�޻�ִ��Ϣ", null);
		}else {
			for(RPTItem r : items){
				sb.append("�ֻ�: ");
                sb.append(r.getMobile() + " ");
                sb.append("��ִ����: ");
                sb.append(r.getCode() + " ");
                sb.append("��ִ����: ");
                try {
                	//�Է���iso8859-1����
					sb.append(new String(r.getDesc().getBytes("ISO-8859-1"),"GB2312") + " ");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                sb.append("��ִʱ��: ");
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
		MessageWindow.show("�ɹ�", null);
	}

	@Override
	public void sendButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		String mobiles = MainFrame.mobilesInput.getText();
		String content = MainFrame.msgContentInput.getText();
		if ("".equals(mobiles)){
			MessageWindow.show("�������ֻ�����", null);
			return ;
		}else if ("".equals(content)){
			MessageWindow.show("�������ݲ���Ϊ��", null);
			return ;
		}else{
			APIClient handler = MobileMessageHandler.getInstance();
			Random random = new Random();
			long smID = random.nextInt(99999);//Long.parseLong(phoneNumber.substring(6, phoneNumber.length())) ;
			int result = handler.sendSM(mobiles.split(","),content, smID) ;
			
			if(result == APIClient.IMAPI_SUCC)
	        {            
				log.info("���ŷ��ͳɹ�");
				MessageWindow.show("���ŷ��ͳɹ�", null);
	        }
	        else if(result == APIClient.IMAPI_CONN_ERR){
	        	log.info("���ŷ��������ݿ�����ʧ��");
	        	MessageWindow.show("���ŷ��������ݿ�����ʧ��", null);
	        }
	        else if(result == APIClient.IMAPI_DATA_ERR){
	        	log.info("��������");
	        	MessageWindow.show("��������", null);
	        }
	        else if(result == APIClient.IMAPI_DATA_TOOLONG){
	        	log.info("��Ϣ����̫��");
	        	MessageWindow.show("��Ϣ����̫��", null);
	        }
	        else if(result == APIClient.IMAPI_INS_ERR){
	        	log.info("���ݿ�������");
	        	MessageWindow.show("���ݿ�������", null);
	        }
	        else{
	        	log.info("������������,���ش����:" + result);
	        	MessageWindow.show("������������,���ش����:" + result, null);
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
			log.info("��ʼ��������...");
			log.info("��ʼ�����ݿ�����...");
			HibernateUtil.getSession();

			log.info("����MAS������...");
			MobileMessageHandler.getInstance();
			
			// ProcessReceiveDataExt��Ĺ����Ǵ����Ķ��������ı�ǩ���롣
			// ��Ϊ����ѯ���Էŵ��������߳���ִ�У��������Ῠ�����ﲻ����ִ��
			ThreadPoolManage.getThreadPool().execute(new ProcessReceiveDataExt());
			
			//�����Ķ�����׼����������
			ThreadPoolManage.getThreadPool().execute(new SerialReaderExt());
			
			//��������30���ʼִ���ջ�ִ���� 
			Timer t1 = new Timer();
			ReceiveMessageRPT task1 = new ReceiveMessageRPT();
			t1.schedule(task1, 30 * 1000);
		
			//��һ������ÿ������8�㡢����15��������η��Ͱ༶�������
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
