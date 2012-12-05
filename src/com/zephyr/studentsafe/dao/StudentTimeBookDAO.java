package com.zephyr.studentsafe.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Session;

import com.zephyr.studentsafe.bo.Studenttimebook;

public class StudentTimeBookDAO extends BaseDAO{
	
	public Studenttimebook getTimeBookByCardId(String cardid){
		Session s = null;
		List<Studenttimebook> list = null;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			Locale loc = new Locale("zh", "CN");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", loc);

			String date = sdf.format(new Date());
			list = s.createSQLQuery(
					"SELECT * FROM studenttimebook s WHERE rfidcardID=:c and TIME>=UNIX_TIMESTAMP('"
							+ date + "')")
					
					.addEntity("s", Studenttimebook.class)
					.setParameter("c", cardid)
					.list();
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return list.isEmpty() ? null : list.get(0);
	}
	//
	public Studenttimebook getMaxMessageSendTime(String cardid){
		Session s = null;
		Studenttimebook t = null ;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			
			t  = (Studenttimebook) s.createSQLQuery(
					"select *  from studenttimebook s where rfidcardid=:c order by time desc")
					
					.addEntity("s", Studenttimebook.class)
					.setParameter("c", cardid)
					.setMaxResults(1)
					.uniqueResult();
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return t ;
	}
	
	public static void main(String[] argvs) {
		StudentTimeBookDAO dao = new StudentTimeBookDAO();
		System.out.println(dao.getMaxMessageSendTime("132868").getTime().toLocaleString());
	}

}
