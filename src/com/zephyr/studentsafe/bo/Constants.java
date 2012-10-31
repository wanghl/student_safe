package com.zephyr.studentsafe.bo;

public class Constants {
	
	public static final int SEND_MESSAGE_WAIT_RPT = 1; //短信已发送，等待回执
	public static final int SEND_MESSAGE_FAIL = 2; //短信发送失败
	public static final int SEND_MESSAGE_SUCCESS = 0; //回执成功。
	public static final int MO_MESSAGE_UN_READ = 3 ;//未读取的手机回复消息
	public static final int MO_MESSAGE_READED = 4 ; //已经读取过的手机回复消息
	
	//-----------------------------------------------------
	public static final int DB_INIT_ERROR = 1001 ; //数据库初始化错误
	
	//-----------------------------------------------------
	
	public static final String MAS_IP = "ip"; //MAS服务器地址
	public static final String MAS_USER_NAME ="user";
	public static final String MAS_USER_PASSWORD="pswd";
	public static final String MAS_DB_NAME = "dbname";
	public static final String MAS_APP="appid";
	
	//------------------------------------------------------
	public static final String SERIAL_PORT_A="serialPortId_A";
	public static final String SERIAL_PORT_B="serialPortId_B";
	public static final String SERIAL_PORT_C="serialPortId_C";
	public static final String BAUDRATE="baudrate";
	public static final String NO_SCAN_TIMES="no_scan_times";
	public static final String RECEIVE_RPT_TIME="receive_rpt_time";
	public static final String SEND_TEACHER_KQ = "send_teacher_kq"; //给教师发进出校门班级考情
	public static final String TIME_TO_SCHOOL = "time_to_school"; //早上上学时间
	public static final String TIME_TO_SCHOOL_PM = "time_to_school_pm"; //下午上学时间.
	
	///.-------------------------------------------------------
	//messagelog messagetype字段 ，信息类型
	public static final String MESSAGE_TYPE_IN_SCHOOLE = "0";  //入校通知
	public static final String MESSAGE_TYPE_OUT_SCHOOLE = "1";  //离校通知
	public static final String MESSAGE_TYPE_HONE_WORK = "2";   //家庭作业
	public static final String MESSAGE_TYPE_KQ = "3"; 			// 考勤
	public static final String MESSAGE_TYPE_BULLETIN = "4"; 	//通知公告
	public static final int  MESSAGE_TYPE_MO = 5; 	//手机回复短信 （家长回复)
	
	
	
	

}
