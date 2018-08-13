package com.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.http.servlet.HttpsServlet;

public class PurchaseHistory extends HttpsServlet {

	
	Connection con;
	PreparedStatement ps;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession hs = request.getSession();
		String user = (String)hs.getAttribute("username");
		try
		{
			con = (Connection)getServletContext().getAttribute("Connection");
			ps = con.prepareStatement("select * from purchase where name = ?");
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				String productid = rs.getString("productid");
				String category = rs.getString("category");
				String company = rs.getString("company");
				String model = rs.getString("model");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
