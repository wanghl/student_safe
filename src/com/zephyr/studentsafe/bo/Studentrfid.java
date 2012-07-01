/**
 * Studentrfid.java
 * com.zephyr.studentsafe.bo
 *
 * Function£º TODO 
 *
 *   ver     date      		author
 * ©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤
 *   		 2010-9-7 		lenovo
 *
 * Copyright (c) 2010, TNT All Rights Reserved.
 */

package com.zephyr.studentsafe.bo;

import java.sql.Date;
import java.util.Set;

/**
 * ClassName:Studentrfid Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 * 
 * @author lenovo
 * @version
 * @since Ver 1.1
 * @Date 2010-9-7 ÏÂÎç07:28:48
 * 
 * @see
 */
public class Studentrfid {

	private String studentUID;
	private String studentID;
	private String studentName;
	private String rfidCardID;
	private String studentSex;
	private Date studentBirthday;
	private java.util.Date lastScanDate ;
	private String classUID;
	private String teacherUID ;
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

	private Set<Studentfamily> studentFamily;

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

}
