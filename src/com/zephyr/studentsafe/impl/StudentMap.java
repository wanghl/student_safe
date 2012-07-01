/**
 * StudentInQueue.java
 * com.zephyr.studentsafe.serialport
 *
 * Function£º TODO 
 *
 *   ver     date      		author
 * ©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤
 *   		 2010-9-9 		lenovo
 *
 * Copyright (c) 2010, TNT All Rights Reserved.
 */

package com.zephyr.studentsafe.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zephyr.studentsafe.bo.Student;

/**
 * ClassName:StudentInQueue Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 * 
 * @author lenovo
 * @version
 * @since Ver 1.1
 * @Date 2010-9-9 ÏÂÎç03:39:16
 * 
 * @see
 */
public class StudentMap {

	private static Map<String,Student> studentMap =  new HashMap<String,Student>();
	
	private StudentMap(){
		
	}
	
	public static void put(String cardId,Student student){
		studentMap.put(cardId, student);
	}
	
	public static Student get(String cardId){
		return studentMap.get(cardId);
	}
	
	public static void pop (String key){
		studentMap.remove(key);
	}
	
	public static Iterator getMapIterator(){
		return studentMap.keySet().iterator();
	}
	
	public static void main(String[] argvs){
		List s = new ArrayList();
		System.out.println(s.get(0));
	}
	
	

}
