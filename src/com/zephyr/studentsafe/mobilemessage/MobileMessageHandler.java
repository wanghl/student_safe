package com.zephyr.studentsafe.mobilemessage;

import org.apache.log4j.Logger;

import com.jasson.im.api.APIClient;

import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.studentsafe.exception.StudentSafeException;

/**
 * 创建一个单例的APIClient 
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
	 * MAS服务初始化方法 。
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
        	log.info("短信发送服务初始化成功");
        else if(connectRe == APIClient.IMAPI_CONN_ERR){
        	log.info("连接MAS服务器失败");
        	throw new StudentSafeException(APIClient.IMAPI_CONN_ERR,"连接MAS服务器失败");
        }
        else if(connectRe == APIClient.IMAPI_API_ERR){
        	log.info("MAS apiID不存在");
        	throw new StudentSafeException(APIClient.IMAPI_CONN_ERR,"MAS apiID不存在");
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
