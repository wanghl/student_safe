package com.zephyr.studentsafe.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test {
	
	public static void main(String[] argvs) throws UnknownHostException, IOException{
		
		Socket socket = new Socket("221.194.156.93",8086);
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
		socket.setKeepAlive(true);
		
			
			socket.getOutputStream().write(b);
			
		
	}

}
