package com.zephyr.studentsafe.mina;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Test {
	
	public static void main(String[] argvs) throws UnknownHostException, IOException{
		
		
			RunThread r1  = new RunThread();
			RunThread r2  = new RunThread();
			RunThread r3  = new RunThread();
			RunThread r4  = new RunThread();
			RunThread r5  = new RunThread();
			
			r1.start();
//			r2.start();
//			r3.start();
//			r4.start();
//			r5.start();
			
	}

}

class RunThread extends Thread{
	
	public void run()
	{
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1",8086);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		byte[] b = new byte[14];
		b[0] = (byte)0xff;
		b[1] = (byte)0xff;
		b[2] = (byte)0x01;
		b[3] = (byte)0x02;
		b[4] = (byte)0x02;
		b[5] = (byte)0x00;
		b[6] = (byte)0x02;
		b[7] = (byte)0x07;
		b[8] = (byte)0x04;
		b[9] = (byte)0x01;
		b[10] = (byte)0x12;
		b[11] = (byte)0x1;
		b[12] = (byte)0x1;
		b[13] = (byte)0x1;
		try {
			socket.setKeepAlive(true);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 int i = 2 ;
			while( --i > 0 ){
				try {
					socket.getOutputStream().write(b);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
	}
	
	
	
}
