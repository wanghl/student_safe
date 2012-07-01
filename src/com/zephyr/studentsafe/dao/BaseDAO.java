package com.zephyr.studentsafe.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.zephyr.studentsafe.bo.CardException;
import com.zephyr.studentsafe.bo.ClassInfo;
import com.zephyr.studentsafe.bo.Messagelog;
import com.zephyr.studentsafe.bo.Schooleinfor;
import com.zephyr.studentsafe.bo.Studentfamily;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.bo.Studenttimebook;

public class BaseDAO {
	
	public void saveORupdate(Object obj) {
		Session s = null;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();

			s.saveOrUpdate(obj);

			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}

	}
	
	public Object get(Class clazz) {
		Session s = null;
		List list = null;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			
			list = s.createCriteria(clazz).list();
			
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
	
	public List getObjList(Class clazz) {
		Session s = null;
		List list = null;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			
			list = s.createCriteria(clazz).list();
			
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return list.isEmpty() ? null : list;
	}
	
	public static void main(String[] argvs){
		BaseDAO d = new BaseDAO();
//		d.get(Studenttimebook.class);
		ClassInfo l = new ClassInfo();
		System.out.println(d.getObjList(l.getClass()));
		
	}
	
}
