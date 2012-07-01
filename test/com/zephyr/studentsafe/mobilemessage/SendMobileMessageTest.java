package com.zephyr.studentsafe.mobilemessage;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.zephyr.studentsafe.bo.SendMessageResult;


public class SendMobileMessageTest {
	
	@Test
	public void testSendMessage(){
		ISendMobileMessage s = new SendMobileMessage();
		Map<String,String> map = new HashMap<String,String>();
		map.put("phoneNumber", "13488669242");
		map.put("message", "²âÊÔ");
		s.sendMessage(map);
		//Assert.assertTrue(result.getState().equals(SendMobileMessage.SEND_MESSAGE_WAITING));
		
		
	}

}
