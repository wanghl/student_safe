package com.zephyr.studentsafe.bo;

import java.util.Calendar;

public class StudentExt implements Comparable{

	private String objuid; 
	

	private String rfidCardID; //����
	private Calendar readerATime = null;  //����A̽����ʱ��
	private Calendar readerBTime = null;  //����B̽����ʱ��
	private Calendar inSchooleTime;
	private Calendar outSchooleTime;
	private Calendar lastUpdateTime ;
	
	private String fristScanArea ;
	private String lastScanArea ;
	
	private int readerAnoScanTime ; //δ��⵽����ͳ�ơ�����5��û��ɨ�赽ͬһ�ſ� ��Ϊѧ���Ѿ��뿪�ü�ⷶΧ��
	private int readerBnoScanTime ;
	private int readerCnoScanTime ;
	
	public int getReaderCnoScanTime() {
		return readerCnoScanTime;
	}

	public void setReaderCnoScanTime(int readerCnoScanTime) {
		this.readerCnoScanTime = readerCnoScanTime;
	}

	public String getObjuid() {
		return objuid;
	}

	public void setObjuid(String objuid) {
		this.objuid = objuid;
	}
	public int getReaderAnoScanTime() {
		return readerAnoScanTime;
	}

	public void setReaderAnoScanTime(int readerAnoScanTime) {
		this.readerAnoScanTime = readerAnoScanTime;
	}

	public int getReaderBnoScanTime() {
		return readerBnoScanTime;
	}

	public void setReaderBnoScanTime(int readerBnoScanTime) {
		this.readerBnoScanTime = readerBnoScanTime;
	}


	
	public String getFristScanArea() {
		return fristScanArea;
	}

	public void setFristScanArea(String fristScanArea) {
		this.fristScanArea = fristScanArea;
	}

	public String getLastScanArea() {
		return lastScanArea;
	}

	public void setLastScanArea(String lastScanArea) {
		this.lastScanArea = lastScanArea;
	}

	public String getEvent(){
		String flg = null ;
		if ((fristScanArea.equals("A") && lastScanArea.equals("B")) || (fristScanArea.equals("A") && lastScanArea.equals("C"))){
			flg = "��У" ;
		}
		if ((fristScanArea.equals("B") && lastScanArea.equals("A")) || (fristScanArea.equals("C") && lastScanArea.equals("A"))){
			flg =  "��У" ;
		}
		return flg;
	}
	
	public Calendar getEventTime(){
		if (getEvent().equals("��У")){
			return readerBTime ;
		}else{
			return readerATime ;
		}
	}
	public Calendar getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Calendar lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getRfidCardID() {
		return rfidCardID;
	}

	public void setRfidCardID(String rfidCardID) {
		this.rfidCardID = rfidCardID;
	}

	public Calendar getReaderATime() {
		return readerATime;
	}

	public void setReaderATime(Calendar readerATime) {
		this.readerATime = readerATime;
	}

	public Calendar getReaderBTime() {
		return readerBTime;
	}

	public void setReaderBTime(Calendar readerBTime) {
		this.readerBTime = readerBTime;
	}

	public Calendar getInSchooleTime() {
		return inSchooleTime;
	}

	public void setInSchooleTime(Calendar inSchooleTime) {
		this.inSchooleTime = inSchooleTime;
	}

	public Calendar getOutSchooleTime() {
		return outSchooleTime;
	}

	public void setOutSchooleTime(Calendar outSchooleTime) {
		this.outSchooleTime = outSchooleTime;
	}

	@Override
	public int compareTo(Object arg0) {
		StudentExt s = (StudentExt) arg0;
		if ( getLastUpdateTime().before(s.getLastUpdateTime())){
			return 0 ;
		}
		return 1;
	}

}
