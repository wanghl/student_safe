package com.zephyr.studentsafe.bo;

public class MonitorData {
	
	private int inSchool ;
	public int getInSchool() {
		return inSchool;
	}

	public void setInSchool() {
		inSchool = inSchool + 1;
	}

	public int getOutSchool() {
		return outSchool;
	}

	public void setOutSchool() {
		outSchool = outSchool + 1;
	}

	public int getNotLeave() {
		return notLeave;
	}

	public void setNotLeave() {
		notLeave = notLeave + 1;
	}
	

	private int outSchool; 
	private int notLeave ;
	
	//归零    再坚持一把 。。。 不知道对不对的坚持
	
	public void clear(){
		this.inSchool = 0 ;
		this.outSchool = 0 ;
		this.notLeave = 0 ;
	}

}
