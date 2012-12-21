package com.zephyr.studentsafe.bo;


public class StudentExt {

	private String objuid; 
	

	private String rfidCardID; //卡号
	
	private int readerAnoScanTime ; //未检测到次数统计。
	private int readerBnoScanTime ;
	private int readerCnoScanTime ;
	
	private String remoteIp ;
	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	private int noscantimes = 0;
	private String direction ;//进出校门方向
	
	private String firstArea  ; //首次进入区域
	private String lastArea ; //最终区域
	
	private int receiveTimes = 1 ;

	public int getReceiveTimes() {
		return receiveTimes;
	}

	public void setReceiveTimes(int receiveTimes) {
		this.receiveTimes = receiveTimes;
	}

	public String getFirstArea() {
		return firstArea;
	}

	public void setFirstArea(String firstArea) {
		this.firstArea = firstArea;
	}



	
	public String getLastArea() {
		return lastArea;
	}

	public void setLastArea(String lastArea) {
		this.lastArea = lastArea;
	}

	public int getNoscantimes() {
		return noscantimes;
	}

	public void setNoscantimes(int noscantimes) {
		this.noscantimes = noscantimes;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}



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


	

	public String getEvent(){
		String flg = null ;
		if(firstArea.equals(lastArea) || lastArea == null){
			if (firstArea.equals("12")){
				flg = "出校" ;
			}else {
				flg = "入校";
			}
		}
		else {
			flg = "进出点相同，忽略";
		}
		return flg;
	}
	
	public String getInOutEvent() {
		if (direction.equals("AB")){
			return "入校" ;
		}else {
			return "出校" ;
		}
	}
	


	public String getRfidCardID() {
		return rfidCardID;
	}

	public void setRfidCardID(String rfidCardID) {
		this.rfidCardID = rfidCardID;
	}


	
}
