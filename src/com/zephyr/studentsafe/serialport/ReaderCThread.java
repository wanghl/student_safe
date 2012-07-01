package com.zephyr.studentsafe.serialport;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.comm.UnsupportedCommOperationException;

import com.zephyr.studentsafe.impl.StudentReaderQueue;
import com.zephyr.studentsafe.util.StudentSafeUtil;

public class ReaderCThread implements Runnable{
	
	private InputStream inputStream_C ;
	public ReaderCThread(InputStream in){
		inputStream_C = in ;
	}

	@Override
	public void run() {
		byte[] readBuffer = null;

		List<String> list = null;
		try{
		while (true) {
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (inputStream_C.available() > 0) {
				list = null;
				readBuffer = new byte[inputStream_C.available()];
				for (int i = 0; i < readBuffer.length; i++) {
					if (readBuffer[i] == 2)
						readBuffer[i] = ' ';

				}
				list = StudentSafeUtil.getHexString(new String(readBuffer).split(" "));
				//标记队列属于B阅读器读到的内容
				list.add("C");
				StudentReaderQueue.put(list);
			}
			
			
			
			//threadPool = poolManage.getThreadPool();
			//threadPool.execute(new ProcessReceiveData(list, "B"));

		}
	
	} catch (IOException e) {

		// TODO Buto-generated catch block
		e.printStackTrace();

	} 
		
	}
	
	

}
