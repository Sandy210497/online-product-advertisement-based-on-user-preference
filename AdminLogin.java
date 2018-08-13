package com.logic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.http.servlet.HttpsServlet;

public class AdminLogin extends HttpsServlet {

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String user = request.getParameter("admin");
		String password = request.getParameter("");
		if(user.equalsIgnoreCase("admin")&&password.equalsIgnoreCase("admin"))
		{
			HttpSession hs = request.getSession();
			hs.setAttribute("user", user);
			RequestDispatcher rs = request.getRequestDispatcher("");
			rs.forward(request, response);
		}
		else
		{
			
		}
	}

}
