package com.zephyr.studentsafe.mobilemessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

import org.junit.Test;

import com.zephyr.studentsafe.util.StudentSafeUtil;

public class NewSerialTest {
	static CommPortIdentifier portId;
	static Enumeration portList;
	static InputStream inputStream_A;
	static InputStream inputStream_B;
	static InputStream inputStream_C;
	static SerialPort serialPort_A;
	@Test
	public void testNewSerial() throws PortInUseException, UnsupportedCommOperationException, IOException, InterruptedException {
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals("COM20")) {
					serialPort_A = (SerialPort) portId.open("SerialReader",5000);

				}
			}
		}
		inputStream_A = serialPort_A.getInputStream();
		serialPort_A.setSerialPortParams(38400, SerialPort.DATABITS_8,SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		byte[] buffer = null ;
		while (true){
			Thread.sleep(200);
			int i = inputStream_A.read();
			System.out.println(Integer.toHexString(i));
			//buffer = new byte[inputStream_A.available()];
			//inputStream_A.read(buffer);
			//System.out.println(Integer.parseInt(new String(buffer),16));
		}
	}
	
}