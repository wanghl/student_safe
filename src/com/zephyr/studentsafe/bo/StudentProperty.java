package com.zephyr.studentsafe.bo;

import java.util.Date;

public class StudentProperty {
	
	private String objUID;
	private String linkStudent ;
	private Date lastScanDate ;
	private int lastScanState;
	private String remainingTime; //剩余通话时长
	
	public int getLastScanState() {
		return lastScanState;
	}
	public void setLastScanState(int lastScanState) {
		this.lastScanState = lastScanState;
	}
	
	
	public String getObjUID() {
		return objUID;
	}
	public void setObjUID(String objuid) {
		this.objUID = objuid;
	}
	public String getLinkStudent() {
		return linkStudent;
	}
	public void setLinkStudent(String linkStudent) {
		this.linkStudent = linkStudent;
	}
	public Date getLastScanDate() {
		return lastScanDate;
	}
	public void setLastScanDate(Date lastScanDate) {
		this.lastScanDate = lastScanDate;
	}
	public String getRemainingTime() {
		return remainingTime;
	}
	public void setRemainingTime(String remainingTime) {
		this.remainingTime = remainingTime;
	}
	
	

}
