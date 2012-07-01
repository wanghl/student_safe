package com.zephyr.studentsafe.bo;

import java.util.Calendar;

public class Student implements Comparable {

	/**
	 * 
	 */
	
	private String cardID;  //rfid卡号
	private int inflag = -1 ; // 0代表A探测器，1代表B探测器
	private int outflag = -1 ;
	private int readerAstate = -1;
	private int readerBstate = -1;
	private int noTimes_A = 0; //A探头对本ID未扫描到的次数
	private int noTimes_B = 0; //B探头对本ID未扫描到的次数
	
	private Calendar inSchooleTime ;
	private Calendar outSchooleTime ;

	public String getInOutValue(){
		if (inflag == 0 && outflag == 1){
			return "入校";
		}
		if (inflag == 1 && outflag == 0){
			return "出校";
		}
		if (inflag == 1 && outflag == 1){
			
			return "进入B探测器未进入A探测器，未离开学校" ;
		}
		if (inflag == 0 && outflag == 0){
			
			return "进入A探测器未进入B探测器，未进入学校" ;
		}
		return "不确定" ;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public int getInflag() {
		return inflag;
	}

	public void setInflag(int inflag) {
		this.inflag = inflag;
	}

	public int getOutflag() {
		return outflag;
	}

	public void setOutflag(int outflag) {
		this.outflag = outflag;
	}

	public int getReaderAstate() {
		return readerAstate;
	}

	public void setReaderAstate(int readerAstate) {
		this.readerAstate = readerAstate;
	}

	public int getReaderBstate() {
		return readerBstate;
	}

	public void setReaderBstate(int readerBstate) {
		this.readerBstate = readerBstate;
	}

	public int getNoTimes_A() {
		return noTimes_A;
	}

	public void setNoTimes_A(int noTimesA) {
		noTimes_A = noTimesA;
	}

	public int getNoTimes_B() {
		return noTimes_B;
	}

	public void setNoTimes_B(int noTimesB) {
		noTimes_B = noTimesB;
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
		// TODO Auto-generated method stub
		return 0;
	}


}
