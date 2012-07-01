package com.zephyr.studentsafe.bo;

import java.util.Date;

public class PrivateMessage {
	
	private String message_id ;
	private String teacher_id;
	private String phone_number ;
	private java.util.Date send_time; 
	private java.util.Date receive_time; 
	private String message_content ;
	private int read_state; 
	private int ask_state ;
	
	public int getAsk_state() {
		return ask_state;
	}
	public void setAsk_state(int askState) {
		ask_state = askState;
	}
	public int getRead_state() {
		return read_state;
	}
	public void setRead_state(int read_state) {
		this.read_state = read_state;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String messageId) {
		message_id = messageId;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacherId) {
		teacher_id = teacherId;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phoneNumber) {
		phone_number = phoneNumber;
	}
	public Date getSend_time() {
		return send_time;
	}
	public void setSend_time(java.util.Date sendTime) {
		send_time = sendTime;
	}
	public Date getReceive_time() {
		return receive_time;
	}
	public void setReceive_time(java.util.Date receiveTiem) {
		receive_time = receiveTiem;
	}
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String messageContent) {
		message_content = messageContent;
	}
	

}
