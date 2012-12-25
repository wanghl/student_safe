package com.zephyr.studentsafe.mina;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.MonitorData;
import com.zephyr.studentsafe.impl.MonitorDataPool;
import com.zephyr.studentsafe.impl.StudentReaderQueue;
import com.zephyr.studentsafe.util.StudentSafeUtil;

public class ProcessThread implements Runnable{
	private final Logger log = Logger.getLogger(ProcessThread.class);
	public Socket client ;
	private ProcessThread(){}
	
	public ProcessThread(Socket client){
		this.client = client ;
		MonitorData data = new MonitorData();
		MonitorDataPool.put(client.getInetAddress().getHostAddress(), data);
	}

	@Override
	public void run() {
		try {
			client.setKeepAlive(true);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			log.error(e1);
		}
		InputStream reader = null ;
		byte[] readBuffer = new byte[1024];
		StringBuffer stringBuffer = null;
		List list = new ArrayList();
		try {
			reader = client.getInputStream() ;
			while(reader.read(readBuffer) > 0)
			{
				stringBuffer = new StringBuffer();
				list = null;
				for (int i = 0 ; i < readBuffer.length; i++){
					int v = readBuffer[i] & 0xff ;
					String hv = Integer.toHexString(v);
					if (hv.length() <2 ){
						stringBuffer.append(0);
					}
					//System.out.print(hv + " ");
					stringBuffer.append(hv);
				}
				if(log.isDebugEnabled())
				{
					log.debug(stringBuffer);
				}
				list = StudentSafeUtil.getHexString(stringBuffer.toString().split("ffff"),client.getInetAddress().getHostAddress()) ;
				if(log.isDebugEnabled())
				{
					log.debug(list);
				}
				//put to reader queue
				StudentReaderQueue.put(list);
				//clear StringBuffer 
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e) ;
		}
	}
	
	

}
