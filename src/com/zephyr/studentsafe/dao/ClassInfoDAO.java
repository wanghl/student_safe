package com.zephyr.studentsafe.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

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
	
	public static void main(String[] argvs){
		ClassInfoDAO d = new ClassInfoDAO();
		System.out.println(d.getTeacherMobile("8a8a8cb32c63f2f1012c63fd592c0008")) ;
	}

}
