/**
 * StudentSafeUtil.java
 * com.zephyr.studentsafe.bo.util
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.comm.CommPortIdentifier;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.Schooleinfor;
import com.zephyr.studentsafe.bo.Studentfamily;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.dao.BaseDAO;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.impl.ProcessQueueData;
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
 * @Date 2010-9-9 下午11:07:28
 * 
 * @see
 */

public class StudentSafeUtil {
	private final static Logger log = Logger.getLogger(StudentSafeUtil.class);

	private static PropertiesConfiguration config = null;
	//随机数种子
	private  static final String allChars = "1234567890abcdef";
	public static PropertiesConfiguration getConfig(){
		if (config == null) {
			try {
				config = new PropertiesConfiguration(
						"resource/studentsafe.properties");
				config.setReloadingStrategy(new FileChangedReloadingStrategy());
				return config;
			} catch (ConfigurationException e) {
				log.error(e);
			}
		} 
		return config;
	}

	public static Date formatDate(String dstr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(dstr);
	}

	public static String getStringValue(String key) {
		String value = null;
			value = getConfig().getString(key);
		
		return value;
	}

	public static int getIntValue(String key) {
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", loc);

		return sdf.format(new Date());
	}

	/**
	 * @return 判断是否周末 0:周日 6:周六
	 */
	public static boolean isWeek() {
		Calendar cd = Calendar.getInstance();
		int day = cd.get(Calendar.DAY_OF_WEEK) - 1;
		log.info(day);
		return (day != 0 && day != 6) ? false : true;
	}

	// 发送短信回执代码及含义
	private final static Map<Integer, String> map = new HashMap<Integer, String>() {
		{
			put(0, "发送成功");
			put(1, "发送内容为空");
			put(2, "发送内容中存在被禁词组");
			put(3, "手机号码不正确");
			put(4, "手机号码为运营商所禁止");
			put(5, "手机号码在黑名单中");
			put(6, "手机号码不在白名单中");
			put(7, "企业欠费");
			put(8, "通讯异常");
			put(101, "系统异常");
			put(102, "短信无法到达手机");
		}

	};

	public static String getMobileRPT(int code) {
		return map.get(code);
	}

	/**
	 * 将从阅读器收到的十六进制数转为十进制数
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> getHexString(String[] str) {
		// TODO: 阅读器传来的卡号第一位表示卡片的电池电量 。0为正常1为电池电量低
		// 需实现监控卡片电量的功能
		List<String> list = new ArrayList<String>();
		String s = "";
		String tigger = "";
		String rfid = "";
		try {
			for (int i = 1; i < str.length; i++) {
				s = str[i];
				// 第一位不为01的 去掉。标签信号01开头，读头心跳包02开头，还有些有乱码的 长度不够的 全部屏蔽
				if (!s.substring(0, 2).equals("01")) {
					continue;
				}
				rfid = s.substring(6, 14);
				tigger = s.substring(16, 18);
				list.add(Integer.valueOf(rfid, 16).toString() + "&" + tigger);
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(s);
		}
		return list;
	}

	/**
	 *<b>功能: 解析卡号信息和进出校门信息。 阅读器上送的是一串数字</b><br>
	 *<br>
	 * 
	 * @author wanghongliang,2012-7-2
	 *@param strs
	 *@return
	 */
	public static List<String> paseStringWidthTriggerinfo(String[] stringArray) {

		StringBuffer buffer = new StringBuffer();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < stringArray.length; i++) {
			buffer.setLength(0);
			buffer.append(stringArray[i]);

			list.add(Integer.valueOf(buffer.substring(4, 8), 16).toString()
					+ "|" + buffer.substring(10, 12).toUpperCase());
		}
		return list;

	}

	// TODO : 这个方法写的真恶心~~~ 没时间了 演示完了要好好重构下。
	@SuppressWarnings("unused")
	public static Map<String, String> makeMessageData(Studentfamily family,
			String studentName, Calendar date, String flag)
			throws StudentSafeException {
		BaseDAO dao = new BaseDAO();
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> ret = new HashMap<String, String>();
		Locale loc = new Locale("zh", "CN");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh点mm分", loc);
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
				message = makeMessage(flag.equals("入校") ? sinfo
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
			FileOutputStream out = new FileOutputStream(new File("message.ftl"));
			out.write(ftlTemplate.getBytes());
			Configuration cfg = new Configuration();
			Template temp = cfg.getTemplate("message.ftl", "gbk");
			temp.process(data, sw);

		} catch (TemplateException e) {
			log.error("组织短信内容出错:" + e.getLocalizedMessage());
			throw new StudentSafeException(-1, "组织短信内容出错");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("组织短信内容出错:" + e.getLocalizedMessage());
			throw new StudentSafeException(-1, "组织短信内容出错");
		}
		return sw.toString();
	}

	public static String[] getSerialPortList() {
		String ports = "未连接,";
		CommPortIdentifier portId;
		Enumeration serList = CommPortIdentifier.getPortIdentifiers();
		while (serList.hasMoreElements()) {
			portId = (CommPortIdentifier) serList.nextElement();
			ports += portId.getName() + ",";
		}
		return ports.split(",");
	}
	
	//生成一个定长随机数  
	public  static String getRandom(){
	
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for( int i = 0 ; i < 32 ; i++ ){
			sb.append(allChars.charAt(random.nextInt(allChars.length())));
		}
		return sb.toString();
	}
	
	//生成MD5
	public static String getMD5String(byte[] b){
		MessageDigest md5;
		StringBuilder sb = new StringBuilder();
		try
		{
			md5 = MessageDigest.getInstance("MD5");
			md5.update(b);
			byte[] tmp = md5.digest();
			for (byte bb:tmp) {
				sb.append(Integer.toHexString(bb&0xff));
			}
		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return sb.toString();
	}


	public static void main(String[] argvs) throws InterruptedException {
		Map map = new HashMap();
		
	}

}
