package com.zephyr.studentsafe.serialport;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TooManyListenersException;
import java.util.concurrent.ThreadPoolExecutor;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;
import javax.swing.table.DefaultTableModel;



import org.apache.log4j.Logger;

import com.zephyr.studentsafe.impl.StudentReaderQueue;
import com.zephyr.studentsafe.ui.MessageWindow;
import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.studentsafe.util.ThreadPoolManage;

/**
 * 
 * @author wanghongliang
 * @version
 * @since Ver 1.1
 * @Date 2010-9-2 上午11:18:57
 * 
 * @see
 * 
 */
public class SerialReaderManage implements Runnable
{
	static CommPortIdentifier portId;
	static Enumeration portList;
	static InputStream inputStream_A;
	static InputStream inputStream_B;
	static InputStream inputStream_C;
	static SerialPort serialPort_A = null;
	static SerialPort serialPort_B = null;
	static SerialPort serialPort_C = null;
	static boolean alive = true;
	private static SerialReaderManage serailReaderManager;
	private final static Logger log = Logger.getLogger(SerialReaderManage.class);
	public SerialReaderManage() {

	}

	public static SerialReaderManage getReader() {
		if (serailReaderManager == null) {
			serailReaderManager = new SerialReaderManage();
			return serailReaderManager;
		} else {
			return serailReaderManager;
		}
	}

	@Override
	public void run() {
		log.info("连接阅读器....");
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			// is serial port ?
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				// is selected port ?
				System.out.println(portId.getName()) ;
				if (portId.getName().equals(
						StudentSafeUtil.getStringValue("serialPortId_A"))) {
		
					try {
						serialPort_A = (SerialPort) portId.open("StartButtonAction", 5000);
						log.info("打开" + portId.getName() + "成功");
						alive = true ;
					} catch (PortInUseException e1) {
						// TODO Auto-generated catch block
						alive = false ;
						log.error("打开"
								+ StudentSafeUtil.getStringValue("serialPortId_A") + "失败:"
								+ e1.getLocalizedMessage() );
					}
				}
				// readerB selected port
				if (portId.getName().equals(
						StudentSafeUtil.getStringValue("serialPortId_B"))) {
					try {
						serialPort_B = (SerialPort) portId.open("StartButtonAction", 5000);
						log.info("打开" + portId.getName() + "成功");
						alive = true ;
					} catch (PortInUseException e1) {
						alive = false ;
						// TODO Auto-generated catch block
						log.error("打开"
								+ StudentSafeUtil.getStringValue("serialPortId_B") + "失败:"
								+ e1.getLocalizedMessage() );
					}
				}
				// readerA selected port
				if (portId.getName().equals(
						StudentSafeUtil.getStringValue("serialPortId_C"))) {
					try {
						serialPort_C = (SerialPort) portId.open("StartButtonAction", 5000);
						log.info("打开" + portId.getName() + "成功");
						alive = true ;
					} catch (PortInUseException e1) {
						alive = false ;
						// TODO Auto-generated catch block
						log.error("打开"
								+ StudentSafeUtil.getStringValue("serialPortId_C") + "失败:"
								+ e1.getLocalizedMessage() );
					}
				}
			}
		}
		
		//如果一个都没打开
		
		if (serialPort_A == null && serialPort_B == null && serialPort_C == null ){
			log.error("打开串口失败！");
			alive = false ;
			return  ;
		}
			

		try {
			byte[] readBuffer = null;
			int numBytes;
			String cardId;
			List<String> list = null;
			StringBuffer stringBuilder = null ;
			
			// set serial port parameter
			if (serialPort_A != null) {
				try {
					serialPort_A.setSerialPortParams(38400,
							SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
							SerialPort.PARITY_NONE);
					inputStream_A = serialPort_A.getInputStream();
				} catch (UnsupportedCommOperationException e5) {
					// TODO Auto-generated catch block
					log.error("设置端口失败：" + e5.getLocalizedMessage());
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					log.error("I/O错误：" + e6.getLocalizedMessage());
				}
			}
			if (serialPort_B != null) {
				try {
					serialPort_B.setSerialPortParams(38400,
							SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
							SerialPort.PARITY_NONE);
					inputStream_B = serialPort_B.getInputStream();
				} catch (UnsupportedCommOperationException e4) {
					// TODO Auto-generated catch block
					log.error("设置端口失败：" + e4.getLocalizedMessage());
				} catch (IOException e7) {
					// TODO Auto-generated catch block
					log.error("I/O错误：" + e7.getLocalizedMessage());
				}
			}
			if (serialPort_C != null) {
				try {
					serialPort_C.setSerialPortParams(38400,
							SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
							SerialPort.PARITY_NONE);
					inputStream_C = serialPort_C.getInputStream();
				} catch (UnsupportedCommOperationException e3) {
					// TODO Auto-generated catch block
					log.error("设置端口失败：" + e3.getLocalizedMessage());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					log.error("I/O错误：" + e2.getLocalizedMessage());
				}
			}

			while (alive) {
				try {
					Thread.sleep(400);
				} catch (InterruptedException el) {
					//log.error(el);
				}
				if (serialPort_A != null) {
					
					if (inputStream_A.available() > 0) {
						list = null;
						stringBuilder = new StringBuffer();
						readBuffer = new byte[inputStream_A.available()];
						numBytes = inputStream_A.read(readBuffer);
						for (int i = 0 ; i < readBuffer.length; i++){
							int v = readBuffer[i] & 0xff ;
							String hv = Integer.toHexString(v);
							if (hv.length() <2 ){
								stringBuilder.append(0);
							}
							stringBuilder.append(hv);
						}

						// parse for rfid card id .
						list = StudentSafeUtil.getHexString(stringBuilder.toString().split("ffff")) ;
						//put to reader queue
						StudentReaderQueue.put(list);
						//clear StringBuffer 
						stringBuilder = null ;
					}
				}
				if (serialPort_B != null) {
					if (inputStream_B.available() > 0) {
						list = null;
						stringBuilder = new StringBuffer();
						readBuffer = new byte[inputStream_B.available()];
						numBytes = inputStream_B.read(readBuffer);
						for (int i = 0 ; i < readBuffer.length; i++){
							int v = readBuffer[i] & 0xff ;
							String hv = Integer.toHexString(v);
							if (hv.length() <2 ){
								stringBuilder.append(0);
							}
							stringBuilder.append(hv);
						}

						// parse for rfid card id .
						list = StudentSafeUtil.getHexString(stringBuilder.toString().split("ffff")) ;
						//put to reader queue
						StudentReaderQueue.put(list);
						//clear StringBuffer 
						stringBuilder = null ;
					}
				}
				if (serialPort_C != null) {
					if (inputStream_C.available() > 0) {
						list = null;
						stringBuilder = new StringBuffer();
						readBuffer = new byte[inputStream_C.available()];
						numBytes = inputStream_C.read(readBuffer);
						for (int i = 0 ; i < readBuffer.length; i++){
							int v = readBuffer[i] & 0xff ;
							String hv = Integer.toHexString(v);
							if (hv.length() <2 ){
								stringBuilder.append(0);
							}
							stringBuilder.append(hv);
						}
						// parse for rfid card id .
						list = StudentSafeUtil.getHexString(stringBuilder.toString().split("ffff")) ;
						//put to reader queue
						StudentReaderQueue.put(list);
						//clear StringBuffer 
						stringBuilder = null ;
					}
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			log.error(e1);
		}
	}

	// 线程无法正真停止。先使用一个标示停止从阅读器读取数据，然后关闭
	public static  void shutDownThread() {
		log.info("关闭阅读器");
		alive = false;
		if (serialPort_A != null) {

			serialPort_A.close();
			serialPort_A = null;
		}
		if (serialPort_B != null) {

			serialPort_B.close();
			serialPort_B = null;
		}
		if (serialPort_C != null) {

			serialPort_C.close();
			serialPort_C = null;
		}

	}

	public static  boolean isThreadAlive() {
		return alive;
	}

}
