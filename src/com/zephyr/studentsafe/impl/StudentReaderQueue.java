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
import java.util.Iterator;
import java.util.List;


/**
 * ClassName:StudentInQueue
 * Function: 保存读头读到的卡号和触发器信息。格式为 卡号|触发器 
 * 			 例如 ，12345|12 12345表示卡号，12表示触发器编号(1表示校内触发器，2表示校外触发器)
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
	
	//
	private static List<List<String>> studentReaderSet =  Collections.synchronizedList(new  ArrayList<List<String>>());
	
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

