package com.zephyr.sutdentsafe.mobilemessage;

import com.jasson.im.api.APIClient;
import com.jasson.im.api.MOItem;
import com.zephyr.studentsafe.mobilemessage.MobileMessageHandler;
import com.zephyr.sudentsafe.exception.StudentSafeException;

public class ReceiveMO {
	
	public static void main(String[] argvs) throws StudentSafeException{
		APIClient handler = MobileMessageHandler.getInstance();
		MOItem[] it = handler.receiveSM();
		for ( MOItem i : it){
			System.out.println(i.getContent());
		}
		
	}

}
