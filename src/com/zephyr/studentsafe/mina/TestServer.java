package com.zephyr.studentsafe.mina;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.zephyr.studentsafe.impl.StudentReaderQueue;
import com.zephyr.studentsafe.util.StudentSafeUtil;

public class TestServer {

	public static void main(String[] argvs) {

		ServerSocket socket = null;
		Socket client = null;

		try {
			socket = new ServerSocket(8086);
			System.out.println("服务已启动");
			while (true) {
				client = socket.accept();
				InputStream reader = null;
				byte[] readBuffer = new byte[50];
				StringBuffer stringBuffer = null;
				List list = new ArrayList();
				try {
					reader = client.getInputStream();
					while (reader.read(readBuffer) > 0) {
						stringBuffer = new StringBuffer();
						list = null;
						for (int i = 0; i < readBuffer.length; i++) {
							int v = readBuffer[i] & 0xff;
							String hv = Integer.toHexString(v);
							if (hv.length() < 2) {
								stringBuffer.append(0);
							}
							stringBuffer.append(hv);
						}

						System.out.print(stringBuffer.toString());
						list = getHexString(stringBuffer.toString().split("ffff"), client.getInetAddress().getHostAddress());

						System.out.println(list);

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> getHexString(String[] str,String ip) {
		// TODO: 阅读器传来的卡号第一位表示卡片的电池电量 。0为正常1为电池电量低
		// 需实现监控卡片电量的功能
		List<String> list = new ArrayList<String>();
		String s = "";
		String tigger = "";
		String rfid = "";
		try {
			for (int i = 1; i < str.length; i++) {
				s = str[i];
				// 第一位不为01的 去掉。标签信号01开头，读头心跳包02开头，还有些有乱码的 长度不够的 全部屏蔽
				if (!s.substring(0, 2).equals("01")) {
					continue;
				}
				rfid = s.substring(6, 14);
				System.out.println("rfid:" + rfid);
				tigger = s.substring(16, 18);
				System.out.println("tigger:" + tigger);
				list.add(Integer.valueOf(rfid, 16).toString() + "&" + tigger + "&" + ip);
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(s);
		}
		return list;
	}

}
