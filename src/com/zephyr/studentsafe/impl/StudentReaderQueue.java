/**
 * StudentInQueue.java
 * com.zephyr.studentsafe.serialport
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2010-9-9 		lenovo
 *
 * Copyright (c) 2010, TNT All Rights Reserved.
*/

package com.zephyr.studentsafe.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zephyr.studentsafe.bo.Student;
import com.zephyr.studentsafe.bo.StudentExt;

/**
 * ClassName:StudentInQueue
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lenovo
 * @version  
 * @since    Ver 1.1
 * @Date	 2010-9-9		下午03:39:16
 *
 * @see 	 
 */
public class StudentReaderQueue {
	
	// 保存AB阅读器读卡信息 list的第一个对象用于区分AB阅读器
	private static List<List<String>> studentReaderSet = new ArrayList<List<String>>();
	
	private StudentReaderQueue (){
		
	}
	
	public static void put(List<String> list){
		studentReaderSet.add(list) ;
	}
	public static List<String> get(int i){
		return studentReaderSet.get(i);
	}
	
	public static Iterator<List<String>> getIterator(){
		return studentReaderSet.iterator() ;
	}
	

	public static void pop(int i){
		studentReaderSet.remove(i);
	}
	public static int getSize(){
		return studentReaderSet.size();
	}
	
	public static void main(String[] argvs){
		List<String> l = new ArrayList<String>();
		StudentReaderQueue.put(l);
		System.out.println(StudentReaderQueue.getSize());
		//StudentReaderQueue.pop(l);
		System.out.println(StudentReaderQueue.getSize());
	}
}

