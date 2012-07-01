package com.zephyr.studentsafe.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.util.StudentSafeUtil;

public class SettingProperites {
	
	private static final Logger log = Logger.getLogger(SettingProperites.class);
	private static Map<String,String> map = new HashMap<String,String>();
	
	public static void put(String key,String value){
		map.put(key, value);
	}
	
	public static String get(String key){
		return map.get(key);
	}
	
	//±£´æÅäÖÃ
	public static void saveSetting() throws StudentSafeException{
		PropertiesConfiguration g = StudentSafeUtil.getConfig();
		for (Iterator<String> it = map.keySet().iterator();it.hasNext();){
			String key = it.next();
			g.setProperty(key, map.get(key));
			try {
				g.save();
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				log.error("±£´æÅäÖÃÎÄ¼þ´íÎó£º"+ e.getLocalizedMessage());
				throw new StudentSafeException("±£´æÅäÖÃÎÄ¼þ´íÎó£º"+ e.getLocalizedMessage());
			}
		}
		
	}
}
