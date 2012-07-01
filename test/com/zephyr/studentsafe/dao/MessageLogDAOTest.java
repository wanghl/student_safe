package com.zephyr.studentsafe.dao;

import org.junit.Test;

import com.jasson.im.api.RPTItem;


public class MessageLogDAOTest {
	@Test
	public void testUpdateRPT(){
		RPTItem[] items = new RPTItem[3];
		RPTItem t = new RPTItem();
		RPTItem t1 = new RPTItem();
		RPTItem t2 = new RPTItem();
		t.setSmID(123);
		t1.setSmID(456);
		t2.setSmID(789);
		items[0] = t;
		items[1] = t1;
		items[2] = t2;
		MessageLogDAO dao = new MessageLogDAO();
		dao.updateRPT(items);
	}

}
