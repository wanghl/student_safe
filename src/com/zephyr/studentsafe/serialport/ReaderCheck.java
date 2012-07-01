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
	// ϵͳ��ǰCOM���б�

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
	 * ����Ķ����Ƿ������� �����ڵļ�ⷽ�����ܶ������ݼ��ɡ� �Ժ���Ҫ��Ϊ��û�п���������ܶ����Ķ�����������Ϣ
	 * 
	 * @return
	 * @throws StudentSafeException
	 */
	public static String[] check() throws StudentSafeException {
		String[] s = new String[2] ;
		// �趨��AB�Ķ������Ӷ˿�
		port_A = StudentSafeUtil
				.getStringValue(com.zephyr.studentsafe.bo.Constants.SERIAL_PORT_A);
		port_B = StudentSafeUtil
				.getStringValue(com.zephyr.studentsafe.bo.Constants.SERIAL_PORT_B);
		//�趨�Ĳ����ʡ�
		btl = StudentSafeUtil.getIntValue(com.zephyr.studentsafe.bo.Constants.BAUDRATE);
		//��ǰϵͳ�ӿ��б�
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			//����Ǵ����豸
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				//�ж��Ƿ�����趨��A�Ķ������ں�
				if (portId.getName().equals(port_A)) {
					try {
						isReaderAReady = true ;
						//��ͼ�򿪴��ڣ����ܴ��׳�PortInUseException�쳣
						serialPort_A = (SerialPort) portId.open("SerialReader",
								5000);
						//�˿ڳɹ��򿪣�׼��������
						inputStream_A = serialPort_A.getInputStream();
						serialPort_A.setSerialPortParams(btl,
								SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
								SerialPort.PARITY_NONE);
						for(int i=0; i<6; i++){
						Thread.sleep(400);
						if (inputStream_A.available() > 0) {
							s[0] = "�Ķ���A �趨���Ӷ˿�:" + port_A + "�趨�Ĳ�����:" + btl + ",״̬������������";
							break ;
						} else {
							s[0] = "�Ķ���A �趨���Ӷ˿�:" + port_A + "�趨�Ĳ�����:" + btl + ",״̬���˿��Ѵ򿪣���������Ϣ�����������ߡ�";
						}
						}
						serialPort_A.close();
					} catch (PortInUseException e) {
						e.printStackTrace();
						// �򿪶˿�ʧ��
						s[0] = "�Ķ���A �趨���Ӷ˿�:" + port_A + "�趨�Ĳ�����:" + btl + ",״̬���˿�����ʹ�á�";

					} catch (IOException e) {
						log.error(e.getLocalizedMessage());
						throw new StudentSafeException("������"
								+ e.getLocalizedMessage());
					} catch (UnsupportedCommOperationException e) {
						log.error(e.getLocalizedMessage());
						throw new StudentSafeException("������"
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
							s[1] = "�Ķ���B �趨���Ӷ˿�:" + port_B + "�趨�Ĳ�����:" + btl +",״̬������������";
							break ;
						} else {
							s[1] = "�Ķ���B �趨���Ӷ˿�:" + port_B 
									+ "�趨�Ĳ�����:" + btl + ",״̬���˿��Ѵ򿪣���������Ϣ�����������ߡ�";
						}
						}
						serialPort_B.close();
					} catch (PortInUseException e) {
						s[1] = "�Ķ���B �趨���Ӷ˿�:" + port_B + "�趨�Ĳ�����:" + btl + ",״̬���˿�����ʹ�á�";
					} catch (IOException e) {
						log.error(e.getLocalizedMessage());
						throw new StudentSafeException("������"
								+ e.getLocalizedMessage());
					} catch (UnsupportedCommOperationException e) {
						log.error(e.getLocalizedMessage());
						throw new StudentSafeException("������"
								+ e.getLocalizedMessage());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}
		
		if ( ! isReaderAReady){
			s[0] = "�Ķ���A �趨���Ӷ˿�:" + port_A + "�趨�Ĳ�����:" + btl + ",״̬���˿ڲ����ڣ�";
		}
		if ( ! isReaderBReady){
			s[1] = "�Ķ���B �趨���Ӷ˿�:" + port_B + "�趨�Ĳ�����:" + btl + ",״̬���˿ڲ����ڣ�";
		}

		return s;
	}

}
