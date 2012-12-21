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

/**
 * @author Administrator
 * 
 * 监控数据输出到页面
 *
 */
public class GetMonitorDataServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>() ;
		String key = null ;
		MonitorData data = null ;
		for(Iterator<String> it = MonitorDataPool.getKeySet().iterator(); it.hasNext();)
		{
			key = it.next();
			data =  MonitorDataPool.getMonitorData(key); 
			
			map.put("ip", key);
			map.put("inschool", Integer.toString(data.getInSchool())) ;
			map.put("outschool", Integer.toString(data.getOutSchool()));
			map.put("notleave", Integer.toString(data.getNotLeave()));
			map.put("waitprocess", Integer.toString(StudentReaderQueue.getSize()));
			list.add(map);
		}
		request.setAttribute("data", list);
		request.getRequestDispatcher("/index.html").forward(request,
				response);
	}
	
	
}
