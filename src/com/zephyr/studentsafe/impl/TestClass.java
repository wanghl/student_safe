package com.zephyr.studentsafe.impl;

import java.util.ArrayList;
import java.util.List;

import com.zephyr.studentsafe.dao.HibernateUtil;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.mobilemessage.MobileMessageHandler;
import com.zephyr.studentsafe.util.ThreadPoolManage;

public class TestClass {
	
	
	
	public  void run(){
//		// 数据库连接初始化;
//		HibernateUtil.getSession();
//		try {
//			MobileMessageHandler.getInstance();
//		} catch (StudentSafeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		List<String> a1 = new ArrayList<String>();
		List<String> b1 = new ArrayList<String>();
		
		b1.add("210888&21");  
		b1.add("207701&21");
		b1.add("209247&21");
		b1.add("209263&21");
		b1.add("209219&21");
		b1.add("207729&21");
		b1.add("209271&21");
		b1.add("209229&21");
		b1.add("207871&21");
		b1.add("362838&21");
		b1.add("362833&21");
		b1.add("362842&21");
		b1.add("2841	&21");
		b1.add("362856&21");
		b1.add("362868&21");
		b1.add("362852&21");
		b1.add("362855&21");
		b1.add("362848&21");
		b1.add("362835&21");
		b1.add("210884&21");
		b1.add("210880&21");
		b1.add("210765&21");
		b1.add("210876&21");
		b1.add("210757&21");
		b1.add("210756&21");
		b1.add("213874&21");
		b1.add("362852&21");
		b1.add("210952&21");
		b1.add("213879&21");
		b1.add("210798&21");
		b1.add("210800&21");
		b1.add("210526&21");
		b1.add("364508&21");
		b1.add("210581&21");
		b1.add("364204&21");
		b1.add("210760&21");
		b1.add("210810&21");
		b1.add("210753&21");
		b1.add("436411&21");
		b1.add("210626&21");
		b1.add("210881&21");
		b1.add("364199&21");
		b1.add("215822&21");
		b1.add("210947&21");
		b1.add("364176&21");
		b1.add("210949&21");
		b1.add("210913&21");
		b1.add("210749&21");
		b1.add("210517&21");
		b1.add("213885&21");
		b1.add("213851&21");
		b1.add("213880&21");
		b1.add("210796&21");
		b1.add("321076&21");
		b1.add("210888&21");
		b1.add("210911&21");
		b1.add("210948&21");
		b1.add("210752&21");
		b1.add("210744&21");




		List<String> d1 = new ArrayList<String>();
		d1.add("120888&12");  
		d1.add("207701&12");
		d1.add("209247&12");
		d1.add("209263&12");
		d1.add("209129&12");
		d1.add("207729&12");
		d1.add("207695&12");
		d1.add("209271&12");
		d1.add("209229&12");
		d1.add("207871&12");
		d1.add("362838&12");
		d1.add("362868&12");
		d1.add("362852&12");
		d1.add("362855&12");
		d1.add("362848&12");
		d1.add("362835&12");
		d1.add("120884&12");
		d1.add("120880&12");
		d1.add("120765&12");
		d1.add("120876&12");
		d1.add("120612&12");
		d1.add("120757&12");
		d1.add("120756&12");
		d1.add("123874&12");
		d1.add("362852&12");
		d1.add("120952&12");
		d1.add("123879&12");
		d1.add("120798&12");
		d1.add("120800&12");
		d1.add("120526&12");
		d1.add("120581&12");
		d1.add("364204&12");
		d1.add("120760&12");
		d1.add("120810&12");
		d1.add("120753&12");
		d1.add("436411&12");
		d1.add("120626&12");
		d1.add("120881&12");
		d1.add("364199&12");
		d1.add("125822&12");
		d1.add("120947&12");
		d1.add("364176&12");
		d1.add("120949&12");
		d1.add("120913&12");
		d1.add("120749&12");
		d1.add("120517&12");
		d1.add("123885&12");
		d1.add("123851&12");
	               

		StudentReaderQueue.put(d1);
		StudentReaderQueue.put(b1);

		
	}

}
