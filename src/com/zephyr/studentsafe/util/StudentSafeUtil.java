/**
 * StudentSafeUtil.java
 * com.zephyr.studentsafe.bo.util
 *
 * Function�� TODO 
 *
 *   ver     date      		author
 * ��������������������������������������������������������������������
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
 * @Date 2010-9-9 ����11:07:28
 * 
 * @see
 */

public class StudentSafeUtil {
	private final static Logger log = Logger.getLogger(StudentSafeUtil.class);

	private static PropertiesConfiguration config = null;
	//���������
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��", loc);

		return sdf.format(new Date());
	}

	/**
	 * @return �ж��Ƿ���ĩ 0:���� 6:����
	 */
	public static boolean isWeek() {
		Calendar cd = Calendar.getInstance();
		int day = cd.get(Calendar.DAY_OF_WEEK) - 1;
		log.info(day);
		return (day != 0 && day != 6) ? false : true;
	}

	// ���Ͷ��Ż�ִ���뼰����
	private final static Map<Integer, String> map = new HashMap<Integer, String>() {
		{
			put(0, "���ͳɹ�");
			put(1, "��������Ϊ��");
			put(2, "���������д��ڱ�������");
			put(3, "�ֻ����벻��ȷ");
			put(4, "�ֻ�����Ϊ��Ӫ������ֹ");
			put(5, "�ֻ������ں�������");
			put(6, "�ֻ����벻�ڰ�������");
			put(7, "��ҵǷ��");
			put(8, "ͨѶ�쳣");
			put(101, "ϵͳ�쳣");
			put(102, "�����޷������ֻ�");
		}

	};

	public static String getMobileRPT(int code) {
		return map.get(code);
	}

	/**
	 * �����Ķ����յ���ʮ��������תΪʮ������
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> getHexString(String[] str) {
		// TODO: �Ķ��������Ŀ��ŵ�һλ��ʾ��Ƭ�ĵ�ص��� ��0Ϊ����1Ϊ��ص�����
		// ��ʵ�ּ�ؿ�Ƭ�����Ĺ���
		List<String> list = new ArrayList<String>();
		String s = "";
		String tigger = "";
		String rfid = "";
		try {
			for (int i = 1; i < str.length; i++) {
				s = str[i];
				// ��һλ��Ϊ01�� ȥ������ǩ�ź�01��ͷ����ͷ������02��ͷ������Щ������� ���Ȳ����� ȫ������
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
	 *<b>����: ����������Ϣ�ͽ���У����Ϣ�� �Ķ������͵���һ������</b><br>
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

	// TODO : �������д�������~~~ ûʱ���� ��ʾ����Ҫ�ú��ع��¡�
	@SuppressWarnings("unused")
	public static Map<String, String> makeMessageData(Studentfamily family,
			String studentName, Calendar date, String flag)
			throws StudentSafeException {
		BaseDAO dao = new BaseDAO();
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> ret = new HashMap<String, String>();
		Locale loc = new Locale("zh", "CN");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��hh��mm��", loc);
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
				message = makeMessage(flag.equals("��У") ? sinfo
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
			log.error("��֯�������ݳ���:" + e.getLocalizedMessage());
			throw new StudentSafeException(-1, "��֯�������ݳ���");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("��֯�������ݳ���:" + e.getLocalizedMessage());
			throw new StudentSafeException(-1, "��֯�������ݳ���");
		}
		return sw.toString();
	}

	public static String[] getSerialPortList() {
		String ports = "δ����,";
		CommPortIdentifier portId;
		Enumeration serList = CommPortIdentifier.getPortIdentifiers();
		while (serList.hasMoreElements()) {
			portId = (CommPortIdentifier) serList.nextElement();
			ports += portId.getName() + ",";
		}
		return ports.split(",");
	}
	
	//����һ�����������  
	public  static String getRandom(){
	
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for( int i = 0 ; i < 32 ; i++ ){
			sb.append(allChars.charAt(random.nextInt(allChars.length())));
		}
		return sb.toString();
	}
	
	//����MD5
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
