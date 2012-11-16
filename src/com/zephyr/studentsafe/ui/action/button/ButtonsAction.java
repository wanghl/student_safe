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
 * @author lenovo 实现所有按钮action
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
		// 初始化连接，异常向上抛
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
			log.info("短信发送服务初始化成功");
		else if (connectRe == APIClient.IMAPI_CONN_ERR) {
			log.info("连接MAS服务器失败(短信发送测试模块)");
			MessageWindow.show("连接MAS服务器失败");
			return ;
		} else if (connectRe == APIClient.IMAPI_API_ERR) {
			log.info("MAS apiID不存在(短信发送测试模块)");
			MessageWindow.show("MAS apiID不存在");
			return ;
		} else {
			MessageWindow.show("MAS服务返回错误号：" + connectRe);
			return ;
		}

		MessageSenderFrame.sendButton.setEnabled(true);
		MessageSenderFrame.receiveButton.setEnabled(true);
		MessageSenderFrame.relaseButton.setEnabled(true);
		MessageWindow.show("初始化成功");
	}

	@Override
	public void receiveRPTButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		StringBuffer sb = new StringBuffer();

		RPTItem[] items = handler.receiveRPT();
		if (items != null && items.length < 0) {
			MessageWindow.show("无回执信息");
		} else {
			for (RPTItem r : items) {
				sb.append("手机: ");
				sb.append(r.getMobile() + " ");
				sb.append("回执编码: ");
				sb.append(r.getCode() + " ");
				sb.append("回执描述: ");
				try {
					// 对方是iso8859-1编码
					sb.append(new String(r.getDesc().getBytes("ISO-8859-1"),
							"GB2312")
							+ " ");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sb.append("回执时间: ");
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
		MessageWindow.show("成功");
	}

	@Override
	public void sendButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		String mobiles = MessageSenderFrame.phoneNumber.getText();
		String content = MessageSenderFrame.content.getText();
		if ("".equals(mobiles)) {
			MessageWindow.show("请输入手机号码");
			return;
		} else if ("".equals(content)) {
			MessageWindow.show("短信内容不能为空");
			return;
		} else {
			APIClient handler = MobileMessageHandler.getInstance();
			Random random = new Random();
			long smID = random.nextInt(99999);// Long.parseLong(phoneNumber.substring(6,
			// phoneNumber.length())) ;
			int result = handler.sendSM(mobiles.split(","), content, smID);

			if (result == APIClient.IMAPI_SUCC) {
				log.info("短信发送成功");
				MessageWindow.show("短信发送成功");
			} else if (result == APIClient.IMAPI_CONN_ERR) {
				log.info("短信服务器数据库连接失败");
				MessageWindow.show("短信服务器数据库连接失败");
			} else if (result == APIClient.IMAPI_DATA_ERR) {
				log.info("参数错误");
				MessageWindow.show("参数错误");
			} else if (result == APIClient.IMAPI_DATA_TOOLONG) {
				log.info("消息内容太长");
				MessageWindow.show("消息内容太长");
			} else if (result == APIClient.IMAPI_INS_ERR) {
				log.info("数据库插入错误");
				MessageWindow.show("数据库插入错误");
			} else {
				log.info("出现其他错误,返回错误号:" + result);
				MessageWindow.show("出现其他错误,返回错误号:" + result);
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
		if (ZephyrPntMainFrame.startButton.getToolTipText().equals("启动")) {

			if (ProcessStudentData._DEBUG_MODEL == 1) {
				log.info("以调试模式运行，不做数据库及短信通道连接。");
				// 打开串口
				ThreadPoolManage.getThreadPool().execute(
						new SerialReaderManage());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 如果打开串口成功，启动进出判断逻辑
				if (SerialReaderManage.isThreadAlive()) {
					ProcessStudentData.alive();
					ThreadPoolManage.getThreadPool().execute(
							new ProcessStudentData());
				}
				// 启动成功，修改startButton图标，噢耶
				ZephyrPntMainFrame.startButton.setToolTipText("停止");
				ZephyrPntMainFrame.startButton.setIcon(new ImageIcon(getClass()
						.getClassLoader().getResource(
								"com/zephyr/studentsafe/icons/stop.png")));

			} else {
				log.info("开始启动程序...");

				// 打开串口，连接阅读器。
				// 只有当阅读器连接成功后，连接数据库和MAS服务器,并启动处理数据线程
				ThreadPoolManage.getThreadPool().execute(
						new SerialReaderManage());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (SerialReaderManage.isThreadAlive()) {

					log.info("连接MAS服务器...");
					try{
						MobileMessageHandler.alive();
					}catch (StudentSafeException e)
					{
						log.error(e);
						
						int r = JOptionPane.showConfirmDialog(null,
						        e.getMessage() + "\n是否继续启动程序？", "ERROR", JOptionPane.YES_NO_OPTION,
						        JOptionPane.ERROR_MESSAGE );
						if(r == JOptionPane.NO_OPTION )
						{
							SerialReaderManage.shutDownThread();
							ThreadPoolManage.relaseThreadPool();
							return ;
						}
					}

					log.info("初始化数据库连接...");
					// 先判断数据库连接是不是已经被关闭了，如果关闭，开启之
					HibernateUtil.alive();
					// HibernateUtil.getSession();
					// 启动逻辑判断线程
					ProcessStudentData.alive();
					ThreadPoolManage.getThreadPool().execute(
							new ProcessStudentData());
				} else {
					// 打开阅读器失败，终止
					log.info("程序启动失败");
					return;
				}
				

				ZephyrPntMainFrame.startButton.setEnabled(true);
				// 启动成功，修改startButton图标，噢耶
				ZephyrPntMainFrame.startButton.setToolTipText("停止");
				ZephyrPntMainFrame.startButton.setIcon(new ImageIcon(getClass()
						.getClassLoader().getResource(
								"com/zephyr/studentsafe/icons/stop.png")));


			}
		} else {
			// 停止按钮
			// 停止阅读器线程
			SerialReaderManage.shutDownThread();
			log.info("终止判断逻辑");
			 ProcessStudentData.shutDown();
			// 关闭线程池
			ThreadPoolManage.relaseThreadPool();

			// 释放MAS服务器连接
			// log.info("释放MAS连接");
			// MobileMessageHandler.relaseConnection();
			// 释放数据库
			// HibernateUtil.relaseConnection();

			// 终止定时收短信回执任务
			// receiveRPOTask.cancel();

			//	
			// 启动成功，修改startButton图标，噢耶
			ZephyrPntMainFrame.startButton.setToolTipText("启动");
			ZephyrPntMainFrame.startButton.setIcon(new ImageIcon(getClass()
					.getClassLoader().getResource(
							"com/zephyr/studentsafe/icons/start.png")));
			jcp.setEnabled(true);

		}
		// 执行收回执任务
		Timer receiveRPOTask = new Timer();
		ReceiveMessageRPT task1 = new ReceiveMessageRPT();
		receiveRPOTask.schedule(task1, 30 * 1000,StudentSafeUtil
				.getIntValue(Constants.RECEIVE_RPT_TIME));

		// 周一到周五每天早上8点、下午15点给班主任发送班级考勤情况 需要设置
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

	// 清空表格内容
	@Override
	public void clearRfidTablePerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// 清空进出校数据
				((DefaultTableModel) ZephyrPntMainFrame.getRfidInfoTable()
						.getModel()).getDataVector().clear();
				((DefaultTableModel) ZephyrPntMainFrame.getRfidInfoTable()
						.getModel()).fireTableDataChanged();
				ZephyrPntMainFrame.getRfidInfoTable().updateUI();
				// 情况监控数据
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
