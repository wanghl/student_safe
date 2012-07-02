/**
 *<p>Title: StudentQueue.java</p>
 *<p>Copyright (c) 2012 北京紫枫科技开发有限公司.Co.Ltd. All rights reserved.</p>
 *@author wanghongliang
 *@data 2012-7-2
 */
package com.zephyr.studentsafe.impl.trigger;

import java.util.ArrayList;
import java.util.Collections;
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
	
	private static List<StudentExt> _studentlist = Collections.synchronizedList(new ArrayList<StudentExt>()) ;
	
	
	public static void put(StudentExt student){
		_studentlist.add(student) ;
	}
	
	public static StudentExt get(int index){
		return _studentlist.get(index);
	}
	
	
	/**
	 *<b>功能:根据给定rfid号查找对应的student对象</b><br>
	 *<br>
	 *@author wanghongliang,2012-7-2
	 *@param _rfid
	 *@return 
	 */
	public static StudentExt get(String _rfid){
		StudentExt student = null ;
		for (int i = 0 ; i < _studentlist.size() ; i ++ ){
			student = _studentlist.get(i);
			if (student.getRfidCardID().equals(_rfid)){
				return student ;
			}
		}
		
		return null ;
	}
	
	public static List<String> getRfidList(){
		List<String> l = new ArrayList<String>();
		StudentExt student  ;
		for(int i = 0 ; i < _studentlist.size() ; i++ ){
			student = _studentlist.get(i);
			l.add(student.getRfidCardID()); 
		}
		return l ;
	}
	
	public static int getSize() {
		return _studentlist.size() ;
	}
	
	public static void pop(StudentExt s) {
		for (int i = 0; i < _studentlist.size(); i++) {
			if ( s.getRfidCardID().equals(_studentlist.get(i).getRfidCardID())){
				_studentlist.remove(i);
				break;
				
			}
		}
	}
	

}
