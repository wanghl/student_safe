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
 * 监控数据输出到页面
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
		map.put("key", "判定学生离开信号区域时间");
		map.put("value", StudentSafeUtil.getStringValue("no_scan_times")) ;
		map.put("id", "no_scan_times");
		map.put("desc", "从最后一次收到卡信号算起，经过时间N后，判定学生进校或出校。单位秒，建议不大于2分钟");
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "过滤单位时间进出方向相同的短信");
		map.put("value", StudentSafeUtil.getStringValue("check_message_send_time")) ;
		map.put("id", "check_message_send_time");
		map.put("desc", "学生在触发器区域内停留会造成只发进校或出校信息，方向相同的重复进出信息需屏蔽");
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "重复短信过滤间隔");
		map.put("value", StudentSafeUtil.getStringValue("message_between_time")) ;
		map.put("memo", " ");
		map.put("id", "message_between_time");
		map.put("desc", "重复短信屏蔽时间。例如设置为10，则十分钟内，和上一次相同的进出短信屏蔽不发");
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "接收短信回执");
		map.put("value", StudentSafeUtil.getStringValue("receive_mo")) ;
		map.put("id", "receive_mo");
		map.put("desc", "是否接收家长回复的短信。1接收0不接收。建议值：1");
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "接受短信回执间隔(毫秒)");
		map.put("value", StudentSafeUtil.getStringValue("receive_mo_time")) ;
		map.put("id", "receive_mo_time");
		map.put("desc", "收家长短信回执的时间间隔。单位毫秒，建议不大于5分钟");
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "短信回执转发手机");
		map.put("value", StudentSafeUtil.getStringValue("receive_mo_mobile")) ;
		map.put("id", "receive_mo_mobile");
		map.put("desc", "收到家长的短信回执后，转发至该手机：");
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "客户端连接异常短信报警");
		map.put("value", StudentSafeUtil.getStringValue("send_network_error_message")) ;
		map.put("id", "send_network_error_message");
		map.put("desc", "通讯箱与服务器连接中断后，是否发报警短信。1发送0不发，建议值：1" ) ;
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "阅读器无心跳信号短信报警");
		map.put("value", StudentSafeUtil.getStringValue("send_reader_error_message")) ;
		map.put("id", "send_reader_error_message");
		map.put("desc", "阅读器在设定时间内无心跳信息，是否发送报警短信。1发送0不发，建议值：1" ) ;
		list.add(map) ;
		
		map = new HashMap<String,String>() ;
		map.put("key", "与客户端建立连接短信通知");
		map.put("value", StudentSafeUtil.getStringValue("send_socket_opened_message")) ;
		map.put("id", "send_socket_opened_message");
		map.put("desc", "通讯箱与服务器建立连接后，是否发通知短信。1发送0不发，建议值：1" ) ;
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
