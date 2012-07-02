package com.zephyr.studentsafe.serialport;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.comm.UnsupportedCommOperationException;

import com.zephyr.studentsafe.impl.StudentReaderQueue;
import com.zephyr.studentsafe.impl.trigger.SerialReaderWithTrigger;
import com.zephyr.studentsafe.util.StudentSafeUtil;

public class ReaderAThread implements Runnable{
	
	private InputStream inputStream_A ;
	public ReaderAThread(InputStream in){
		inputStream_A = in ;
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
			if (inputStream_A.available() > 0) {
				list = null;
				readBuffer = new byte[inputStream_A.available()];
				for (int i = 0; i < readBuffer.length; i++) {
					if (readBuffer[i] == 2)
						readBuffer[i] = ' ';

				}
				list = StudentSafeUtil.getHexString(new String(readBuffer).split(" "));
				//标记队列属于A阅读器读到的内容
				list.add("A");
				StudentReaderQueue.put(list);
			}
			
			
			
			//threadPool = poolManage.getThreadPool();
			//threadPool.execute(new ProcessReceiveData(list, "A"));

		}
	
	} catch (IOException e) {

		// TODO Auto-generated catch block
		e.printStackTrace();

	} 
		
	}
	


	/**
	 *<b>功能:</b><br>
	 *<br>
	 *wanghongliang,2012-6-30
	 *@param aa
	 *@param bb
	 *@return
	 *@throws Exception String
	 */
	public SerialReaderWithTrigger getTest(String aa,int bb ) throws Exception{
		SerialReaderWithTrigger t = new SerialReaderWithTrigger ();
		return t ;
	}
	
	

}
