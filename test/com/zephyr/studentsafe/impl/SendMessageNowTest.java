package com.zephyr.studentsafe.impl;
import org.junit.Test;
import com.zephyr.studentsafe.bo.StudentExt;
import com.zephyr.studentsafe.mobilemessage.SendMessageNow;


public class SendMessageNowTest {
	
	@Test
	public void testSendMessage(){
		SendMessageNow task = new SendMessageNow();
		StudentExt s = new StudentExt();
		s.setFristScanArea("A");
		s.setLastScanArea("B");
		s.setRfidCardID("209247");
		task.sendMessage(s);
	}

}
