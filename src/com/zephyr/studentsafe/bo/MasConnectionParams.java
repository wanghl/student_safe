package com.zephyr.studentsafe.bo;

/**
 * ���ų�ʼ��������
 * @author lenovo
 *
 */
public class MasConnectionParams {
	
	private String ip ;   // mas������IP��ַ
	private String user ; //�������ݿ��û���
	private String pswd ; //����
	private String appId ; //�ӿ�����
	private String dbName  = "mas"; //���ݿ������̶�Ϊmas ;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

}
