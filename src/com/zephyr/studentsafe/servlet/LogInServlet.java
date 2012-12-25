package com.zephyr.studentsafe.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zephyr.studentsafe.dao.BaseDAO;
import com.zephyr.studentsafe.util.MD5Util;
import com.zephyr.studentsafe.util.StudentSafeUtil;


public class LogInServlet extends HttpServlet{

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
		BaseDAO dao  = new BaseDAO();
		String userName = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		
		userName = new String(userName.getBytes("iso8859-1"),"utf-8");
		password = new String(password.getBytes("iso8859-1"),"utf-8");
		boolean result = dao.verificationUser(userName, MD5Util.getMD5String(password.getBytes()));
		
		if(result)
		{
			request.getSession().setAttribute("user", userName);
			response.sendRedirect(request.getContextPath() + "/job");
		}else
		{
			response.sendRedirect(request.getContextPath() + "/job");
		}
		
	}
	

}
