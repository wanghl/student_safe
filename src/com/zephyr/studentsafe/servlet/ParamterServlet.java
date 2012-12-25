package com.zephyr.studentsafe.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.zephyr.studentsafe.bo.MonitorData;
import com.zephyr.studentsafe.impl.MonitorDataPool;
import com.zephyr.studentsafe.impl.StudentReaderQueue;
import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.studentsafe.util.SystemProperty;

/**
 * @author Administrator
 * 
 * ������������ҳ��
 *
 */
public class ParamterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> map = null;
		List list = new ArrayList();
		
		map = new HashMap<String,String>() ;
		map.put("key", "�ж�ѧ���뿪�ź�����ʱ��");
		map.put("value", StudentSafeUtil.getStringValue("no_scan_times")) ;
		map.put("id", "no_scan_times");
		map.put("desc", "�����һ���յ����ź����𣬾���ʱ��N���ж�ѧ����У���У����λ�룬���鲻����2����");
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "���˵�λʱ�����������ͬ�Ķ���");
		map.put("value", StudentSafeUtil.getStringValue("check_message_send_time")) ;
		map.put("id", "check_message_send_time");
		map.put("desc", "ѧ���ڴ�����������ͣ�������ֻ����У���У��Ϣ��������ͬ���ظ�������Ϣ������");
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "�ظ����Ź��˼��");
		map.put("value", StudentSafeUtil.getStringValue("message_between_time")) ;
		map.put("memo", " ");
		map.put("id", "message_between_time");
		map.put("desc", "�ظ���������ʱ�䡣��������Ϊ10����ʮ�����ڣ�����һ����ͬ�Ľ����������β���");
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "���ն��Ż�ִ");
		map.put("value", StudentSafeUtil.getStringValue("receive_mo")) ;
		map.put("id", "receive_mo");
		map.put("desc", "�Ƿ���ռҳ��ظ��Ķ��š�1����0�����ա�����ֵ��1");
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "���ܶ��Ż�ִ���(����)");
		map.put("value", StudentSafeUtil.getStringValue("receive_mo_time")) ;
		map.put("id", "receive_mo_time");
		map.put("desc", "�ռҳ����Ż�ִ��ʱ��������λ���룬���鲻����5����");
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "���Ż�ִת���ֻ�");
		map.put("value", StudentSafeUtil.getStringValue("receive_mo_mobile")) ;
		map.put("id", "receive_mo_mobile");
		map.put("desc", "�յ��ҳ��Ķ��Ż�ִ��ת�������ֻ���");
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "�ͻ��������쳣���ű���");
		map.put("value", StudentSafeUtil.getStringValue("send_network_error_message")) ;
		map.put("id", "send_network_error_message");
		map.put("desc", "ͨѶ��������������жϺ��Ƿ񷢱������š�1����0����������ֵ��1" ) ;
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "�Ķ����������źŶ��ű���");
		map.put("value", StudentSafeUtil.getStringValue("send_reader_error_message")) ;
		map.put("id", "send_reader_error_message");
		map.put("desc", "�Ķ������趨ʱ������������Ϣ���Ƿ��ͱ������š�1����0����������ֵ��1" ) ;
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "��ͻ��˽������Ӷ���֪ͨ");
		map.put("value", StudentSafeUtil.getStringValue("send_socket_opened_message")) ;
		map.put("id", "send_socket_opened_message");
		map.put("desc", "ͨѶ����������������Ӻ��Ƿ�֪ͨ���š�1����0����������ֵ��1" ) ;
		list.add(map) ;
		
		request.setAttribute("data", list);
		request.getRequestDispatcher("/param.html").forward(request,
				response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String key = request.getParameter("key");
		String value = request.getParameter(key);
		SystemProperty.saveStting(key, value);
		doGet(request,response);
		
		
	}
	
	
}
