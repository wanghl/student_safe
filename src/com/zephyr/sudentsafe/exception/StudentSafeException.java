package com.zephyr.sudentsafe.exception;

public class StudentSafeException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int error_code ;
	private String error_message  ;
	
	public StudentSafeException(int errCod,String errMsg){
		super(errMsg);
		error_code = errCod ;
		error_message = errMsg ;
	}
	
	public StudentSafeException(String errMsg){
		super(errMsg);
		error_message = errMsg ;
	}
	
	public  String getMessageDetail(){
		return error_message ;
	}
	
	public int getErrorCode(){
		return error_code ;
	}

}
