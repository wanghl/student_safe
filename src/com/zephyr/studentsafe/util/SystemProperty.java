package com.zephyr.studentsafe.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.zephyr.studentsafe.bo.Constants;
import com.zephyr.studentsafe.exception.StudentSafeException;

public class SystemProperty {
	// 是否接收短信回复MO

	public static boolean isReceiveMOFromFamily() {
		return StudentSafeUtil.getStringValue(Constants.RECEIVE_MO).equals("1");
	}

	// 是否接收短信回复并转发到指定手机
	public static boolean isReceiveMO() {

		if (StudentSafeUtil.getStringValue(Constants.RECEIVE_MO).equals("1")
				&&
				!StudentSafeUtil.getStringValue(Constants.RECEIVE_MO_MOBILE).equals(""))
		{
			return true;
		} else
		{
			return false;
		}
	}

	// 是否发送班级考勤短信给班主任
	public static boolean isSendClassAttendanceMessage() {
		return StudentSafeUtil.getIntValue(Constants.SEND_TEACHER_KQ) == 1;
	}

	// 是否启用了短信过滤功能

	public static boolean isMessageFilterEnabled() {
		return StudentSafeUtil.getStringValue(Constants.CHECK_MESSAGE_SEND_TIME).equals("1");
	}

	// 返回短信回复指定的转发手机号
	public static String getReceiveMOMobile() {
		return StudentSafeUtil.getStringValue(Constants.RECEIVE_MO_MOBILE);
	}

	// 回收短信间隔时间
	public static int getReceiveMOTime() {
		return StudentSafeUtil.getIntValue(Constants.RECEIVE_MO_TIME);
	}

	// 收短信回执RPT间隔时间

	public static int getReceiveRPTTime() {
		return StudentSafeUtil.getIntValue(Constants.RECEIVE_RPT_TIME);
	}

	// 进出短信过滤时间

	public static int getMessageBetweenTime() {
		return StudentSafeUtil.getIntValue(Constants.MESSAGE_BETWEEN_TIME);
	}
	
	//网络异常是否短信通知管理员
	
	public static boolean sendNetworkErrorMessage()
	{
		return StudentSafeUtil.getStringValue(Constants.SEND_NETWORK_ERROR_MESSAGE).equals("1") ;
	}
	
	//收到新连接 ，短信通知管理员
	
	public static boolean sendSocketOpenedMessge()
	{
		return StudentSafeUtil.getStringValue(Constants.SEND_SOCKET_OPENED_MESSAGE).equals("1");
	}
	//阅读器无心跳短信报警
	
	public static boolean sendReaderErrorMessage()
	{
		return StudentSafeUtil.getStringValue(Constants.SEND_READER_ERROR_MESSAGE).equals("1") ;
	}
	//保存配置
		public static void saveSetting(Map map) throws StudentSafeException{
			PropertiesConfiguration g = StudentSafeUtil.getConfig();
			for (Iterator<String> it = map.keySet().iterator();it.hasNext();){
				String key = it.next();
				g.setProperty(key, map.get(key));
				try {
					g.save();
				} catch (ConfigurationException e) {
					throw new StudentSafeException("保存配置文件错误："+ e.getLocalizedMessage());
				}
			}
		}
			
	//保存配置  KEY  VALUE
			
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

		
}