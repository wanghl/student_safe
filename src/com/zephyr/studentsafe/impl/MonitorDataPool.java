package com.zephyr.studentsafe.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.zephyr.studentsafe.bo.MonitorData;
import com.zephyr.studentsafe.bo.StudentExt;

/**
 * @author Administrator
 * 存放连接到服务器的通讯箱IP地址 及其对应学校当天的进出校人数
 */
public class MonitorDataPool {
	
	private static Map<String,MonitorData> map = new HashMap<String,MonitorData>();
	
	public static synchronized void put(String key ,MonitorData monitor){
		map.put(key, monitor);
	}
	
	public static synchronized void addMonitorData(StudentExt student){
		MonitorData data = map.get(student.getRemoteIp());
		if(student.getEvent().equals("出校"))
		{
			data.setOutSchool() ; 
			map.put(student.getRemoteIp(), data) ;
		}else if(student.getEvent().equals("入校"))
		{
			data.setInSchool() ;
			map.put(student.getRemoteIp(), data) ;
		}else 
		{
			data.setNotLeave() ; 
			map.put(student.getRemoteIp(), data) ;
		}
	}
	
	public static synchronized void addReceivedDataLength(String key,float length) 
	{
		MonitorData data = map.get(key);
		data.addReceiveDataLength(length);
		map.put(key, data);
	}
	
	public static synchronized MonitorData getMonitorData(String key){
		return map.get(key);
	}
	
	public static synchronized Set getKeySet(){
		return map.keySet() ;
	}
	
	public static void pop(String key)
	{
		map.remove(key);
	}
	
	

}
