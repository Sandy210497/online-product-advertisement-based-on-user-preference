package com.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.http.servlet.HttpsServlet;

public class UserRegister extends HttpsServlet
{

	Connection con;
	PreparedStatement ps;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try
		{
			con = (Connection)getServletContext().getAttribute("DBConnection");
			String username = request.getParameter("user");
			String usermail = request.getParameter("usermail");
			String pass = request.getParameter("password");
			
		
				ps = con.prepareStatement("insert into videoregister values(?,?,?)");
				ps.setString(1, username);
				ps.setString(2, usermail);
				ps.setString(3, pass);
//				int i = ps.executeUpdate();
//				if(i == 1)
//				{
//					request.setAttribute("result", "Register Successfully");
//					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
//					rd.forward(request, response);
//				}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

}
