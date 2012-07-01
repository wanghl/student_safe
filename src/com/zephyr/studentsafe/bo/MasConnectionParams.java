package com.zephyr.studentsafe.bo;

/**
 * 短信初始化连接类
 * @author lenovo
 *
 */
public class MasConnectionParams {
	
	private String ip ;   // mas服务器IP地址
	private String user ; //连接数据库用户名
	private String pswd ; //密码
	private String appId ; //接口名称
	private String dbName  = "mas"; //数据库名，固定为mas ;
	
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
