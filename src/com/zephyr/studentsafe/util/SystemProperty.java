package com.zephyr.studentsafe.util;

import com.zephyr.studentsafe.bo.Constants;

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

}
