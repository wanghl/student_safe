package com.zephyr.studentsafe.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.zephyr.studentsafe.bo.Constants;
import com.zephyr.studentsafe.exception.StudentSafeException;

public class SystemProperty {
	// �Ƿ���ն��Żظ�MO

	public static boolean isReceiveMOFromFamily() {
		return StudentSafeUtil.getStringValue(Constants.RECEIVE_MO).equals("1");
	}

	// �Ƿ���ն��Żظ���ת����ָ���ֻ�
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

	// �Ƿ��Ͱ༶���ڶ��Ÿ�������
	public static boolean isSendClassAttendanceMessage() {
		return StudentSafeUtil.getIntValue(Constants.SEND_TEACHER_KQ) == 1;
	}

	// �Ƿ������˶��Ź��˹���

	public static boolean isMessageFilterEnabled() {
		return StudentSafeUtil.getStringValue(Constants.CHECK_MESSAGE_SEND_TIME).equals("1");
	}

	// ���ض��Żظ�ָ����ת���ֻ���
	public static String getReceiveMOMobile() {
		return StudentSafeUtil.getStringValue(Constants.RECEIVE_MO_MOBILE);
	}

	// ���ն��ż��ʱ��
	public static int getReceiveMOTime() {
		return StudentSafeUtil.getIntValue(Constants.RECEIVE_MO_TIME);
	}

	// �ն��Ż�ִRPT���ʱ��

	public static int getReceiveRPTTime() {
		return StudentSafeUtil.getIntValue(Constants.RECEIVE_RPT_TIME);
	}

	// �������Ź���ʱ��

	public static int getMessageBetweenTime() {
		return StudentSafeUtil.getIntValue(Constants.MESSAGE_BETWEEN_TIME);
	}
	
	//�����쳣�Ƿ����֪ͨ����Ա
	
	public static boolean sendNetworkErrorMessage()
	{
		return StudentSafeUtil.getStringValue(Constants.SEND_NETWORK_ERROR_MESSAGE).equals("1") ;
	}
	
	//�յ������� ������֪ͨ����Ա
	
	public static boolean sendSocketOpenedMessge()
	{
		return StudentSafeUtil.getStringValue(Constants.SEND_SOCKET_OPENED_MESSAGE).equals("1");
	}
	//�Ķ������������ű���
	
	public static boolean sendReaderErrorMessage()
	{
		return StudentSafeUtil.getStringValue(Constants.SEND_READER_ERROR_MESSAGE).equals("1") ;
	}
	//��������
		public static void saveSetting(Map map) throws StudentSafeException{
			PropertiesConfiguration g = StudentSafeUtil.getConfig();
			for (Iterator<String> it = map.keySet().iterator();it.hasNext();){
				String key = it.next();
				g.setProperty(key, map.get(key));
				try {
					g.save();
				} catch (ConfigurationException e) {
					throw new StudentSafeException("���������ļ�����"+ e.getLocalizedMessage());
				}
			}
		}
			
	//��������  KEY  VALUE
			
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