package com.zephyr.studentsafe.bo;

public class Messagelog {

	private String messageLogUID;

	public String getMessageLogUID() {
		return messageLogUID;
	}

	public void setMessageLogUID(String messageLogUID) {
		this.messageLogUID = messageLogUID;
	}

	public String getMessageDesc() {
		return messageDesc;
	}

	public void setMessageDesc(String messageDesc) {
		this.messageDesc = messageDesc;
	}

	public String getSendPhone() {
		return sendPhone;
	}

	public void setSendPhone(String sendPhone) {
		this.sendPhone = sendPhone;
	}

	public java.util.Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(java.util.Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSmid() {
		return smid;
	}

	public void setSmid(String smid) {
		this.smid = smid;
	}

	private String messageDesc;
	private String sendPhone;
	private java.util.Date sendTime;
	private String memo;
	private int state;
	private String smid;
	private int messageType;
	private String studentName;
	private String rfidcardid;
	private String className;
	private String teacher;
	private String sendteacher ;
	public String getSendteacher() {
		return sendteacher;
	}

	public void setSendteacher(String sendteacher) {
		this.sendteacher = sendteacher;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getRfidcardid() {
		return rfidcardid;
	}

	public void setRfidcardid(String rfidcardid) {
		this.rfidcardid = rfidcardid;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

}
