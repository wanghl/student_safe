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
import java.util.HashSet;
import java.util.Iterator;
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
 * @Date	 2010-9-9		ÏÂÎç03:39:16
 *
 * @see 	 
 */
public class StudentQueue2DB {
	
	
	private static Set<StudentExt> studentReaderSet = new HashSet<StudentExt>();
	
	private StudentQueue2DB (){
		
	}
	
	public static List<StudentExt> getFinshData(String rfid){
		List<StudentExt> list = new ArrayList<StudentExt>();
		StudentExt s = null;
		for(Iterator<StudentExt> it = studentReaderSet.iterator(); it.hasNext();){
			s = it.next();
			if(s.getRfidCardID().equals(rfid)){
				list.add(s);
				//studentReaderSet.
			}
		}
		Collections.sort(list);
		studentReaderSet.removeAll(list);
		return list;
		
	}
	public static void put(StudentExt s){
		studentReaderSet.add(s) ;
	}
	
	public static Iterator<StudentExt> getIterator(){
		return studentReaderSet.iterator() ;
	}
	
	public static void pop(Object o){
		studentReaderSet.remove(o) ;
	}
	public static int getSize(){
		return studentReaderSet.size();
	}
	
	public static void main(String[] argvs){
		
	}
}

