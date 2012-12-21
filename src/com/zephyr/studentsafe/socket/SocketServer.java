package com.zephyr.studentsafe.socket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.dao.HibernateUtil;
import com.zephyr.studentsafe.impl.ProcessStudentData;
import com.zephyr.studentsafe.util.ThreadPoolManage;

public class SocketServer extends HttpServlet {

	private final static Logger log = Logger.getLogger(SocketServer.class) ;
	public void init() throws ServletException { 
		log.info("�������ݿ�");
		// TODO start db
		HibernateUtil.getSession();
		ProcessStudentData.alive();
		ThreadPoolManage.getThreadPool().execute(new ProcessStudentData());
		
		SocketListener s  = new SocketListener();
		s.start();
		
	}

}

class SocketListener extends Thread {
	private final static Logger log = Logger.getLogger(SocketServer.class);
	ServerSocket socket = null;
	Socket client = null;

	public void run() {
		try {
			socket = new ServerSocket(8086);
			log.info("������������׼����������");
			while (true) {
				client = socket.accept();
				log.info("�յ�ͨѶ����������IP��ַ��" + client.getRemoteSocketAddress());
				Thread runThread = new Thread(new ProcessThread(client));
				runThread.start();
			}
		} catch (Exception e) {
			log.error(e);
		}
	}
}