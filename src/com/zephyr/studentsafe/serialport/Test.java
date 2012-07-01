package com.zephyr.studentsafe.serialport;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

import com.zephyr.studentsafe.impl.StudentReaderQueue;
import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.sudentsafe.exception.StudentSafeException;

public class Test {
	static Enumeration portList;
	static CommPortIdentifier portId;
	static InputStream inputStream_A;
	static SerialPort serialPort_A;
	public static void main(String...strings ) throws PortInUseException, StudentSafeException, IOException, UnsupportedCommOperationException, InterruptedException {
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()){
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL){
				if (portId.getName().equals("COM19")) {
					serialPort_A = (SerialPort) portId.open("SerialReader", 5000);
					break ;
				}
			}
		}
		inputStream_A = serialPort_A.getInputStream();

		// ����COM�ڲ���
		serialPort_A.setSerialPortParams(38400, SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		List list = new ArrayList();
		byte[] readBuffer ;
		int numBytes ;
		while (true) {
			Thread.sleep(500);
			if (inputStream_A.available() > 0) {
				list = null;
				readBuffer = new byte[inputStream_A.available()];
				numBytes = inputStream_A.read(readBuffer);
				String s = new String(readBuffer);
				System.out.println(s);
				for (int i = 0; i < readBuffer.length; i++) {
					if (readBuffer[i] == 2);
						readBuffer[i] = ' ';

				}
				String tmp = " ";
				s = s.replaceAll("[^a-zA-Z0-9]+", " ");
//				System.out.println(new String(readBuffer));
//				Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
//			      java.util.regex.Matcher m = pattern.matcher(s);
//			      char[] array = s.toCharArray();
//			      for (char c : array) {
//			       Matcher mat = pattern.matcher(c+"");
//			       	if(mat.matches()){
//			       		tmp +=c ;
//			       	}
//			      }
				list = StudentSafeUtil.getHexString(s.split(" "));
				
				System.out.println(list);
			}
		}
		}

}
