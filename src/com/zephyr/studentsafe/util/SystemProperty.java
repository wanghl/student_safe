package com.zephyr.studentsafe.util;

import com.zephyr.studentsafe.bo.Constants;

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

}
