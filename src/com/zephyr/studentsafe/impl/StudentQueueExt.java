package com.zephyr.studentsafe.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zephyr.studentsafe.bo.Student;
import com.zephyr.studentsafe.bo.StudentExt;

public class StudentQueueExt {

	/**
	 * 
	 */
	private static List<StudentExt> studentList = new ArrayList<StudentExt>();
	private static List<String> list = null;
	private StudentQueueExt() {

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
	public static void pop(int i){
		studentList.remove(i);
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

	public static void main(String[] argvs) {

	}

}
