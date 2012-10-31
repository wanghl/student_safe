/**
 * Studentrfid.java
 * com.zephyr.studentsafe.bo
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2010-9-7 		lenovo
 *
 * Copyright (c) 2010, TNT All Rights Reserved.
 */

package com.zephyr.studentsafe.bo;

import java.util.Date;
import java.util.Set;

/**
 * ClassName:Studentrfid Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 * 
 * @author lenovo
 * @version
 * @since Ver 1.1
 * @Date 2010-9-7 下午07:28:48
 * 
 * @see
 */
public class Studentrfid implements Comparable{

	private String studentUID;
	private String studentID;
	private String studentName;
	private String rfidCardID;
	private String studentSex;
	private Date studentBirthday;
	private java.util.Date lastScanDate ;
	private String classUID;
	private String teacherUID ;
	private String ClassName ;
	public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	private Set<Studentfamily> studentFamily;
	
	private int serial ;
	
	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	//剩余通话时长
	private String callTimes ;
	//低频卡号
	private String lowCardNumber ;
	
	public String getCallTimes() {
		return callTimes;
	}

	public void setCallTimes(String callTimes) {
		this.callTimes = callTimes;
	}

	public String getLowCardNumber() {
		return lowCardNumber;
	}

	public void setLowCardNumber(String lowCardNumber) {
		this.lowCardNumber = lowCardNumber;
	}

	public int getLastScanState() {
		return lastScanState;
	}

	public void setLastScanState(int lastScanState) {
		this.lastScanState = lastScanState;
	}

	private int lastScanState;
	public String getClassUID() {
		return classUID;
	}

	public void setClassUID(String classUID) {
		this.classUID = classUID;
	}

	public String getTeacherUID() {
		return teacherUID;
	}

	public void setTeacherUID(String teacherUID) {
		this.teacherUID = teacherUID;
	}

	public java.util.Date getLastScanDate() {
		return lastScanDate;
	}

	public void setLastScanDate(java.util.Date lastScanDate) {
		this.lastScanDate = lastScanDate;
	}



	public Set<Studentfamily> getStudentFamily() {
		return studentFamily;
	}

	public void setStudentFamily(Set<Studentfamily> studentFamily) {
		this.studentFamily = studentFamily;
	}

	public String getStudentUID() {
		return studentUID;
	}

	public void setStudentUID(String studentUID) {
		this.studentUID = studentUID;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getRfidCardID() {
		return rfidCardID;
	}

	public void setRfidCardID(String rfidCardID) {
		this.rfidCardID = rfidCardID;
	}

	public String getStudentSex() {
		return studentSex;
	}

	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}

	public Date getStudentBirthday() {
		return studentBirthday;
	}

	public void setStudentBirthday(Date studentBirthday) {
		this.studentBirthday = studentBirthday;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if (rfidCardID == null)
		{
			return 1;
		}
		return Integer.parseInt(rfidCardID);
	}

}
