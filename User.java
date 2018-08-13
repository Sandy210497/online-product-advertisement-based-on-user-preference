package com.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.http.servlet.HttpsServlet;

public class User extends HttpsServlet 
{
	Connection con;
	PreparedStatement ps;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
	
		String email = request.getParameter("email");
		String mobileno = request.getParameter("mobileno");
		String city  = request.getParameter("city");
		System.out.println(username+"--"+password+"--"+email+"--"+mobileno+"--"+city);
		con = (Connection) getServletContext().getAttribute("Connection");
		try
		{
			con = (Connection) getServletContext().getAttribute("Connection");
			ps = con.prepareStatement("select * from registration where email=?");
			ps.setString(1, email);
			ResultSet rs =  ps.executeQuery();
			if(rs.next())
			{
				request.setAttribute("error", "User Already Exists");
				RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
				rd.forward(request, response);
			}
			else
			{
				ps = con.prepareStatement("insert into registration(name,password,email,mobileno,city) values(?,?,?,?,?)");
				ps.setString(1,username);
				ps.setString(2,password);
				ps.setString(3,email);
				ps.setString(4,mobileno);
				ps.setString(5,city);
				int i = ps.executeUpdate();
				if(i==1)
				{
					request.setAttribute("result", "Registered Successfully");
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}	
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

}
