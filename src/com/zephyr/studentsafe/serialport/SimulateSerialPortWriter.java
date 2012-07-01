/**
 * SimulateSerialPortWriter.java
 * com.zephyr.studentsafe.serialport
 *
 * Function£º TODO 
 *
 *   ver     date      		author
 * ©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤
 *   		 2010-9-8 		lenovo
 *
 * Copyright (c) 2010, TNT All Rights Reserved.
*/

package com.zephyr.studentsafe.serialport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Random;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

/**
 * ClassName:SimulateSerialPortWriter
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lenovo
 * @version  
 * @since    Ver 1.1
 * @Date	 2010-9-8		ÏÂÎç12:06:18
 *
 * @see 	 
 */
public class SimulateSerialPortWriter {
	
	CommPortIdentifier portId;
	Enumeration portList;
	SerialPort serialPorts;
	OutputStream outputStream;
	InputStream in ;
	
	public SimulateSerialPortWriter() throws InterruptedException{
		portList = CommPortIdentifier.getPortIdentifiers();
		while ( portList.hasMoreElements() ){
			portId = (CommPortIdentifier)portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL){
				if (portId.getName().equals("COM14")){
					writerToSerialPort();
				}
			}
		}
	}
	
	public void writerToSerialPort() throws InterruptedException{
		try {
			serialPorts = (SerialPort) portId.open("APP", 5000);
			outputStream = serialPorts.getOutputStream();
			in = serialPorts.getInputStream();
			serialPorts.setSerialPortParams(38400,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
			while (true){
				if ( in.available() > 0 ){
					byte[] b  = new byte[200];
					in.read(b);
					System.out.println(new String(b));
				}
			}
		} catch (PortInUseException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (UnsupportedCommOperationException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
	}
	
	private int makeRandom(){
		Random random = new Random();
		return random.nextInt(999999);
	}
	
	public static void main(String[] argvs) throws InterruptedException{
		SimulateSerialPortWriter w = new SimulateSerialPortWriter();
	}
}

