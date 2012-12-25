package com.zephyr.studentsafe.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zephyr.studentsafe.dao.BaseDAO;
import com.zephyr.studentsafe.util.MD5Util;
import com.zephyr.studentsafe.util.StudentSafeUtil;


public class LogOffServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response) ;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("user", null)	;
		
			response.sendRedirect(request.getContextPath() + "/job");
		
		
	}
	

}
