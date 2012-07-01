package com.zephyr.studentsafe.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test1 {
	
	
	public static void main(String[] argvs) throws IOException{
		
		InputStream in = null ;
		OutputStream out = null ;
		ServerSocket s = new ServerSocket(3030);
		while(true){
			//阻塞 等待接收消息
			Socket server = s.accept();
			
			 in = server.getInputStream() ;
			out = server.getOutputStream() ;
			String send = null;
			String rev = null ;
			byte[] b = null;
			if (in.available() > 0){
				b = new byte[in.available()];
				in.read(b);
				rev = new String(b,"GBK");
				System.out.println("收到客户端数据:"+rev);
			}
			
			send="ccccccc";
			out.write(send.getBytes());
			out.close();
			in.close();
			
		}
		
	}
}
