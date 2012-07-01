package com.zephyr.studentsafe.bo;

import java.util.Date;


/**
 * @author lenovo
 *“Ï≥£ø®∫≈∂‘œÛ
 */
public class CardException {
	private String objUID ;
	private Date scanDate;
	private String event ;
	private String memo ;
	private String cardid ;
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getObjUID() {
		return objUID;
	}
	public void setObjUID(String objUID) {
		this.objUID = objUID;
	}
	public Date getScanDate() {
		return scanDate;
	}
	public void setScanDate(Date scanDate) {
		this.scanDate = scanDate;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

}
