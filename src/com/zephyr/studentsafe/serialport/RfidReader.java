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

import com.zephyr.studentsafe.ui.MessageWindow;
import com.zephyr.studentsafe.ui.action.button.ReadeCardNumberAction;
import com.zephyr.studentsafe.util.StudentSafeUtil;

public class RfidReader implements Runnable{
	static CommPortIdentifier portId;
	static Enumeration portList;
	static InputStream inputStream_C;
	static SerialPort serialPort_C = null;
	private static final Logger log = Logger.getLogger(RfidReader.class);

	@Override
	public void run() {
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements())
		{
			portId = (CommPortIdentifier) portList.nextElement();
			// is serial port ?
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL)
			{

				// readerA selected port
				if (portId.getName().equals(
						StudentSafeUtil.getStringValue("serialPortId_C")))
				{
					try
					{
						serialPort_C = (SerialPort) portId.open("StartButtonAction", 5000);
						log.info("��" + portId.getName() + "�ɹ�");
					} catch (PortInUseException e1)
					{
						// TODO Auto-generated catch block
						log.error("��"
								+ StudentSafeUtil.getStringValue("serialPortId_C") + "ʧ��:"
								+ e1.getLocalizedMessage());
						MessageWindow.show("��"
								+ StudentSafeUtil.getStringValue("serialPortId_C") + "ʧ��:"
								+ e1.getLocalizedMessage());
					}
				}
			}

		}

		if (serialPort_C == null)
		{
			log.error("�򿪴���ʧ�ܣ�");
			return;
		}

		try
		{
			byte[] readBuffer = null;
			int numBytes;
			String cardId;
			List<String> list = null;
			StringBuffer stringBuilder = null;

			if (serialPort_C != null)
			{
				try
				{
					serialPort_C.setSerialPortParams(38400,
							SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
							SerialPort.PARITY_NONE);
					inputStream_C = serialPort_C.getInputStream();
				} catch (UnsupportedCommOperationException e3)
				{
					// TODO Auto-generated catch block
					log.error("���ö˿�ʧ�ܣ�" + e3.getLocalizedMessage());
				} catch (IOException e2)
				{
					// TODO Auto-generated catch block
					log.error("I/O����" + e2.getLocalizedMessage());
				}
			}

			while (true)
			{
				
				try
				{
					Thread.sleep(400);
				} catch (InterruptedException el)
				{
					log.error(el);
				}
				if (serialPort_C != null)
				{
					if (inputStream_C.available() > 0)
					{
						list = null;
						stringBuilder = new StringBuffer();
						readBuffer = new byte[inputStream_C.available()];
						numBytes = inputStream_C.read(readBuffer);
						for (int i = 0; i < readBuffer.length; i++)
						{
							int v = readBuffer[i] & 0xff;
							String hv = Integer.toHexString(v);
							if (hv.length() < 2)
							{
								stringBuilder.append(0);
							}
							stringBuilder.append(hv);
						}
						// parse for rfid card id .
						list = StudentSafeUtil.getHexString(stringBuilder.toString().split("ffff"));
						// �õ��ĸ�ʽ�Ͷ�ͷ�յ���һ��������&������ Ҫ����һ��
						log.info(list);
						if (!list.isEmpty())
						{
							ReadeCardNumberAction.updateStudentCardNumber(list.get(0).split("&")[0]);
						}

					}
				}
			}
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			log.error(e1);
		}

	}
	
	public static void close(){
		
		if (serialPort_C != null ){
			serialPort_C.close();
		}
		
	}
	
	public static boolean isClosed(){
		return serialPort_C == null ;
	}

}
