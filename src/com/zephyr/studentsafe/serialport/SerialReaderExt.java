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



import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.Student;
import com.zephyr.studentsafe.impl.ProcessReceiveData;
import com.zephyr.studentsafe.impl.StudentMap;
import com.zephyr.studentsafe.impl.StudentReaderQueue;
import com.zephyr.studentsafe.mobilemessage.SendMessageTask;
import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.studentsafe.util.ThreadPoolManage;
import com.zephyr.sudentsafe.exception.StudentSafeException;

/**
 * ClassName:SerialReader Function: 从串口读取数据。生产环境下，考虑使用多线程 Reason:
 * 
 * @author wanghongliang
 * @version
 * @since Ver 1.1
 * @Date 2010-9-2 上午11:18:57
 * 
 * @see
 * 
 */
public class SerialReaderExt implements Runnable
{
	private static final Logger log = Logger.getLogger(SerialReaderExt.class);
	static CommPortIdentifier portId;
	static Enumeration portList;
	static InputStream inputStream_A;
	static InputStream inputStream_B;
	static InputStream inputStream_C;
	static SerialPort serialPort_A;
	static SerialPort serialPort_B;
	static SerialPort serialPort_C;
	
	static boolean isReaderAReady ;
	static boolean isReaderBReady ;
	static boolean isReaderCReady;
//	static ThreadPoolManage poolManage = new ThreadPoolManage();
//	static ThreadPoolExecutor threadPool;
	// static ThreadPoolManage threadPoolManage = new ThreadPoolManage();
	public static StudentMap s;

	public SerialReaderExt() {

	}

	public static void start() throws PortInUseException, StudentSafeException {
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals(StudentSafeUtil.getStringValue("serialPortId_A"))) {
					serialPort_A = (SerialPort) portId.open("SerialReader", 5000);
					isReaderAReady = true;
					log.info("A阅读器已连接...");
				}
				if (portId.getName().equals(StudentSafeUtil.getStringValue("serialPortId_B"))) {
					serialPort_B = (SerialPort) portId.open("SerialReader", 5000);
					isReaderBReady = true; 
					log.info("B阅读器已连接...");
				}
				if (portId.getName().equals(StudentSafeUtil.getStringValue("serialPortId_C"))) {
					serialPort_C = (SerialPort) portId.open("SerialReader", 5000);
					isReaderCReady = true; 
					log.info("C阅读器已连接...");
				}
				
				

			}
		}
		if (isReaderAReady && isReaderBReady && isReaderCReady){
			log.info("系统初始化完成，开始接收阅读器数据！");
			startReader() ;
			
		}
		else{
			log.info("阅读器连接失败 ");
			throw new StudentSafeException("阅读器连接失败。请检查连接线是否接好");
		}
	}

	private  static void startReader() {
		
	
		byte[] readBuffer = null;
		int numBytes;
		String cardId;
		List<String> list = null;
		try {
			// 打开COM口，5秒超时
			//serialPort = (SerialPort) portId.open("SerialReader", 5000);
			inputStream_A = serialPort_A.getInputStream();
			inputStream_B = serialPort_B.getInputStream();
			inputStream_C = serialPort_C.getInputStream();

			// 设置COM口参数
			serialPort_A.setSerialPortParams(38400, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			serialPort_B.setSerialPortParams(38400, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			serialPort_C.setSerialPortParams(38400, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			while (true) {
				Thread.sleep(200);
				if (inputStream_A.available() > 0) {
					list = null;
					readBuffer = new byte[inputStream_A.available()];
					numBytes = inputStream_A.read(readBuffer);
					//String s = new String(readBuffer);
					//s = s.replaceAll("[^a-zA-Z0-9]+", " ");
					for (int i = 0; i < readBuffer.length; i++) {
						if (readBuffer[i] == 2)
							readBuffer[i] = ' ';
						
					}
					list = StudentSafeUtil.getHexString(new String(readBuffer).split(" "));
					//标记队列属于A阅读器读到的内容
					list.add("A");
				//	System.out.println(list);
					StudentReaderQueue.put(list);
				}
				if (inputStream_B.available() > 0) {
					list = null;
					readBuffer = new byte[inputStream_B.available()];
					//String s = new String(readBuffer);
					numBytes = inputStream_B.read(readBuffer);
					//s = s.replaceAll("[^a-zA-Z0-9]+", " ");
					//log.info("B:=====>" + s);
					for (int i = 0; i < readBuffer.length; i++) {
						if (readBuffer[i] == 2)
							readBuffer[i] = ' ';
						
					}
					list = StudentSafeUtil.getHexString(new String(readBuffer).split(" "));
					System.out.println(list);
					//标记队列属于A阅读器读到的内容
					list.add("B");
					StudentReaderQueue.put(list);
					//System.out.println(StudentReaderQueue.getSize());
				}
				if (inputStream_C.available() > 0) {
					list = null;
					readBuffer = new byte[inputStream_C.available()];
					String s = new String(readBuffer);
					numBytes = inputStream_C.read(readBuffer);
					for (int i = 0; i < readBuffer.length; i++) {
						if (readBuffer[i] == 2)
							readBuffer[i] = ' ';
						
					}
					list = StudentSafeUtil.getHexString(new String(readBuffer).split(" "));
					//标记队列属于A阅读器读到的内容
					list.add("C");
					StudentReaderQueue.put(list);
					//System.out.println(StudentReaderQueue.getSize());
				}
				
				
				//threadPool = poolManage.getThreadPool();
				//threadPool.execute(new ProcessReceiveData(list, "A"));

			}
		
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (UnsupportedCommOperationException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}// catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
 catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public static void main(String[] argvs) throws PortInUseException, StudentSafeException {
		
	}

	@Override
	public void run() {
		try {
			SerialReaderExt.start();
		} catch (PortInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StudentSafeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

}
