/**
 * StudentSafeUtil.java
 * com.zephyr.studentsafe.bo.util
 *
 * Function£º TODO 
 *
 *   ver     date      		author
 * ©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤
 *   		 2010-9-9 		lenovo
 *
 * Copyright (c) 2010, TNT All Rights Reserved.
 */

package com.zephyr.studentsafe.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.Schooleinfor;
import com.zephyr.studentsafe.bo.Student;
import com.zephyr.studentsafe.bo.Studentfamily;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.dao.BaseDAO;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.impl.ProcessQueueDataExt;
import com.zephyr.studentsafe.impl.StudentMap;
import com.zephyr.studentsafe.impl.StudentReaderQueue;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * ClassName:StudentSafeUtil Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 * 
 * @author lenovo
 * @version
 * @since Ver 1.1
 * @Date 2010-9-9 ÏÂÎç11:07:28
 * 
 * @see
 */

public class StudentSafeUtil {
	private final static Logger log = Logger.getLogger(StudentSafeUtil.class);

	public static PropertiesConfiguration getConfig()
			throws StudentSafeException {
		try {
			PropertiesConfiguration config = new PropertiesConfiguration(
					"resource/studentsafe.properties");
			config.setReloadingStrategy(new FileChangedReloadingStrategy());
			return config;
		} catch (ConfigurationException e) {
			throw new StudentSafeException(e.getLocalizedMessage());
		}
	}
	
	public static Date formatDate(String dstr) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(dstr);
	}

	public static String getStringValue(String key) throws StudentSafeException {
		return getConfig().getString(key);
	}

	public static int getIntValue(String key) throws StudentSafeException {
		return getConfig().getInt(key);
	}

	public static String getCurrentDateAll() {
		Locale loc = new Locale("zh", "CN");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss", loc);

		return sdf.format(new Date());
	}
	public static String getCurrentDateAllformated() {
		Locale loc = new Locale("zh", "CN");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", loc);
		
		return sdf.format(new Date());
	}

	public static String getCurrentDate() {
		Locale loc = new Locale("zh", "CN");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", loc);

		return sdf.format(new Date());
	}

	public static String getCurrentDateFormat() {
		Locale loc = new Locale("zh", "CN");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyÄêMMÔÂddÈÕ", loc);

		return sdf.format(new Date());
	}

	/**
	 * @return ÅÐ¶ÏÊÇ·ñÖÜÄ© 0:ÖÜÈÕ 6:ÖÜÁù
	 */
	public static boolean isWeek() {
		Calendar cd = Calendar.getInstance();
		int day = cd.get(Calendar.DAY_OF_WEEK) - 1;
		log.info(day);
		return (day != 0 && day != 6) ? false : true;
	}

	// ·¢ËÍ¶ÌÐÅ»ØÖ´´úÂë¼°º¬Òå
	private final static Map<Integer, String> map = new HashMap<Integer, String>() {
		{
			put(0, "·¢ËÍ³É¹¦");
			put(1, "·¢ËÍÄÚÈÝÎª¿Õ");
			put(2, "·¢ËÍÄÚÈÝÖÐ´æÔÚ±»½û´Ê×é");
			put(3, "ÊÖ»úºÅÂë²»ÕýÈ·");
			put(4, "ÊÖ»úºÅÂëÎªÔËÓªÉÌËù½ûÖ¹");
			put(5, "ÊÖ»úºÅÂëÔÚºÚÃûµ¥ÖÐ");
			put(6, "ÊÖ»úºÅÂë²»ÔÚ°×Ãûµ¥ÖÐ");
			put(7, "ÆóÒµÇ··Ñ");
			put(8, "Í¨Ñ¶Òì³£");
			put(101, "ÏµÍ³Òì³£");
			put(102, "¶ÌÐÅÎÞ·¨µ½´ïÊÖ»ú");
		}

	};

	public static String getMobileRPT(int code) {
		return map.get(code);
	}

	/**
	 * ½«´ÓÔÄ¶ÁÆ÷ÊÕµ½µÄÊ®Áù½øÖÆÊý×ªÎªÊ®½øÖÆÊý
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> getHexString(String[] str) {
		// TODO: ÔÄ¶ÁÆ÷´«À´µÄ¿¨ºÅµÚÒ»Î»±íÊ¾¿¨Æ¬µÄµç³ØµçÁ¿ ¡£0ÎªÕý³£1Îªµç³ØµçÁ¿µÍ
		// ÐèÊµÏÖ¼à¿Ø¿¨Æ¬µçÁ¿µÄ¹¦ÄÜ
		List<String> list = new ArrayList<String>();
		String s = "";
		try {
			for (int i = 0; i < str.length; i++) {
				s = str[i];
				if (s.length() == 10) {
					s = s.substring(3, 9);
					list.add(Integer.valueOf(s, 16).toString());
				} else if (s.length() == 9) {
					s = s.substring(3, 9);
					list.add(Integer.valueOf(s, 16).toString());
				} else if (s.length() == 6){
					s = s.substring(0, s.length());
					list.add(Integer.valueOf(s, 16).toString());
				}else if (s.length() == 7){
					s = s.substring(0, s.length() - 1);
					list.add(Integer.valueOf(s, 16).toString());
				}
				// if ( str[i].length() != 6){
				// continue;
				// }
				// list.add(Integer.valueOf(str[i].trim(), 16).toString());
			}
		} catch (NumberFormatException e1) {
			log.error("ÊÕµ½´íÎó¸ñÊ½Êý¾Ý£¬¶ªÆú¡£" + s);
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		return list;
	}

	// TODO : Õâ¸ö·½·¨Ð´µÄÕæ¶ñÐÄ~~~ Ã»Ê±¼äÁË ÑÝÊ¾ÍêÁËÒªºÃºÃÖØ¹¹ÏÂ¡£
	@SuppressWarnings("unused")
	public static Map<String, String> makeMessageData(Studentfamily family,
			String studentName, Calendar date, String flag)
			throws StudentSafeException {
		BaseDAO dao = new BaseDAO();
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> ret = new HashMap<String, String>();
		Locale loc = new Locale("zh", "CN");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyÄêMMÔÂddÈÕhhµãmm·Ö", loc);
		String message = null;
		Schooleinfor sinfo = (Schooleinfor) dao.get(Schooleinfor.class);
		if (sinfo != null) {
			map.put("familyName", family.getFamilyName());
			map.put("familySex", family.getFamilySex());
			map.put("studentName", studentName);
			map.put("date", sdf.format(date.getTime()));
			map.put("schooleName", sinfo.getSchooleName());
			map.put("headMaster", sinfo.getHeadMaster());
			try {
				message = makeMessage(flag.equals("ÈëÐ£") ? sinfo
						.getInSchooleMsg() : sinfo.getOutSchooleMsg(), map);
			} catch (StudentSafeException e) {
				throw new StudentSafeException(-1, e.getLocalizedMessage());
			}

		}
		ret.put("phoneNumber", family.getFamilyPhone());
		ret.put("message", message);
		return ret;
	}

	public static String makeMessage(String ftlTemplate, Map data)
			throws StudentSafeException {
		/* Merge data model with template */
		StringWriter sw = new StringWriter();
		try {
			FileOutputStream out = new FileOutputStream(new File("ftl.dat"));
			out.write(ftlTemplate.getBytes());
			Configuration cfg = new Configuration();
			Template temp = cfg.getTemplate("ftl.dat", "gbk");
			temp.process(data, sw);

		} catch (TemplateException e) {
			log.error("×éÖ¯¶ÌÐÅÄÚÈÝ³ö´í:" + e.getLocalizedMessage());
			throw new StudentSafeException(-1, "×éÖ¯¶ÌÐÅÄÚÈÝ³ö´í");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("×éÖ¯¶ÌÐÅÄÚÈÝ³ö´í:" + e.getLocalizedMessage());
			throw new StudentSafeException(-1, "×éÖ¯¶ÌÐÅÄÚÈÝ³ö´í");
		}
		return sw.toString();
	}

	public static void main(String[] argvs) throws InterruptedException {
		//Integer s = new Integer(130);
		System.out.println(Integer.toHexString(255));
	}
	
	
}
