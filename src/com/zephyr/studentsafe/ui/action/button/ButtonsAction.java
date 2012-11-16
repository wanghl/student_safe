package com.zephyr.studentsafe.ui.action.button;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

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
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.impl.ProcessStudentData;
import com.zephyr.studentsafe.impl.StudentReaderQueue;
import com.zephyr.studentsafe.mobilemessage.MobileMessageHandler;
import com.zephyr.studentsafe.mobilemessage.ReceiveMessageRPT;
import com.zephyr.studentsafe.mobilemessage.SendMessage2Teacher;
import com.zephyr.studentsafe.serialport.SerialReaderManage;
import com.zephyr.studentsafe.ui.MessageWindow;
import com.zephyr.studentsafe.ui.ZephyrPntMainFrame;
import com.zephyr.studentsafe.ui.dialog.MessageSenderFrame;
import com.zephyr.studentsafe.ui.dialog.SettingFrame;
import com.zephyr.studentsafe.ui.dialog.StudentImportMainFrame;
import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.studentsafe.util.ThreadPoolManage;

/**
 * @author lenovo ʵ�����а�ťaction
 */
public class ButtonsAction implements IButtonsAction {
	private Logger log = Logger.getLogger(ButtonsAction.class);
	private APIClient handler = null;

	@Override
	public void helpButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		
	}

	@Override
	public void initButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		// ��ʼ�����ӣ��쳣������
		String IP = MessageSenderFrame.serverIP.getText();
		String USER = MessageSenderFrame.userName.getText();
		String PSWD = MessageSenderFrame.password.getText();
		String APPID = MessageSenderFrame.app.getText();
		String DBNAME = MessageSenderFrame.dbName.getText();
		handler = new APIClient();
		int connectRe = 0;
		try {

			connectRe = handler.init(IP, USER, PSWD, APPID, DBNAME);
		} catch (Exception e) {
			MessageWindow.show(e.getLocalizedMessage());
		}
		if (connectRe == APIClient.IMAPI_SUCC)
			log.info("���ŷ��ͷ����ʼ���ɹ�");
		else if (connectRe == APIClient.IMAPI_CONN_ERR) {
			log.info("����MAS������ʧ��(���ŷ��Ͳ���ģ��)");
			MessageWindow.show("����MAS������ʧ��");
			return ;
		} else if (connectRe == APIClient.IMAPI_API_ERR) {
			log.info("MAS apiID������(���ŷ��Ͳ���ģ��)");
			MessageWindow.show("MAS apiID������");
			return ;
		} else {
			MessageWindow.show("MAS���񷵻ش���ţ�" + connectRe);
			return ;
		}

		MessageSenderFrame.sendButton.setEnabled(true);
		MessageSenderFrame.receiveButton.setEnabled(true);
		MessageSenderFrame.relaseButton.setEnabled(true);
		MessageWindow.show("��ʼ���ɹ�");
	}

	@Override
	public void receiveRPTButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		StringBuffer sb = new StringBuffer();

		RPTItem[] items = handler.receiveRPT();
		if (items != null && items.length < 0) {
			MessageWindow.show("�޻�ִ��Ϣ");
		} else {
			for (RPTItem r : items) {
				sb.append("�ֻ�: ");
				sb.append(r.getMobile() + " ");
				sb.append("��ִ����: ");
				sb.append(r.getCode() + " ");
				sb.append("��ִ����: ");
				try {
					// �Է���iso8859-1����
					sb.append(new String(r.getDesc().getBytes("ISO-8859-1"),
							"GB2312")
							+ " ");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sb.append("��ִʱ��: ");
				sb.append(r.getRptTime() + " ");
				sb.append("\n");
			}
			MessageWindow.show(sb.toString());
		}

	}

	@Override
	public void relaseButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		handler.release();
		MessageSenderFrame.initButton.setEnabled(true);
		MessageSenderFrame.sendButton.setEnabled(false);
		MessageSenderFrame.receiveButton.setEnabled(false);
		MessageSenderFrame.relaseButton.setEnabled(false);
		MessageWindow.show("�ɹ�");
	}

	@Override
	public void sendButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		String mobiles = MessageSenderFrame.phoneNumber.getText();
		String content = MessageSenderFrame.content.getText();
		if ("".equals(mobiles)) {
			MessageWindow.show("�������ֻ�����");
			return;
		} else if ("".equals(content)) {
			MessageWindow.show("�������ݲ���Ϊ��");
			return;
		} else {
			APIClient handler = MobileMessageHandler.getInstance();
			Random random = new Random();
			long smID = random.nextInt(99999);// Long.parseLong(phoneNumber.substring(6,
			// phoneNumber.length())) ;
			int result = handler.sendSM(mobiles.split(","), content, smID);

			if (result == APIClient.IMAPI_SUCC) {
				log.info("���ŷ��ͳɹ�");
				MessageWindow.show("���ŷ��ͳɹ�");
			} else if (result == APIClient.IMAPI_CONN_ERR) {
				log.info("���ŷ��������ݿ�����ʧ��");
				MessageWindow.show("���ŷ��������ݿ�����ʧ��");
			} else if (result == APIClient.IMAPI_DATA_ERR) {
				log.info("��������");
				MessageWindow.show("��������");
			} else if (result == APIClient.IMAPI_DATA_TOOLONG) {
				log.info("��Ϣ����̫��");
				MessageWindow.show("��Ϣ����̫��");
			} else if (result == APIClient.IMAPI_INS_ERR) {
				log.info("���ݿ�������");
				MessageWindow.show("���ݿ�������");
			} else {
				log.info("������������,���ش����:" + result);
				MessageWindow.show("������������,���ش����:" + result);
			}
		}

	}

	@Override
	public void settingButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingFrame inst = new SettingFrame();
					inst.setLocationRelativeTo(null);
					inst.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					inst.setVisible(true);

				} catch (Exception el) {
					log.error(el.getLocalizedMessage());
					MessageWindow.show(el.getLocalizedMessage());
				}
			}
		});

	}

	@Override
	public void startButtonPerformeAction(ActionEvent evt, final JComponent jcp)
			throws StudentSafeException {
		if (jcp != null) {
			jcp.setEnabled(false);
		}
		// } else {
		//
		// SwingUtilities.invokeLater(new Runnable() {
		// public void run() {
		// ZephyrPntMainFrame.getStartButton().setEnabled(false);
		// ZephyrPntMainFrame.getStartMenu().setEnabled(false);
		// }
		// });
		//
		// }
		if (ZephyrPntMainFrame.startButton.getToolTipText().equals("����")) {

			if (ProcessStudentData._DEBUG_MODEL == 1) {
				log.info("�Ե���ģʽ���У��������ݿ⼰����ͨ�����ӡ�");
				// �򿪴���
				ThreadPoolManage.getThreadPool().execute(
						new SerialReaderManage());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// ����򿪴��ڳɹ������������ж��߼�
				if (SerialReaderManage.isThreadAlive()) {
					ProcessStudentData.alive();
					ThreadPoolManage.getThreadPool().execute(
							new ProcessStudentData());
				}
				// �����ɹ����޸�startButtonͼ�꣬��Ү
				ZephyrPntMainFrame.startButton.setToolTipText("ֹͣ");
				ZephyrPntMainFrame.startButton.setIcon(new ImageIcon(getClass()
						.getClassLoader().getResource(
								"com/zephyr/studentsafe/icons/stop.png")));

			} else {
				log.info("��ʼ��������...");

				// �򿪴��ڣ������Ķ�����
				// ֻ�е��Ķ������ӳɹ����������ݿ��MAS������,���������������߳�
				ThreadPoolManage.getThreadPool().execute(
						new SerialReaderManage());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (SerialReaderManage.isThreadAlive()) {

					log.info("����MAS������...");
					try{
						MobileMessageHandler.alive();
					}catch (StudentSafeException e)
					{
						log.error(e);
						
						int r = JOptionPane.showConfirmDialog(null,
						        e.getMessage() + "\n�Ƿ������������", "ERROR", JOptionPane.YES_NO_OPTION,
						        JOptionPane.ERROR_MESSAGE );
						if(r == JOptionPane.NO_OPTION )
						{
							SerialReaderManage.shutDownThread();
							ThreadPoolManage.relaseThreadPool();
							return ;
						}
					}

					log.info("��ʼ�����ݿ�����...");
					// ���ж����ݿ������ǲ����Ѿ����ر��ˣ�����رգ�����֮
					HibernateUtil.alive();
					// HibernateUtil.getSession();
					// �����߼��ж��߳�
					ProcessStudentData.alive();
					ThreadPoolManage.getThreadPool().execute(
							new ProcessStudentData());
				} else {
					// ���Ķ���ʧ�ܣ���ֹ
					log.info("��������ʧ��");
					return;
				}
				

				ZephyrPntMainFrame.startButton.setEnabled(true);
				// �����ɹ����޸�startButtonͼ�꣬��Ү
				ZephyrPntMainFrame.startButton.setToolTipText("ֹͣ");
				ZephyrPntMainFrame.startButton.setIcon(new ImageIcon(getClass()
						.getClassLoader().getResource(
								"com/zephyr/studentsafe/icons/stop.png")));


			}
		} else {
			// ֹͣ��ť
			// ֹͣ�Ķ����߳�
			SerialReaderManage.shutDownThread();
			log.info("��ֹ�ж��߼�");
			 ProcessStudentData.shutDown();
			// �ر��̳߳�
			ThreadPoolManage.relaseThreadPool();

			// �ͷ�MAS����������
			// log.info("�ͷ�MAS����");
			// MobileMessageHandler.relaseConnection();
			// �ͷ����ݿ�
			// HibernateUtil.relaseConnection();

			// ��ֹ��ʱ�ն��Ż�ִ����
			// receiveRPOTask.cancel();

			//	
			// �����ɹ����޸�startButtonͼ�꣬��Ү
			ZephyrPntMainFrame.startButton.setToolTipText("����");
			ZephyrPntMainFrame.startButton.setIcon(new ImageIcon(getClass()
					.getClassLoader().getResource(
							"com/zephyr/studentsafe/icons/start.png")));
			jcp.setEnabled(true);

		}
		// ִ���ջ�ִ����
		Timer receiveRPOTask = new Timer();
		ReceiveMessageRPT task1 = new ReceiveMessageRPT();
		receiveRPOTask.schedule(task1, 30 * 1000,StudentSafeUtil
				.getIntValue(Constants.RECEIVE_RPT_TIME));

		// ��һ������ÿ������8�㡢����15��������η��Ͱ༶������� ��Ҫ����
		if (StudentSafeUtil.getIntValue(Constants.SEND_TEACHER_KQ) == 1)
		{
			try {
				JobDetail jobd = new JobDetail("job1", "group1",
						SendMessage2Teacher.class);
				CronTrigger cronTrigger = new CronTrigger("tr1", "tg1");
				CronExpression cexp = new CronExpression(
				"0 0 8,15 ? * MON-FRI");
				cronTrigger.setCronExpression(cexp);
				SchedulerFactory sf = new StdSchedulerFactory();
				Scheduler tt = sf.getScheduler();
				tt.scheduleJob(jobd, cronTrigger);
				tt.start();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				log.error(e);
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				log.error(e);
			}
		}
		
	}

	// ��ձ������
	@Override
	public void clearRfidTablePerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// ��ս���У����
				((DefaultTableModel) ZephyrPntMainFrame.getRfidInfoTable()
						.getModel()).getDataVector().clear();
				((DefaultTableModel) ZephyrPntMainFrame.getRfidInfoTable()
						.getModel()).fireTableDataChanged();
				ZephyrPntMainFrame.getRfidInfoTable().updateUI();
				// ����������
				DefaultTableModel monitModel = (DefaultTableModel) ZephyrPntMainFrame
						.getMonitDataTable().getModel();
				for (int i = 0; i < monitModel.getRowCount(); i++) {
					monitModel.setValueAt("0", i, 1);
				}
				ZephyrPntMainFrame.getMonitDataTable().updateUI();
			}
		});

	}

	@Override
	public void excuteDebugModel(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		

	}

	@Override
	public void messageChanelPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MessageSenderFrame inst = new MessageSenderFrame();
				inst.setLocationRelativeTo(new ZephyrPntMainFrame());
				inst.setVisible(true);
			}
		});

	}

	@Override
	public void studentDataBatchProcess(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				StudentImportMainFrame inst = new StudentImportMainFrame();
				inst.setLocationRelativeTo(null);
				inst.setDefaultCloseOperation(0);
				inst.setVisible(true);
			}
		});

	}
	
	public static void main(String[] argvs) {
		Scanner scanner = new Scanner(System.in) ;
		while (scanner.hasNext()){
			System.out.println(scanner.next());
		}
	}

}
