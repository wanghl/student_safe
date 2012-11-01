package com.zephyr.studentsafe.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.criterion.Example;


import sun.security.provider.MD5;


import com.zephyr.studentsafe.bo.Studentfamily;
import com.zephyr.studentsafe.bo.Studentrfid;

import com.zephyr.studentsafe.util.StudentSafeUtil;

public class BaseDAO {
	
	public void saveORupdate(Object obj) throws Exception {
		Session s = null;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();

			s.saveOrUpdate(obj);

			s.getTransaction().commit();
		} catch (Exception e) {
			throw e;
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
	// get object 需优化
	public Object get(Class clazz,String id) {
		Session s = null;
		Object retsult = null ; 
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			
			retsult = s.get(clazz, id);
			
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return retsult ;
	}
	
	//需优化
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
	
	public List getTeacherByName(String name) {
		Session s = null;
		List list = null;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			
			list = s.createSQLQuery("select objuid from do_org_user where name='" + name + "'").list();
			
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
	//
	public Object[] getTeacherList() {
		Session s = null;
		List list = null;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			
			list = s.createSQLQuery("select name from do_org_user").list();
			
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return (list.isEmpty()) ? new Object[]{} : list.toArray() ;
	}
	//
	public List getTeacherById(String id) {
		Session s = null;
		List list = null;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			
			list = s.createSQLQuery("select name from do_org_user where objuid='" + id + "'").list();
			
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return list ;
	}
	
	public void saveTeacherInfo(String name,String phone_number){
		Session s = null;
		List list = null;
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			MD5 e = new MD5();
			
			 s.createSQLQuery("insert into do_org_user(objuid,name,password,mobile) values ('"
									+ StudentSafeUtil.getRandom() + "','" + name + "','" + 
									"4297f44b13955235245b2497399d7a93" + "','"
									+ phone_number + "');").executeUpdate();
			
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
	
	public List getByExample(Class clazz ,Object object){
		Session s = null ;
		List l = null ;
		try{
			s = HibernateUtil.getSession();
			s.beginTransaction();
			Example example = Example.create(object);
			if (object instanceof Studentrfid)
			{
				example.excludeProperty("lastScanState");
			}else if ( object instanceof Studentfamily){
				example.excludeProperty("isSendMessage");
			}
			l = s.createCriteria(clazz).add(example).list();
			
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
	public void delete(Object object){
		Session s = null ;
		List l = null ;
		try{
			s = HibernateUtil.getSession();
			s.beginTransaction();
			
			s.delete(object);
			s.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (s != null){
				s.close();
			}
		}
		
	}
	
	
	
	public static void main(String[] argvs){
		BaseDAO d = new BaseDAO();
		
		Studentrfid s = new Studentrfid();
		s.setRfidCardID("132627");
		d.getObjList(Studentrfid.class) ;
	}
	
}
