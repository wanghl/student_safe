package com.zephyr.studentsafe.bo;

import java.util.Date;

public class ReaderB {
	
	private String objuid ;
	private String rfid ;
	private Date scantime;
	private String minTime ;
	private String maxTime ;
	public String getMinTime() {
		return minTime;
	}
	public void setMinTime(String minTime) {
		this.minTime = minTime;
	}
	public String getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}
	public Date getScantime() {
		return scantime;
	}
	public void setScantime(Date scantime) {
		this.scantime = scantime;
	}
	public String getObjuid() {
		return objuid;
	}
	public void setObjuid(String objuid) {
		this.objuid = objuid;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

}
