package com.zephyr.studentsafe.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.zephyr.studentsafe.bo.ClassInfo;
import com.zephyr.studentsafe.bo.Studentrfid;


public class ClassInfoDAO extends BaseDAO{
	
	public String getTeacherMobile(String teacherUID){
		Session s = null ;
		List l = new ArrayList() ;
		try{
			s = HibernateUtil.getSession();
			s.beginTransaction();
			l = s.createSQLQuery("select mobile from do_org_user where objuid='" + teacherUID + "'").list();
			s.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (s != null){
				s.close();
			}
		}
		return l.get(0).toString();
	}
	public String getTeacherName(String teacherUID){
		Session s = null ;
		List l = new ArrayList() ;
		try{
			s = HibernateUtil.getSession();
			s.beginTransaction();
			l = s.createSQLQuery("select name from do_org_user where objuid='" + teacherUID + "'").list();
			s.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (s != null){
				s.close();
			}
		}
		return l.get(0).toString();
	}
	
	public Object[] getClassName(){
		Session s = null ;
		List l = new ArrayList() ;
		try{
			s = HibernateUtil.getSession();
			s.beginTransaction();
			l = s.createSQLQuery("select classname from class group by classname").list();
			s.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (s != null){
				s.close();
			}
		}
		return (l.isEmpty()) ? new Object[]{} : l.toArray();
	}
	//get class and teacher 
	public List getClassInfo(){
		Session s = null ;
		List l = new ArrayList() ;
		try{
			s = HibernateUtil.getSession();
			s.beginTransaction();
			l = s.createSQLQuery("SELECT c.classname ,t.name FROM class c ,do_org_user t WHERE c.teacher = t.objuid").list();
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
	
	//get student by class list
	public List getStudentByClassName(String className){
		Session s = null ;
		List l = new ArrayList() ;
		try{
			s = HibernateUtil.getSession();
			ClassInfo classInfo  = new ClassInfo();
			classInfo.setClassName(className);
			classInfo =  (ClassInfo) getByExample(ClassInfo.class,classInfo).get(0);
			s.beginTransaction();
			l = s.createCriteria(Studentrfid.class)
				.add(Expression.eq("classInfo", classInfo))
				.setFetchMode("classInfo", FetchMode.JOIN)
				.setFetchMode("teacherInfo", FetchMode.JOIN)
				.list();
//			for (int i = 0 ; i < l.size() ;i ++){
//				((Studentrfid)l.get(i)).getStudentFamily().iterator();
//			}
//			
			s.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (s != null){
				s.close();
			}
		}
		return  l;
	}
	
	
	
	public static void main(String[] argvs){
		ClassInfoDAO d = new ClassInfoDAO();
		System.out.println(d.getTeacherMobile("8a8a8cb32c63f2f1012c63fd592c0008")) ;
	}

}
