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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.zephyr.studentsafe.bo.ClassInfo;
import com.zephyr.studentsafe.bo.Studentfamily;
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

	public Studentrfid getStudentbyCardID(String cardID) {
		Session s = null;
		Studentrfid student = null ;
		List l = null ;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			l =  s.createCriteria(Studentrfid.class)
				.add(Expression.eq("rfidCardID", cardID))
				.setCacheable(true)
				.setFetchMode("classInfo", FetchMode.JOIN)
				.setFetchMode("teacherInfo", FetchMode.JOIN)
				.setCacheRegion("myCacheRegion")
				.setMaxResults(1).list();
			if(!l.isEmpty())
			{
				student = (Studentrfid) l.get(0);
				for(Iterator<Studentfamily> it = student.getStudentFamily().iterator(); it.hasNext();){
					it.next();
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
		return student;

	}
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
	
	@SuppressWarnings( { "unchecked", "null" })
	public List<Studentrfid> getStudent() {
		Session s = null;
		List<Studentrfid> list = null;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			// List<Studentrfid> l = s.createCriteria(Studentrfid.class).list();
			list = s.createSQLQuery("select * from studentrfid").addEntity(Studentrfid.class)
					.list();
			if(!list.isEmpty())
			{
				for(Iterator<Studentrfid> it = list.iterator(); it.hasNext();)
				{
					it.next().getStudentFamily().iterator();
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
		return list;
	}
	
	public Map<ClassInfo,Object[]> studentInOutSchoolStatistics(String date ,int flag ){
		Session s = null;
		List list = null;
		ClassInfoDAO dao = new ClassInfoDAO();
		Object[] objarray = null ;
		Map<ClassInfo,Object[]> map = new HashMap<ClassInfo,Object[]>();
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			list = s.createCriteria(ClassInfo.class).list();
			for(int i = 0 ; i < list.size() ; i ++)
			{
			 objarray = (Object[]) s.createSQLQuery("select (select count(*) from studentrfid where class='" + ((ClassInfo)list.get(i)).getClassUID() + "') AS pp, " +
						"(select count(*) from studentrfid where rfidcardid != '' and class='" + ((ClassInfo)list.get(i)).getClassUID() + "') AS ppc, count(*)  from studentrfid s ,student_property p where p.link_student = s.studentuid " +
						"and strcmp(p.last_scan_date,'" + date + "')=0 and  s.class='" + ((ClassInfo)list.get(i)).getClassUID() + "' and p.last_scan_state=" + flag)
						.list().get(0);
			 map.put(((ClassInfo)list.get(i)), objarray);
				
			}
			
			s.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return map;
	}
	
	//统计未检测到学生名单
	public Map<String,List> countUnCheckStudent(String className,String date ,int flag){
		Session s = null;
		List list = null;
		ClassInfoDAO dao = new ClassInfoDAO();
		Map<String,List> map = new HashMap<String,List>();
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			ClassInfo classInfo = new ClassInfo();
			classInfo.setClassName(className);
			classInfo = (ClassInfo) dao.getByExample(ClassInfo.class, classInfo).get(0);
			list = s.createSQLQuery("SELECT s.studentname , s.rfidcardid,classname ,MAX(TIME),t.event  FROM studenttimebook AS t,"
										+" studentrfid AS s , student_property AS p, class AS c WHERE STRCMP(last_scan_date,'"+ date + "')=0  AND   last_scan_state<>"+ flag
										+ " AND p.link_student = s.StudentUID AND  s.class='"+ classInfo.getClassUID() 
										+ "'AND s.RfidCardID = t.RfidCardID AND s.class = c.objUID and s.rfidcardid != '' GROUP BY s.StudentName").list();
				map.put("uncheck", list);
			list = s.createSQLQuery("select s.studentname,s.rfidcardid,c.classname ,'无记录','无' from studentrfid s ,class c" +
					" where s.class = c.objuid and s.studentuid not in (select link_student from student_property )" +
					" and s.rfidcardid != '' and c.objuid='" + classInfo.getClassUID() + "'").list();
				map.put("less", list);
			
			
			s = HibernateUtil.getSession();
			s.beginTransaction();
			
			s.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return map;
	}
	
	public List studentQuery(Studentrfid student){
		Session s = null ;
		List l = null ;
		try{
			s = HibernateUtil.getSession();
			s.beginTransaction();
			Example example = Example.create(student);
			example.excludeProperty("lastScanState");
			Criteria query  = s.createCriteria(Studentrfid.class).add(example);
			query.setFetchMode("classInfo", FetchMode.JOIN);
			query.setFetchMode("teacherInfo", FetchMode.JOIN);
			if(student.getClassInfo() != null)
			{
				query.add(Restrictions.eq("classInfo", student.getClassInfo())) ;
			}
			if(student.getTeacherInfo() != null)
			{
				query.add(Restrictions.eq("teacherInfo", student.getTeacherInfo()));
			}
			l = query.list();
			s.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (s != null){
				s.close();
			}
		}
		return l ;
	}
	
	
	public List getStudentByPhoneNumber(String phoneNumber){
		Session s = null ;
		List l = new ArrayList() ;
		Studentrfid st = new Studentrfid();
		try{
			s = HibernateUtil.getSession();
			s.beginTransaction();
			Studentfamily f = new Studentfamily();
			f.setFamilyPhone(phoneNumber);
			 f = (Studentfamily) getByExample(Studentfamily.class,f).get(0);
			 l =  s.createCriteria(Studentrfid.class)
				.add(Expression.eq("studentUID", f.getStudentUID()))
				.setFetchMode("classInfo", FetchMode.JOIN)
				.setFetchMode("teacherInfo", FetchMode.JOIN)
				.setCacheRegion("myCacheRegion")
				.setMaxResults(1).list();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (s != null){
				s.close();
			}
		}
		
		return l ;
		
	}


	public static void main(String[] argvs) {
		StudentDAO dao = new StudentDAO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dao.countUnCheckStudent("2011级4班", "2012-11-05", 3);
	}

}
