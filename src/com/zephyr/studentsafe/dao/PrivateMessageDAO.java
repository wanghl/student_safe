package com.zephyr.studentsafe.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.jasson.im.api.MOItem;
import com.zephyr.studentsafe.bo.Messagelog;
import com.zephyr.studentsafe.bo.PrivateMessage;

public class PrivateMessageDAO extends BaseDAO{
	
	public void receiveMO2Local(MOItem item) throws Exception{
		Session s = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			s = HibernateUtil.getSession();
			s.beginTransaction();
			String sql = "select * from messagelog where smid=" + item.getSmID() ;
			List<Messagelog> list = s.createSQLQuery(sql).addEntity(Messagelog.class).list();
			if (! list.isEmpty()){
				PrivateMessage pm = new PrivateMessage();
				
				pm.setMessage_content(new String(item.getContent().getBytes("iso8859-1"),"gbk"));
				pm.setPhone_number(item.getMobile());
				pm.setReceive_time(sdf.parse(item.getMoTime()));
				pm.setSend_time(new Date(System.currentTimeMillis()));
				pm.setAsk_state(1);
				pm.setRead_state(1);
				pm.setTeacher_id(list.get(0).getSendteacher());
				this.saveORupdate(pm);
			}
			s.getTransaction().commit();
		}catch(Exception e){
			throw e ;
		}finally {
			if (s != null ){
				s.close();
			}
		}
	}
	
	

}
