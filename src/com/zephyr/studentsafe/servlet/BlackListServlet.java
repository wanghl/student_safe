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


import com.zephyr.studentsafe.bo.Constants;
import com.zephyr.studentsafe.bo.MonitorData;
import com.zephyr.studentsafe.impl.MonitorDataPool;
import com.zephyr.studentsafe.impl.StudentReaderQueue;
import com.zephyr.studentsafe.util.IPManUtil;
import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.studentsafe.util.SystemProperty;

/**
 * @author Administrator
 * 
 * 监控数据输出到页面
 *
 */
public class BlackListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("data", IPManUtil.getBlackList());
		request.getRequestDispatcher("/blacklist.html").forward(request,
				response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ip = request.getParameter("ipaddress");
		IPManUtil.saveStting(Constants.BLACK_LIST , IPManUtil.getStringValue(Constants.BLACK_LIST )+ "," +  ip);
		doGet(request,response);
	}
	
	
	
}
