package com.zephyr.studentsafe.mobilemessage;

import org.apache.log4j.Logger;

import com.jasson.im.api.APIClient;

import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.sudentsafe.exception.StudentSafeException;

/**
 * ����һ��������APIClient 
 * @author lenovo
 *
 */
public class MobileMessageHandler {
	private final static Logger log = Logger.getLogger(MobileMessageHandler.class);
	private static APIClient handler = null ;
	private static String IP ;
	private static String USER ;
	private static String PSWD ;
	private static String APPID ;
	private static String DBNAME ; 
	
	public synchronized static APIClient getInstance() throws StudentSafeException{
			if ( handler == null ){
				handler = new APIClient();
				init() ;
				return handler ;
			}
			else{
				return handler ;
			}
	}
	
	/**
	 * MAS�����ʼ������ ��
	 * @param handler 
	 * @param params
	 * @throws StudentSafeException
	 */
	private static void init() throws StudentSafeException{
		IP = StudentSafeUtil.getStringValue("ip");
		USER = StudentSafeUtil.getStringValue("user");
		PSWD = StudentSafeUtil.getStringValue("pswd");
		APPID = StudentSafeUtil.getStringValue("appid");
		DBNAME = StudentSafeUtil.getStringValue("dbname");
		int connectRe = handler.init(IP, USER, PSWD, APPID, DBNAME) ;
		if(connectRe == APIClient.IMAPI_SUCC)
        	log.info("���ŷ��ͷ����ʼ���ɹ�");
        else if(connectRe == APIClient.IMAPI_CONN_ERR){
        	log.info("����MAS������ʧ��");
        	throw new StudentSafeException(APIClient.IMAPI_CONN_ERR,"����MAS������ʧ��");
        }
        else if(connectRe == APIClient.IMAPI_API_ERR){
        	log.info("MAS apiID������");
        	throw new StudentSafeException(APIClient.IMAPI_CONN_ERR,"MAS apiID������");
        }
	}
	
	public static void main(String[] argvs) throws StudentSafeException{
		APIClient a = MobileMessageHandler.getInstance();
		APIClient b = MobileMessageHandler.getInstance();
		if ( a == b ){
			System.out.println("ok");
		}
		
	}
}
