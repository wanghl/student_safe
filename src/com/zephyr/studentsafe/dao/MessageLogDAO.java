package com.zephyr.studentsafe.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import com.zephyr.studentsafe.bo.*;
import com.jasson.im.api.MOItem;
import com.jasson.im.api.RPTItem;
import com.zephyr.studentsafe.bo.Messagelog;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.util.StudentSafeUtil;

public class MessageLogDAO extends BaseDAO {
	private final static Logger log = Logger.getLogger(MessageLogDAO.class);

	public void saveMO(MOItem it) throws Exception {
		Session s = null;
		Studentrfid rfid = null;
		Messagelog msglog = new Messagelog();
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			List l = s
					.createSQLQuery(
							"select s.* from studentrfid s,studentfamily f"
									+ " where s.studentuid=f.studentuid and f.familyphone='"
									+ it.getMobile() + "'").addEntity(
							Studentrfid.class).list();
			if (!l.isEmpty()) {
				rfid = (Studentrfid) l.get(0);
				msglog.setMessageDesc(new String(it.getContent().getBytes(
						"ISO8859-1"), "GB2312"));
				msglog.setSendPhone(it.getMobile());
				msglog.setSendTime(StudentSafeUtil.formatDate(it.getMoTime()));
				msglog.setSmid(Long.toString(it.getSmID()));
				msglog.setState(Constants.MO_MESSAGE_UN_READ);
				msglog.setMemo("消息未读取");
				msglog.setStudentName(rfid.getStudentName());
				msglog.setRfidcardid(rfid.getRfidCardID());
				msglog.setClassName(rfid.getClassUID());
				msglog.setTeacher(rfid.getTeacherUID());
				msglog.setMessageType(Constants.MESSAGE_TYPE_MO);
				log.info("收到手机回复 ：" + it.getContent());

			} else {
				msglog.setMessageDesc(new String(it.getContent().getBytes(
						"ISO8859-1"), "GB2312"));
				msglog.setSendPhone(it.getMobile());
				msglog.setSendTime(StudentSafeUtil.formatDate(it.getMoTime()));
				msglog.setSmid(Long.toString(it.getSmID()));
				msglog.setState(Constants.MO_MESSAGE_UN_READ);
				msglog.setMemo("消息未读取");
				msglog.setMessageType(Constants.MESSAGE_TYPE_MO);
				log.info("收到手机回复 ：" + it.getContent());

			}
			s.save(msglog);
			s.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	public void updateRPT(RPTItem[] items) {

		Session s = null;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			for (RPTItem it : items) {
				List list = s.createSQLQuery(
						"select * from messagelog where smid='" + it.getSmID()
								+ "' and state=1").addEntity(Messagelog.class)
						.list();
				if (list.size() > 0) {
					Messagelog msg = (Messagelog) list.get(0);
					msg.setMemo(StudentSafeUtil.getMobileRPT(it.getCode()));
					msg.setState(it.getCode());
					log.info("收到短信回执 开始更新数据库  SMID：" + it.getSmID());
					s.update(msg);
				}
			}
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}

	}

}
