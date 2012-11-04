package com.zephyr.studentsafe.ui.action.button;

import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
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
					MobileMessageHandler.alive();

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
				
				List a = new ArrayList();
				
				a.add("131734&12");
				a.add("131735&12");
				a.add("131736&12");
				a.add("131737&12");
				a.add("131738&12");
				a.add("131739&12");
				a.add("131740&12");
				a.add("131741&12");
				a.add("131742&12");
//				a.add("131743&12");
//				a.add("131744&12");
//				a.add("131745&12");
//				a.add("131746&12");
//				a.add("131747&12");
//				a.add("131748&12");
//				a.add("131749&12");
//				a.add("131750&12");
//				a.add("131751&12");
//				a.add("131752&12");
//				a.add("131753&12");
//				a.add("131754&12");
//				a.add("131755&12");
//				a.add("131756&12");
//				a.add("131757&12");
//				a.add("131759&12");
//				a.add("131760&12");
//				a.add("131761&12");
//				a.add("131762&12");
//				a.add("131763&12");
//				a.add("131764&12");
//				a.add("131765&12");
//				a.add("131766&12");
//				a.add("131767&12");
//				a.add("131768&12");
//				a.add("131769&12");
//				a.add("131770&12");
//				a.add("131772&12");
//				a.add("131773&12");
//				a.add("131774&12");
//				a.add("131775&12");
//				a.add("131776&12");
//				a.add("131777&12");
//				a.add("131778&12");
//				a.add("131779&12");
//				a.add("131780&12");
//				a.add("131781&12");
//				a.add("131782&12");
//				a.add("131783&12");
//				a.add("131784&12");
//				a.add("131785&12");
//				a.add("131787&12");
//				a.add("131788&12");
//				a.add("131789&12");
//				a.add("131790&12");
//				a.add("131791&12");
//				a.add("131792&12");
//				a.add("131793&12");
//				a.add("131794&12");
//				a.add("131795&12");
//				a.add("131796&12");
//				a.add("131797&12");
//				a.add("131798&12");
//				a.add("131799&12");
//				a.add("131800&12");
//				a.add("131801&12");
//				a.add("131802&12");
//				a.add("131803&12");
//				a.add("131804&12");
//				a.add("131805&12");
//				a.add("131806&12");
//				a.add("131807&12");
//				a.add("131808&12");
//				a.add("131809&12");
//				a.add("131810&12");
//				a.add("131811&12");
//				a.add("131812&12");
//				a.add("131813&12");
//				a.add("131814&12");
//				a.add("131816&12");
//				a.add("131817&12");
//				a.add("131818&12");
//				a.add("131819&12");
//				a.add("131820&12");
//				a.add("131821&12");
//				a.add("131822&12");
//				a.add("131823&12");
//				a.add("131824&12");
//				a.add("131825&12");
//				a.add("131826&12");
//				a.add("131827&12");
//				a.add("131828&12");
//				a.add("131829&12");
//				a.add("131830&12");
//				a.add("131831&12");
//				a.add("131832&12");
//				a.add("131833&12");
//				a.add("131834&12");
//				a.add("131835&12");
//				a.add("131836&12");
//				a.add("131837&12");
//				a.add("131838&12");
//				a.add("131839&12");
//				a.add("131840&12");
//				a.add("131841&12");
//				a.add("131842&12");
//				a.add("131843&12");
//				a.add("131844&12");
//				a.add("131845&12");
//				a.add("131846&12");
//				a.add("131847&12");
//				a.add("131848&12");
//				a.add("131849&12");
//				a.add("131850&12");
//				a.add("131851&12");
//				a.add("131852&12");
//				a.add("131853&12");
//				a.add("131854&12");
//				a.add("131856&12");
//				a.add("131857&12");
//				a.add("131858&12");
//				a.add("131859&12");
//				a.add("131860&12");
//				a.add("131861&12");
//				a.add("131862&12");
//				a.add("131863&12");
//				a.add("131864&12");
//				a.add("131865&12");
//				a.add("131866&12");
//				a.add("131867&12");
//				a.add("131868&12");
//				a.add("131869&12");
//				a.add("131870&12");
//				a.add("131871&12");
//				a.add("131872&12");
//				a.add("131873&12");
//				a.add("131874&12");
//				a.add("131875&12");
//				a.add("131876&12");
//				a.add("131877&12");
//				a.add("131878&12");
//				a.add("131879&12");
//				a.add("131880&12");
//				a.add("131881&12");
//				a.add("131882&12");
//				a.add("131883&12");
//				a.add("131884&12");
//				a.add("131885&12");
//				a.add("131886&12");
//				a.add("131887&12");
//				a.add("131888&12");
//				a.add("131889&12");
//				a.add("131890&12");
//				a.add("131891&12");
//				a.add("131892&12");
//				a.add("131893&12");
//				a.add("131894&12");
//				a.add("131895&12");
//				a.add("131896&12");
//				a.add("131897&12");
//				a.add("131898&12");
//				a.add("131899&12");
//				a.add("131900&12");
//				a.add("131901&12");
//				a.add("131903&12");
//				a.add("131904&12");
//				a.add("131905&12");
//				a.add("131906&12");
//				a.add("131907&12");
//				a.add("131908&12");
//				a.add("131909&12");
//				a.add("131910&12");
//				a.add("131911&12");
//				a.add("131912&12");
//				a.add("131913&12");
//				a.add("131914&12");
//				a.add("131915&12");
//				a.add("131916&12");
//				a.add("131917&12");
//				a.add("131918&12");
//				a.add("131919&12");
//				a.add("131920&12");
//				a.add("131921&12");
//				a.add("131922&12");
//				a.add("131923&12");
//				a.add("131924&12");
//				a.add("131925&12");
//				a.add("131926&12");
//				a.add("131927&12");
//				a.add("131928&12");
//				a.add("131929&12");
//				a.add("131930&12");
//				a.add("131931&12");
//				a.add("131932&12");
//				a.add("131933&12");
//				a.add("131934&12");
//				a.add("131935&12");
//				a.add("131936&12");
//				a.add("131937&12");
//				a.add("131938&12");
//				a.add("131939&12");
//				a.add("131940&12");
//				a.add("131941&12");
//				a.add("131942&12");
//				a.add("131943&12");
//				a.add("131944&12");
//				a.add("131945&12");
//				a.add("131946&12");
//				a.add("131947&12");
//				a.add("131948&12");
//				a.add("131949&12");
//				a.add("131950&12");
//				a.add("131951&12");
//				a.add("131952&12");
//				a.add("131953&12");
//				a.add("131954&12");
//				a.add("131955&12");
//				a.add("131956&12");
//				a.add("131957&12");
//				a.add("131958&12");
//				a.add("131959&12");
//				a.add("131960&12");
//				a.add("131961&12");
//				a.add("131962&12");
//				a.add("131963&12");
//				a.add("131964&12");
//				a.add("131965&12");
//				a.add("131966&12");
//				a.add("131967&12");
//				a.add("131968&12");
//				a.add("131969&12");
//				a.add("131970&12");
//				a.add("131971&12");
//				a.add("131972&12");
//				a.add("131973&12");
//				a.add("131975&12");
//				a.add("131980&12");
//				a.add("131981&12");
//				a.add("131982&12");
//				a.add("131983&12");
//				a.add("131984&12");
//				a.add("131985&12");
//				a.add("131986&12");
//				a.add("131987&12");
//				a.add("131988&12");
//				a.add("131989&12");
//				a.add("131990&12");
//				a.add("131991&12");
//				a.add("131992&12");
//				a.add("131993&12");
//				a.add("131994&12");
//				a.add("131995&12");
//				a.add("131996&12");
//				a.add("131997&12");
//				a.add("131998&12");
//				a.add("131999&12");
//				a.add("1320  &12");
//				a.add("132000&12");
//				a.add("132001&12");
//				a.add("132002&12");
//				a.add("132003&12");
//				a.add("132004&12");
//				a.add("132005&12");
//				a.add("132006&12");
//				a.add("132007&12");
//				a.add("132008&12");
//				a.add("132009&12");
//				a.add("132010&12");
//				a.add("132011&12");
//				a.add("132012&12");
//				a.add("132013&12");
//				a.add("132014&12");
//				a.add("132015&12");
//				a.add("132016&12");
//				a.add("132017&12");
//				a.add("132018&12");
//				a.add("132019&12");
//				a.add("132020&12");
//				a.add("132021&12");
//				a.add("132022&12");
//				a.add("132023&12");
//				a.add("132024&12");
//				a.add("132025&12");
//				a.add("132026&12");
//				a.add("132027&12");
//				a.add("132028&12");
//				a.add("132029&12");
//				a.add("132030&12");
//				a.add("132031&12");
//				a.add("132032&12");
//				a.add("132033&12");
//				a.add("132034&12");
//				a.add("132035&12");
//				a.add("132036&12");
//				a.add("132037&12");
//				a.add("132038&12");
//				a.add("132039&12");
//				a.add("132040&12");
//				a.add("132041&12");
//				a.add("132042&12");
//				a.add("132043&12");
//				a.add("132044&12");
//				a.add("132045&12");
//				a.add("132046&12");
//				a.add("132047&12");
//				a.add("132048&12");
//				a.add("132049&12");
//				a.add("132050&12");
//				a.add("132051&12");
//				a.add("132052&12");
//				a.add("132053&12");
//				a.add("132054&12");
//				a.add("132055&12");
//				a.add("132056&12");
//				a.add("132057&12");
//				a.add("132058&12");
//				a.add("132059&12");
//				a.add("132060&12");
//				a.add("132061&12");
//				a.add("132062&12");
//				a.add("132063&12");
//				a.add("132064&12");
//				a.add("132065&12");
//				a.add("132066&12");
//				a.add("132067&12");
//				a.add("132068&12");
//				a.add("132069&12");
//				a.add("132070&12");
//				a.add("132071&12");
//				a.add("132072&12");
//				a.add("132073&12");
//				a.add("132074&12");
//				a.add("132075&12");
//				a.add("132076&12");
//				a.add("132077&12");
//				a.add("132078&12");
//				a.add("132079&12");
//				a.add("132080&12");
//				a.add("132081&12");
//				a.add("132082&12");
//				a.add("132083&12");
//				a.add("132084&12");
//				a.add("132085&12");
//				a.add("132086&12");
//				a.add("132087&12");
//				a.add("132088&12");
//				a.add("132089&12");
//				a.add("132090&12");
//				a.add("132091&12");
//				a.add("132092&12");
//				a.add("132093&12");
//				a.add("132094&12");
//				a.add("132095&12");
//				a.add("132096&12");
//				a.add("132097&12");
//				a.add("132098&12");
//				a.add("132099&12");
//				a.add("132100&12");
//				a.add("132101&12");
//				a.add("132102&12");
//				a.add("132103&12");
//				a.add("132104&12");
//				a.add("132105&12");
//				a.add("132106&12");
//				a.add("132107&12");
//				a.add("132108&12");
//				a.add("132109&12");
//				a.add("132111&12");
//				a.add("132113&12");
//				a.add("132114&12");
//				a.add("132115&12");
//				a.add("132116&12");
//				a.add("132117&12");
//				a.add("132118&12");
//				a.add("132119&12");
//				a.add("132120&12");
//				a.add("132121&12");
//				a.add("132122&12");
//				a.add("132123&12");
//				a.add("132125&12");
//				a.add("132126&12");
//				a.add("132127&12");
//				a.add("132128&12");
//				a.add("132130&12");
//				a.add("132131&12");
//				a.add("132132&12");
//				a.add("132133&12");
//				a.add("132134&12");
//				a.add("132135&12");
//				a.add("132137&12");
//				a.add("132138&12");
//				a.add("132139&12");
//				a.add("132140&12");
//				a.add("132141&12");
//				a.add("132142&12");
//				a.add("132143&12");
//				a.add("132144&12");
//				a.add("132145&12");
//				a.add("132146&12");
//				a.add("132147&12");
//				a.add("132148&12");
//				a.add("132149&12");
//				a.add("132151&12");
//				a.add("132152&12");
//				a.add("132153&12");
//				a.add("132154&12");
//				a.add("132155&12");
//				a.add("132156&12");
//				a.add("132157&12");
//				a.add("132158&12");
//				a.add("132159&12");
//				a.add("132160&12");
//				a.add("132161&12");
//				a.add("132162&12");
//				a.add("132163&12");
//				a.add("132164&12");
//				a.add("132165&12");
//				a.add("132166&12");
//				a.add("132167&12");
//				a.add("132168&12");
//				a.add("132169&12");
//				a.add("132170&12");
//				a.add("132172&12");
//				a.add("132173&12");
//				a.add("132174&12");
//				a.add("132175&12");
//				a.add("132176&12");
//				a.add("132177&12");
//				a.add("132178&12");
//				a.add("132179&12");
//				a.add("132180&12");
//				a.add("132181&12");
//				a.add("132182&12");
//				a.add("132183&12");
//				a.add("132184&12");
//				a.add("132185&12");
//				a.add("132186&12");
//				a.add("132187&12");
//				a.add("132188&12");
//				a.add("132189&12");
//				a.add("132190&12");
//				a.add("132192&12");
//				a.add("132193&12");
//				a.add("132194&12");
//				a.add("132195&12");
//				a.add("132196&12");
//				a.add("132197&12");
//				a.add("132198&12");
//				a.add("132199&12");
//				a.add("132200&12");
//				a.add("132201&12");
//				a.add("132202&12");
//				a.add("132203&12");
//				a.add("132204&12");
//				a.add("132205&12");
//				a.add("132206&12");
//				a.add("132207&12");
//				a.add("132208&12");
//				a.add("132209&12");
//				a.add("132210&12");
//				a.add("132212&12");
//				a.add("132213&12");
//				a.add("132214&12");
//				a.add("132215&12");
//				a.add("132216&12");
//				a.add("132218&12");
//				a.add("132219&12");
//				a.add("132220&12");
//				a.add("132221&12");
//				a.add("132222&12");
//				a.add("132223&12");
//				a.add("132224&12");
//				a.add("132225&12");
//				a.add("132226&12");
//				a.add("132227&12");
//				a.add("132228&12");
//				a.add("132229&12");
//				a.add("132230&12");
//				a.add("132231&12");
//				a.add("132232&12");
//				a.add("132233&12");
//				a.add("132234&12");
//				a.add("132235&12");
//				a.add("132236&12");
//				a.add("132237&12");
//				a.add("132238&12");
//				a.add("132239&12");
//				a.add("132240&12");
//				a.add("132241&12");
//				a.add("132243&12");
//				a.add("132245&12");
//				a.add("132246&12");
//				a.add("132247&12");
//				a.add("132248&12");
//				a.add("132250&12");
//				a.add("132251&12");
//				a.add("132252&12");
//				a.add("132253&12");
//				a.add("132254&12");
//				a.add("132255&12");
//				a.add("132256&12");
//				a.add("132258&12");
//				a.add("132259&12");
//				a.add("132260&12");
//				a.add("132261&12");
//				a.add("132262&12");
//				a.add("132263&12");
//				a.add("132264&12");
//				a.add("132265&12");
//				a.add("132267&12");
//				a.add("132269&12");
//				a.add("132270&12");
//				a.add("132271&12");
//				a.add("132272&12");
//				a.add("132273&12");
//				a.add("132274&12");
//				a.add("132275&12");
//				a.add("132276&12");
//				a.add("132277&12");
//				a.add("132278&12");
//				a.add("132279&12");
//				a.add("132280&12");
//				a.add("132281&12");
//				a.add("132282&12");
//				a.add("132283&12");
//				a.add("132284&12");
//				a.add("132285&12");
//				a.add("132286&12");
//				a.add("132287&12");
//				a.add("132288&12");
//				a.add("132289&12");
//				a.add("132290&12");
//				a.add("132291&12");
//				a.add("132292&12");
//				a.add("132293&12");
//				a.add("132294&12");
//				a.add("132295&12");
//				a.add("132296&12");
//				a.add("132297&12");
//				a.add("132298&12");
//				a.add("132299&12");
//				a.add("132300&12");
//				a.add("132301&12");
//				a.add("132302&12");
//				a.add("132303&12");
//				a.add("132304&12");
//				a.add("132305&12");
//				a.add("132306&12");
//				a.add("132307&12");
//				a.add("132308&12");
//				a.add("132310&12");
//				a.add("132312&12");
//				a.add("132313&12");
//				a.add("132314&12");
//				a.add("132315&12");
//				a.add("132316&12");
//				a.add("132317&12");
//				a.add("132318&12");
//				a.add("132319&12");
//				a.add("132320&12");
//				a.add("132321&12");
//				a.add("132322&12");
//				a.add("132323&12");
//				a.add("132325&12");
//				a.add("132326&12");
//				a.add("132327&12");
//				a.add("132328&12");
//				a.add("132329&12");
//				a.add("132330&12");
//				a.add("132331&12");
//				a.add("132332&12");
//				a.add("132333&12");
//				a.add("132334&12");
//				a.add("132335&12");
//				a.add("132336&12");
//				a.add("132337&12");
//				a.add("132338&12");
//				a.add("132339&12");
//				a.add("132340&12");
//				a.add("132341&12");
//				a.add("132342&12");
//				a.add("132343&12");
//				a.add("132344&12");
//				a.add("132345&12");
//				a.add("132346&12");
//				a.add("132347&12");
//				a.add("132348&12");
//				a.add("132349&12");
//				a.add("132350&12");
//				a.add("132351&12");
//				a.add("132352&12");
//				a.add("132353&12");
//				a.add("132354&12");
//				a.add("132355&12");
//				a.add("132356&12");
//				a.add("132357&12");
//				a.add("132358&12");
//				a.add("132359&12");
//				a.add("132360&12");
//				a.add("132361&12");
//				a.add("132362&12");
//				a.add("132363&12");
//				a.add("132364&12");
//				a.add("132365&12");
//				a.add("132366&12");
//				a.add("132367&12");
//				a.add("132368&12");
//				a.add("132369&12");
//				a.add("132370&12");
//				a.add("132371&12");
//				a.add("132372&12");
//				a.add("132373&12");
//				a.add("132374&12");
//				a.add("132375&12");
//				a.add("132376&12");
//				a.add("132377&12");
//				a.add("132378&12");
//				a.add("132379&12");
//				a.add("132380&12");
//				a.add("132381&12");
//				a.add("132382&12");
//				a.add("132383&12");
//				a.add("132384&12");
//				a.add("132385&12");
//				a.add("132386&12");
//				a.add("132387&12");
//				a.add("132388&12");
//				a.add("132389&12");
//				a.add("132391&12");
//				a.add("132393&12");
//				a.add("132394&12");
//				a.add("132395&12");
//				a.add("132396&12");
//				a.add("132397&12");
//				a.add("132398&12");
//				a.add("132399&12");
//				a.add("132400&12");
//				a.add("132401&12");
//				a.add("132403&12");
//				a.add("132404&12");
//				a.add("132405&12");
//				a.add("132406&12");
//				a.add("132407&12");
//				a.add("132408&12");
//				a.add("132409&12");
//				a.add("132410&12");
//				a.add("132411&12");
//				a.add("132412&12");
//				a.add("132413&12");
//				a.add("132414&12");
//				a.add("132415&12");
//				a.add("132416&12");
//				a.add("132417&12");
//				a.add("132418&12");
//				a.add("132419&12");
//				a.add("132420&12");
//				a.add("132421&12");
//				a.add("132422&12");
//				a.add("132423&12");
//				a.add("132424&12");
//				a.add("132425&12");
//				a.add("132426&12");
//				a.add("132427&12");
//				a.add("132428&12");
//				a.add("132429&12");
//				a.add("132430&12");
//				a.add("132431&12");
//				a.add("132432&12");
//				a.add("132433&12");
//				a.add("132434&12");
//				a.add("132435&12");
//				a.add("132436&12");
//				a.add("132437&12");
//				a.add("132438&12");
//				a.add("132439&12");
//				a.add("132440&12");
//				a.add("132441&12");
//				a.add("132442&12");
//				a.add("132443&12");
//				a.add("132444&12");
//				a.add("132445&12");
//				a.add("132446&12");
//				a.add("132447&12");
//				a.add("132448&12");
//				a.add("132449&12");
//				a.add("132450&12");
//				a.add("132451&12");
//				a.add("132452&12");
//				a.add("132453&12");
//				a.add("132454&12");
//				a.add("132455&12");
//				a.add("132456&12");
//				a.add("132457&12");
//				a.add("132458&12");
//				a.add("132459&12");
//				a.add("132460&12");
//				a.add("132461&12");
//				a.add("132462&12");
//				a.add("132463&12");
//				a.add("132464&12");
//				a.add("132465&12");
//				a.add("132466&12");
//				a.add("132468&12");
//				a.add("132469&12");
//				a.add("132470&12");
//				a.add("132471&12");
//				a.add("132472&12");
//				a.add("132473&12");
//				a.add("132474&12");
//				a.add("132475&12");
//				a.add("132476&12");
//				a.add("132477&12");
//				a.add("132478&12");
//				a.add("132479&12");
//				a.add("132480&12");
//				a.add("132481&12");
//				a.add("132482&12");
//				a.add("132483&12");
//				a.add("132484&12");
//				a.add("132486&12");
//				a.add("132487&12");
//				a.add("132488&12");
//				a.add("132489&12");
//				a.add("132490&12");
//				a.add("132491&12");
//				a.add("132492&12");
//				a.add("132493&12");
//				a.add("132494&12");
//				a.add("132496&12");
//				a.add("132497&12");
//				a.add("132498&12");
//				a.add("132499&12");
//				a.add("132500&12");
//				a.add("132501&12");
//				a.add("132502&12");
//				a.add("132503&12");
//				a.add("132504&12");
//				a.add("132505&12");
//				a.add("132506&12");
//				a.add("132507&12");
//				a.add("132508&12");
//				a.add("132509&12");
//				a.add("132510&12");
//				a.add("132511&12");
//				a.add("132512&12");
//				a.add("132513&12");
//				a.add("132514&12");
//				a.add("132515&12");
//				a.add("132516&12");
//				a.add("132517&12");
//				a.add("132518&12");
//				a.add("132519&12");
//				a.add("132520&12");
//				a.add("132521&12");
//				a.add("132522&12");
//				a.add("132523&12");
//				a.add("132524&12");
//				a.add("132525&12");
//				a.add("132526&12");
//				a.add("132527&12");
//				a.add("132529&12");
//				a.add("132530&12");
//				a.add("132531&12");
//				a.add("132532&12");
//				a.add("132533&12");
//				a.add("132534&12");
//				a.add("132535&12");
//				a.add("132538&12");
//				a.add("132539&12");
//				a.add("132540&12");
//				a.add("132541&12");
//				a.add("132542&12");
//				a.add("132543&12");
//				a.add("132544&12");
//				a.add("132545&12");
//				a.add("132547&12");
//				a.add("132548&12");
//				a.add("132549&12");
//				a.add("132550&12");
//				a.add("132551&12");
//				a.add("132552&12");
//				a.add("132554&12");
//				a.add("132555&12");
//				a.add("132556&12");
//				a.add("132557&12");
//				a.add("132558&12");
//				a.add("132559&12");
//				a.add("132560&12");
//				a.add("132561&12");
//				a.add("132562&12");
//				a.add("132563&12");
//				a.add("132564&12");
//				a.add("132565&12");
//				a.add("132566&12");
//				a.add("132567&12");
//				a.add("132568&12");
//				a.add("132569&12");
//				a.add("132570&12");
//				a.add("132572&12");
//				a.add("132573&12");
//				a.add("132574&12");
//				a.add("132575&12");
//				a.add("132576&12");
//				a.add("132577&12");
//				a.add("132578&12");
//				a.add("132579&12");
//				a.add("132580&12");
//				a.add("132581&12");
//				a.add("132582&12");
//				a.add("132583&12");
//				a.add("132584&12");
//				a.add("132585&12");
//				a.add("132586&12");
//				a.add("132587&12");
//				a.add("132588&12");
//				a.add("132589&12");
//				a.add("132590&12");
//				a.add("132591&12");
//				a.add("132592&12");
//				a.add("132593&12");
//				a.add("132594&12");
//				a.add("132595&12");
//				a.add("132596&12");
//				a.add("132597&12");
//				a.add("132598&12");
//				a.add("132599&12");
//				a.add("132600&12");
//				a.add("132601&12");
//				a.add("132602&12");
//				a.add("132603&12");
//				a.add("132604&12");
//				a.add("132606&12");
//				a.add("132607&12");
//				a.add("132608&12");
//				a.add("132609&12");
//				a.add("132610&12");
//				a.add("132611&12");
//				a.add("132612&12");
//				a.add("132613&12");
//				a.add("132614&12");
//				a.add("132615&12");
//				a.add("132616&12");
//				a.add("132617&12");
//				a.add("132618&12");
//				a.add("132619&12");
//				a.add("13262 &12");
//				a.add("132620&12");
//				a.add("132621&12");
//				a.add("132622&12");
//				a.add("132623&12");
//				a.add("132624&12");
//				a.add("132625&12");
//				a.add("132626&12");
//				a.add("132627&12");
//				a.add("132627&12");
//				a.add("132628&12");
//				a.add("132629&12");
//				a.add("132630&12");
//				a.add("132631&12");
//				a.add("132632&12");
//				a.add("132633&12");
//				a.add("132634&12");
//				a.add("132635&12");
//				a.add("132636&12");
//				a.add("132637&12");
//				a.add("132638&12");
//				a.add("132639&12");
//				a.add("132640&12");
//				a.add("132641&12");
//				a.add("132642&12");
//				a.add("132643&12");
//				a.add("132644&12");
//				a.add("132645&12");
//				a.add("132646&12");
//				a.add("132647&12");
//				a.add("132648&12");
//				a.add("132649&12");
//				a.add("132650&12");
//				a.add("132651&12");
//				a.add("132652&12");
//				a.add("132653&12");
//				a.add("132654&12");
//				a.add("132655&12");
//				a.add("132656&12");
//				a.add("132657&12");
//				a.add("132658&12");
//				a.add("132659&12");
//				a.add("132660&12");
//				a.add("132661&12");
//				a.add("132662&12");
//				a.add("132663&12");
//				a.add("132664&12");
//				a.add("132665&12");
//				a.add("132667&12");
//				a.add("132668&12");
//				a.add("132669&12");
//				a.add("132670&12");
//				a.add("132671&12");
//				a.add("132674&12");
//				a.add("132675&12");
//				a.add("132676&12");
//				a.add("132677&12");
//				a.add("132678&12");
//				a.add("132679&12");
//				a.add("132680&12");
//				a.add("132681&12");
//				a.add("132682&12");
//				a.add("132683&12");
//				a.add("132684&12");
//				a.add("132685&12");
//				a.add("132687&12");
//				a.add("132688&12");
//				a.add("132690&12");
//				a.add("132691&12");
//				a.add("132692&12");
//				a.add("132693&12");
//				a.add("132694&12");
//				a.add("132695&12");
//				a.add("132696&12");
				
				StudentReaderQueue.put(a);
				
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
