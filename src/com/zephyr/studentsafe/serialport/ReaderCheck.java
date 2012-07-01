package com.zephyr.studentsafe.serialport;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.Constants;
import com.zephyr.studentsafe.impl.StudentReaderQueue;
import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.impl.StudentReaderQueue;
import com.zephyr.studentsafe.util.StudentSafeUtil;

public class ReaderCheck {
	private static final Logger log = Logger.getLogger(ReaderCheck.class);
	static CommPortIdentifier portId;
	static Enumeration portList;
	static InputStream inputStream_A;
	static InputStream inputStream_B;
	static SerialPort serialPort_A;
	static SerialPort serialPort_B;

	static boolean isReaderAReady;
	static boolean isReaderBReady;
	static String port_A = "";
	static String port_B = "";
	static int btl ;
	// 系统当前COM口列表

	public static String[] getSeralPortListA() {
		String ports = "";
		CommPortIdentifier portId;
		Enumeration serList = CommPortIdentifier.getPortIdentifiers();
		while (serList.hasMoreElements()) {
			portId = (CommPortIdentifier) serList.nextElement();
			ports += portId.getName() + ",";
		}
		return ports.split(",");
	}

	/**
	 * 检测阅读器是否工作正常 。现在的检测方法是能读到内容即可。 以后需要改为在没有卡的情况下能读到阅读器的心跳信息
	 * 
	 * @return
	 * @throws StudentSafeException
	 */
	public static String[] check() throws StudentSafeException {
		String[] s = new String[2] ;
		// 设定的AB阅读器连接端口
		port_A = StudentSafeUtil
				.getStringValue(com.zephyr.studentsafe.bo.Constants.SERIAL_PORT_A);
		port_B = StudentSafeUtil
				.getStringValue(com.zephyr.studentsafe.bo.Constants.SERIAL_PORT_B);
		//设定的波特率。
		btl = StudentSafeUtil.getIntValue(com.zephyr.studentsafe.bo.Constants.BAUDRATE);
		//当前系统接口列表
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			//如果是串口设备
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				//判断是否等于设定的A阅读器串口号
				if (portId.getName().equals(port_A)) {
					try {
						isReaderAReady = true ;
						//试图打开串口，不能打开抛出PortInUseException异常
						serialPort_A = (SerialPort) portId.open("SerialReader",
								5000);
						//端口成功打开，准备读数据
						inputStream_A = serialPort_A.getInputStream();
						serialPort_A.setSerialPortParams(btl,
								SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
								SerialPort.PARITY_NONE);
						for(int i=0; i<6; i++){
						Thread.sleep(400);
						if (inputStream_A.available() > 0) {
							s[0] = "阅读器A 设定连接端口:" + port_A + "设定的波特率:" + btl + ",状态：工作正常。";
							break ;
						} else {
							s[0] = "阅读器A 设定连接端口:" + port_A + "设定的波特率:" + btl + ",状态：端口已打开，无心跳信息。请检查连接线。";
						}
						}
						serialPort_A.close();
					} catch (PortInUseException e) {
						e.printStackTrace();
						// 打开端口失败
						s[0] = "阅读器A 设定连接端口:" + port_A + "设定的波特率:" + btl + ",状态：端口正在使用。";

					} catch (IOException e) {
						log.error(e.getLocalizedMessage());
						throw new StudentSafeException("检测错误："
								+ e.getLocalizedMessage());
					} catch (UnsupportedCommOperationException e) {
						log.error(e.getLocalizedMessage());
						throw new StudentSafeException("检测错误："
								+ e.getLocalizedMessage());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				if (portId.getName().equals(port_B)) {
					try {
						isReaderBReady = true ;
						serialPort_B = (SerialPort) portId.open("SerialReader",
								5000);
						inputStream_B = serialPort_B.getInputStream();

						serialPort_B.setSerialPortParams(btl,
								SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
								SerialPort.PARITY_NONE);
						for(int i = 0 ;i<6;i++){
						Thread.sleep(400);
						if (inputStream_B.available() > 0) {
							s[1] = "阅读器B 设定连接端口:" + port_B + "设定的波特率:" + btl +",状态：工作正常。";
							break ;
						} else {
							s[1] = "阅读器B 设定连接端口:" + port_B 
									+ "设定的波特率:" + btl + ",状态：端口已打开，无心跳信息。请检查连接线。";
						}
						}
						serialPort_B.close();
					} catch (PortInUseException e) {
						s[1] = "阅读器B 设定连接端口:" + port_B + "设定的波特率:" + btl + ",状态：端口正在使用。";
					} catch (IOException e) {
						log.error(e.getLocalizedMessage());
						throw new StudentSafeException("检测错误："
								+ e.getLocalizedMessage());
					} catch (UnsupportedCommOperationException e) {
						log.error(e.getLocalizedMessage());
						throw new StudentSafeException("检测错误："
								+ e.getLocalizedMessage());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}
		
		if ( ! isReaderAReady){
			s[0] = "阅读器A 设定连接端口:" + port_A + "设定的波特率:" + btl + ",状态：端口不存在！";
		}
		if ( ! isReaderBReady){
			s[1] = "阅读器B 设定连接端口:" + port_B + "设定的波特率:" + btl + ",状态：端口不存在！";
		}

		return s;
	}

}
