/**
 * HibernateUtil.java
 * com.zephyr.studentsafe.dao
 *
 * Function£º TODO 
 *
 *   ver     date      		author
 * ©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤
 *   		 2010-9-7 		lenovo
 *
 * Copyright (c) 2010, TNT All Rights Reserved.
*/

package com.zephyr.studentsafe.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.zephyr.studentsafe.exception.StudentSafeException;

/**
 * ClassName:HibernateUtil
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lenovo
 * @version  
 * @since    Ver 1.1
 * @Date	 2010-9-7		ÏÂÎç08:36:56
 *
 * @see 	 
 */
public class HibernateUtil {
	
	private final static Logger log = Logger.getLogger(HibernateUtil.class);
	private static SessionFactory sessionFactory ;
	
	private HibernateUtil(){
		
	}
	
	static {
		try{
		Configuration cfg = new Configuration();
		cfg.configure();
		sessionFactory = cfg.buildSessionFactory();
		}catch (Exception e){
			try {
				throw new StudentSafeException("±¾µØÊý¾Ý¿â³õÊ¼»¯Á¬½ÓÒì³£:" + e.getLocalizedMessage());
			} catch (StudentSafeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static Session getSession(){
		return sessionFactory.openSession();
	}

}

