package com.zephyr.studentsafe.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.zephyr.studentsafe.bo.ReaderA;
import com.zephyr.studentsafe.bo.ReaderB;
import com.zephyr.studentsafe.bo.StudentExt;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.util.StudentSafeUtil;

public class ReaderDebugDAO extends BaseDAO {
	private Logger log = Logger.getLogger(ReaderDebugDAO.class);
	public List getExceptionDate(int flag ){
		List l = new ArrayList();
		Session s = HibernateUtil.getSession();
		try {
		s.beginTransaction();
		String date = StudentSafeUtil.getCurrentDate();
		//date = "2011-05-17" ;
		if ( flag == 1){
			 l = s.createSQLQuery("SELECT * FROM studentrfid s WHERE(s.lastscandate < '" + date + "' ) ").addEntity(Studentrfid.class).list();
			
		}else if (flag == 2){
			 l = s.createSQLQuery("SELECT * FROM studentrfid s WHERE  (s.lastscandate = '" + date + "' AND s.lastscanstate !=2)").addEntity(Studentrfid.class).list();
			
		}else {
			l = s.createSQLQuery("SELECT * FROM studentrfid s WHERE  (s.lastscandate = '" + date + "' AND s.lastscanstate !=3)").addEntity(Studentrfid.class).list();
			
		}
		s.getTransaction().commit();
		}catch(Exception e ){
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}finally{
			s.close();
		}
		return l ;
	}

	public Map getReaderInfo(String rfid) {
		Map map = new HashMap();
		Session s = HibernateUtil.getSession();
		try {
			s.beginTransaction();
			
			List al = s.createSQLQuery(
					"select objuid,rfid,min(scantime),max(scantime) from readera where rfid=" + rfid).list();
			List bl = s.createSQLQuery(
					"select objuid,rfid,min(scantime),max(scantime) from readerb where rfid=" + rfid).list();
			s.getTransaction().commit();
			if (!al.isEmpty() && !bl.isEmpty()) {
				ReaderA a = new ReaderA();
				Object[] as = (Object[]) al.get(0);
				if (as[1] != null ){
				a.setRfid(as[1].toString());
				a.setMaxTime(as[3].toString());
				a.setMinTime(as[2].toString());
				}else {
					return null ;
				}
				ReaderB b = new ReaderB();
				Object[] bs = (Object[]) bl.get(0);
				if (bs[1] != null){
				b.setRfid(bs[1].toString());
				b.setMaxTime(bs[3].toString());
				b.setMinTime(bs[2].toString());
				}else {
					return null; 
				}
				map.put("ReaderA", a);
				map.put("ReaderB", b);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			s.close();
		}
		return map;

	}

}
