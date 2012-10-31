package com.zephyr.studentsafe.bo;

public class Constants {
	
	public static final int SEND_MESSAGE_WAIT_RPT = 1; //�����ѷ��ͣ��ȴ���ִ
	public static final int SEND_MESSAGE_FAIL = 2; //���ŷ���ʧ��
	public static final int SEND_MESSAGE_SUCCESS = 0; //��ִ�ɹ���
	public static final int MO_MESSAGE_UN_READ = 3 ;//δ��ȡ���ֻ��ظ���Ϣ
	public static final int MO_MESSAGE_READED = 4 ; //�Ѿ���ȡ�����ֻ��ظ���Ϣ
	
	//-----------------------------------------------------
	public static final int DB_INIT_ERROR = 1001 ; //���ݿ��ʼ������
	
	//-----------------------------------------------------
	
	public static final String MAS_IP = "ip"; //MAS��������ַ
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
	public static final String SEND_TEACHER_KQ = "send_teacher_kq"; //����ʦ������У�Ű༶����
	public static final String TIME_TO_SCHOOL = "time_to_school"; //������ѧʱ��
	public static final String TIME_TO_SCHOOL_PM = "time_to_school_pm"; //������ѧʱ��.
	
	///.-------------------------------------------------------
	//messagelog messagetype�ֶ� ����Ϣ����
	public static final String MESSAGE_TYPE_IN_SCHOOLE = "0";  //��У֪ͨ
	public static final String MESSAGE_TYPE_OUT_SCHOOLE = "1";  //��У֪ͨ
	public static final String MESSAGE_TYPE_HONE_WORK = "2";   //��ͥ��ҵ
	public static final String MESSAGE_TYPE_KQ = "3"; 			// ����
	public static final String MESSAGE_TYPE_BULLETIN = "4"; 	//֪ͨ����
	public static final int  MESSAGE_TYPE_MO = 5; 	//�ֻ��ظ����� ���ҳ��ظ�)
	
	
	
	

}
