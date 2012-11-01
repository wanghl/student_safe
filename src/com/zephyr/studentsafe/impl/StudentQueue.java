/**
 *<p>Title: StudentQueue.java</p>
 *<p>Copyright (c) 2012 北京紫枫科技开发有限公司.Co.Ltd. All rights reserved.</p>
 *@author wanghongliang
 *@data 2012-7-2
 */
package com.zephyr.studentsafe.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.zephyr.studentsafe.bo.StudentExt;



/**
 *<p>ClassName:StudentQueue</p>
 *<p>Description: 存放学生信息。</p> 
 *@author wanghongliang xbwolf@sina.cn
 *@date 2012-7-2 下午08:50:50
 *
 */
public class StudentQueue {
	
	private static List<StudentExt> studentList =  Collections.synchronizedList(new ArrayList<StudentExt>());
	private static List<String> list = null;
	private StudentQueue() {

	}

	public static void put(StudentExt s) {
		studentList.add(s);
	}
	
	public static int getSize(){
		return studentList.size();
	}
	
	public static StudentExt get(int i){
		return studentList.get(i);
	}


	// 查找给定卡号的学生信息
	public static StudentExt get(String rfidCardID) {
		StudentExt s = null;
		StudentExt tmp = null ;
		for (Iterator<StudentExt> it = studentList.iterator(); it.hasNext();) {
			tmp = null ;
			tmp = it.next();
			if (rfidCardID.equals(tmp.getRfidCardID())) {
				s = tmp;
				break;
			}
		}
		return s;
	}
	
	public static List<String> getRfidList(){
		list = null ;
		list = new ArrayList<String>();
		for(Iterator<StudentExt> it = studentList.iterator(); it.hasNext();){
			list.add(it.next().getRfidCardID());
		}
		return list ;
	}
	
	public static Iterator<StudentExt> iterator(){
		return studentList.iterator();
	}

	public static List<StudentExt> getList() {
		return studentList;
	}

	
	public static void pop(StudentExt s) {
		for (int i = 0; i < studentList.size(); i++) {
			if ( s.getRfidCardID().equals(studentList.get(i).getRfidCardID())){
				studentList.remove(i);
				break;
				
			}
		}
	}

}
