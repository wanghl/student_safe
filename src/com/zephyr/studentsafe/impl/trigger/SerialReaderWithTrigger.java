package com.zephyr.studentsafe.impl.trigger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.util.StudentSafeUtil;

/**
 *<p>
 * ClassName:SerialReaderWithTrigger
 * </p>
 *<p>
 * Description: �Ӷ�ͷ��ȡ��Ϣ�������ڴ��������Ľ������
 * </p>
 * 
 * @author wanghongliang xbwolf@sina.cn
 *@date 2012-7-2 ����06:26:12
 * 
 */
public class SerialReaderWithTrigger {
	private static final Logger log = Logger
			.getLogger(SerialReaderWithTrigger.class);
	static CommPortIdentifier portId;
	static Enumeration portList;
	static InputStream inputStream_A;
	static InputStream inputStream_B;
	static InputStream inputStream_C;
	static SerialPort serialPort_A;
	static SerialPort serialPort_B;
	static SerialPort serialPort_C;

	static boolean isReaderAReady;
	static boolean isReaderBReady;
	static boolean isReaderCReady;
	// ͳ�ƶ�ͷ����
	static int _readercount = 0;

	/**
	 *<b>����: ����ͨѶ�����Ӷ�ͷ��һ����ԣ����3����ͷ��</b><br>
	 *<br>
	 * 
	 * @author wanghongliang,2012-7-2
	 *@throws PortInUseException
	 *@throws StudentSafeException
	 */
	public static void start() throws PortInUseException, StudentSafeException {
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals(
						StudentSafeUtil.getStringValue("serialPortId_A"))) {
					serialPort_A = (SerialPort) portId.open("SerialReader",
							5000);
					isReaderAReady = true;
					_readercount++;
					log.info("A�Ķ���������...");
				}
				if (portId.getName().equals(
						StudentSafeUtil.getStringValue("serialPortId_B"))) {
					serialPort_B = (SerialPort) portId.open("SerialReader",
							5000);
					isReaderBReady = true;
					_readercount++;
					log.info("B�Ķ���������...");
				}

			}
		}
		if (isReaderAReady || isReaderBReady) {
			log.info("���ڳ�ʼ����ɣ�����⵽ " + _readercount + " ̨�Ķ�����");
			// ��ʼ��������
			startReader();

		} else {
			log.info("�Ķ�������ʧ�� ");
			throw new StudentSafeException("�Ķ�������ʧ�ܡ������������Ƿ�Ӻ�");
		}
	}

	private static void startReader() {
		byte[] readBuffer = null;
		List<String> list = null;
		try {
			inputStream_A = serialPort_A.getInputStream();
			inputStream_B = serialPort_B.getInputStream();
			// inputStream_C = serialPort_C.getInputStream();

			// ����COM�ڲ���
			serialPort_A.setSerialPortParams(38400, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			serialPort_B.setSerialPortParams(38400, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			while (true) {
				Thread.sleep(200);
				if (inputStream_A.available() > 0) {
					list = null;
					readBuffer = new byte[inputStream_A.available()];
					inputStream_A.read(readBuffer);
					// �������ݣ����ŵ�StudentReaderQueueWithTrigger������
					paseHexString(readBuffer);
				}
				if (inputStream_B.available() > 0) {
					list = null;
					readBuffer = new byte[inputStream_B.available()];
					inputStream_B.read(readBuffer);
					
					paseHexString(readBuffer);
				}
			}

		} catch (IOException e) {
			log.error(e);
		} catch (UnsupportedCommOperationException e) {
			log.error(e);
		}
		catch (InterruptedException e) {
			log.error(e);
		}

	}

	/**
	 *<b>����: ����ͨ��Э�������ͷ�ϴ��ı��ġ���ʽ�μ�����Ƶ�����͵��ӱ�ǩ-�Ķ���Э��V1.4.doc��</b><br>
	 *<br>
	 * 
	 * @author wanghongliang,2012-7-2
	 *@param bytes
	 *@return
	 */
	private static void paseHexString(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder();
		// �Ȱ�byte�����е�ʮ������ת��Ϊ16������
		for (int i = 0; i < bytes.length; i++) {
			int v = bytes[i] & 0xff;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		String[] s = stringBuilder.toString().split("ffff0");
		List<String> list = StudentSafeUtil.paseStringWidthTriggerinfo(s);
		StudentReaderQueueWithTrigger.put(list);
	}

}
