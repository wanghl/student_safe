package com.zephyr.studentsafe.mobilemessage;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.jasson.im.api.APIClient;
import com.jasson.im.api.RPTItem;
import com.zephyr.studentsafe.bo.Constants;
import com.zephyr.studentsafe.dao.HibernateUtil;
import com.zephyr.studentsafe.dao.MessageLogDAO;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.util.StudentSafeUtil;

/**
 * 接收处理短信的回执。此回执由收到短信的手机终端发出。 短息发送未成功的，需要重新发送
 * 
 * @author lenovo
 * 
 */
public class ReceiveMessageRPT extends TimerTask {
	private final static Logger log = Logger.getLogger(ReceiveMessageRPT.class);
	private MessageLogDAO dao = new MessageLogDAO();

	public void run() {
		// 接收回执的数量
		while (true) {
			try {
				Thread.sleep(StudentSafeUtil
						.getIntValue(Constants.RECEIVE_RPT_TIME));
				// 初始化
				APIClient handler = MobileMessageHandler.getInstance();
				RPTItem[] items = handler.receiveRPT();
				if (items != null && items.length < 0) {
					log.info("无回执内容");
					return;
				}
				// 更新messagelog
				dao.updateRPT(items);
			} catch (StudentSafeException e) {
				log.error(e.getLocalizedMessage());
				try {
					throw new StudentSafeException("接收回执错误："
							+ e.getLocalizedMessage());
				} catch (StudentSafeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				continue; 
			} catch (Exception ex) {
				log.error(ex.getLocalizedMessage());
				try {
					throw new StudentSafeException("接收回执错误："
							+ ex.getLocalizedMessage());
				} catch (StudentSafeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue ;
			}
		}
	}

	public static void main(String[] argvs) throws StudentSafeException {
		HibernateUtil.getSession();
		MobileMessageHandler.getInstance();
		ReceiveMessageRPT r = new ReceiveMessageRPT();
		r.run();
	}

}
