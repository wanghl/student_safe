package com.zephyr.studentsafe.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

import com.zephyr.studentsafe.exception.StudentSafeException;

public class IPManUtil {
	private final static Logger log = Logger.getLogger(IPManUtil.class);
	private static PropertiesConfiguration config = null;
	
	private  static PropertiesConfiguration getConfig(){
		if (config == null) {
			try {
				config = new PropertiesConfiguration(
						"ipman.properties");
				config.setReloadingStrategy(new FileChangedReloadingStrategy());
				return config;
			} catch (ConfigurationException e) {
				log.error(e);
			}
		} 
		return config;
	}
	
	public static List getBlackList()
	{
		return getConfig().getList("black_list");
	}
	
	public static String getStringValue(String key)
	{
		return getConfig().getString(key);
	}
	
	//±£¥Ê≈‰÷√
			public static void saveSetting(Map map) throws StudentSafeException{
				PropertiesConfiguration g = getConfig();
				for (Iterator<String> it = map.keySet().iterator();it.hasNext();){
					String key = it.next();
					g.setProperty(key, map.get(key));
					try {
						g.save();
					} catch (ConfigurationException e) {
						throw new StudentSafeException("±£¥Ê≈‰÷√Œƒº˛¥ÌŒÛ£∫"+ e.getLocalizedMessage());
					}
				}
			}
				
		//±£¥Ê≈‰÷√  KEY  VALUE
				
		public static void saveStting(String key ,String value)
		{
			Map map = new HashMap();
			
			map.put(key, value);
			
			try {
				saveSetting(map);
			} catch (StudentSafeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	public static void main(String[] argvs) throws ConfigurationException
	{
		PropertiesConfiguration g = IPManUtil.getConfig() ;
		Map map = new HashMap();
		
		//map.put("black_list", "sssss");
		//g.setProperty("black_list", "1111") ;
		System.out.println(g.getString("black_list"));
	}


}
