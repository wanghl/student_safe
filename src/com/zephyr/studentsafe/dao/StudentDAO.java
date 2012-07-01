/**
 * StudentDAO.java
 * com.zephyr.studentsafe.dao
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2010-9-7 		lenovo
 *
 * Copyright (c) 2010, TNT All Rights Reserved.
 */

package com.zephyr.studentsafe.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.bo.Studenttimebook;
import com.zephyr.studentsafe.util.StudentSafeUtil;

/**
 * ClassName:StudentDAO Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 * 
 * @author lenovo
 * @version
 * @since Ver 1.1
 * @Date 2010-9-7 下午07:35:04
 * 
 * @see
 */
public class StudentDAO extends BaseDAO {

	private final static Logger log = Logger.getLogger(StudentDAO.class);

	@SuppressWarnings("unchecked")
	//TODO :需重构， 这个方法改掉。使用byEnsample模式
	public Studentrfid getStudentbyCardID(String cardID) {
		Session s = null;
		List<Studentrfid> list = null;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			list = s.createCriteria(Studentrfid.class).add(
					Expression.eq("rfidCardID", cardID)).list();
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
	//TODO: 重构之  和上面的方法合并
	public List<Studentrfid> getStudentbyClassUID(String classUid) {
		Session s = null;
		List<Studentrfid> list = null;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			list = s.createSQLQuery("select * from studentrfid where class='" + classUid + "'").addEntity(Studentrfid.class).list();
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return list;
		
	}

	@SuppressWarnings( { "unchecked", "null" })
	public List<String> getRfidList() {
		Session s = null;
		List<String> list = null;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			// List<Studentrfid> l = s.createCriteria(Studentrfid.class).list();
			list = s.createSQLQuery("select rfidcardid from studentrfid")
					.list();
			s.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return list;
	}

	public List<Studenttimebook> getReadyForSendMessage() {
		Session s = null;
		List<Studenttimebook> list = null;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			String date = StudentSafeUtil.getCurrentDate();
			// List<Studentrfid> l = s.createCriteria(Studentrfid.class).list();
			list = s.createSQLQuery(
					"SELECT * FROM studenttimebook s WHERE TIME>=UNIX_TIMESTAMP('"
							+ date + "')")
					.addEntity("s", Studenttimebook.class).list();
			s.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return list;
	}
	
	public int getStudentAttendanceCount() {
		Session s = null ;
		int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		String date = StudentSafeUtil.getCurrentDate();
		List list = new ArrayList() ;
		String str = "";
		if ( h >= 8){
			str = "' and s.lastscanstate=1";
		}else if ( h >= 15){
			str = "' and s.lastscanstate=2";
		}
		
		try {
			s = HibernateUtil.getSession() ;
			s.beginTransaction(); 
			String sql = "select count(*) from studentrfid s where s.lastscandate='" + date + str ;
			list = s.createSQLQuery(sql).list();
			s.getTransaction().commit();
		}catch (Exception e ){
			e.printStackTrace();
		}finally {
			if( s != null ){
				s.close();
			}
		}
		return list.size();
	}
	
	/**
	 * 学生考勤
	 */
	@SuppressWarnings("unchecked")
	public List<Studentrfid> getStudentAttendance(String classId,boolean inSchool){
		Session s = null ;
		List<Studentrfid> list = new ArrayList<Studentrfid>() ;
		String str = "";
		int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		System.out.println(h);
		if ( h == 8){
			str = " and lastscanstate=1";
		}else if ( h == 15){
			str = " and lastscanstate=2";
		}
		try {
			s = HibernateUtil.getSession() ;
			s.beginTransaction(); 
			String date = StudentSafeUtil.getCurrentDate();
			if (inSchool){
				list = s.createSQLQuery("select * from studentrfid s where " +
						"s.lastscandate = '" + date + "'" + str + 
						" and class='" + classId + "'")
						.addEntity("s", Studentrfid.class).list();
			}else{
				if ( h >= 8 && h < 15 ){
					list = s.createSQLQuery("select * from studentrfid s where " +
							"(s.lastscandate < '" + date + "'" +
							" or s.lastscandate is null) and class='" + classId + "'")
							.addEntity("s", Studentrfid.class).list();
					
				}else if ( h >= 15){
					list = s.createSQLQuery("select * from studentrfid s where " +
							"(s.lastscandate < '" + date + "' or s.lastscandate is null" + " or (s.lastscandate = '" + date + "' and s.lastscanstate != 2)) and class='" + classId + "'")
							.addEntity("s", Studentrfid.class).list();
					
				}
				
				
			}
			s.getTransaction().commit();
		}catch (Exception e ){
			e.printStackTrace();
		}finally {
			if (s != null){
				s.close();
			}
		}
		return list ;
	}

	public static void main(String[] argvs) {
		StudentDAO dao = new StudentDAO();
		System.out.println(dao.getStudentAttendanceCount());
	}

}
