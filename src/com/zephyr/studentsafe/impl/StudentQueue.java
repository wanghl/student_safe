package com.zephyr.studentsafe.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zephyr.studentsafe.bo.Student;

public class StudentQueue {
	
	/**
	 * ������ÿ����������뿪ѧУ��ѧ��rfid�����
	 */
	private static List<Student> studentList = new ArrayList<Student>();
	
	private StudentQueue(){
		
	}
	
	public static void put(Student s){
		studentList.add(s);
	}
	
	public static Iterator getStudentListIterator(){
		return studentList.iterator();
	}
	
	public static List<Student> getList(){
		return studentList ;
	}
	
	public static void pop(Student s ){
		studentList.remove(s);
	}
	
	public static void main(String[] argvs){
		
		
	}

}
