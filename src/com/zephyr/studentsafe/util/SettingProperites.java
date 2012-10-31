package com.zephyr.studentsafe.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import com.zephyr.studentsafe.exception.StudentSafeException;

public class SettingProperites {
	
	private static final Logger log = Logger.getLogger(SettingProperites.class);
	private static Map<String,String> map = new HashMap<String,String>();
	
	public static void put(String key,String value){
		map.put(key, value);
	}
	
	public static String get(String key){
		return map.get(key);
	}
	
	//保存配置
	public static void saveSetting() throws StudentSafeException{
		PropertiesConfiguration g = StudentSafeUtil.getConfig();
		for (Iterator<String> it = map.keySet().iterator();it.hasNext();){
			String key = it.next();
			g.setProperty(key, map.get(key));
			try {
				g.save();
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				log.error("保存配置文件错误："+ e.getLocalizedMessage());
				throw new StudentSafeException("保存配置文件错误："+ e.getLocalizedMessage());
			}
		}
		
	}
}
