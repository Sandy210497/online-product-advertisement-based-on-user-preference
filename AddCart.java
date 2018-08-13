package com.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.http.servlet.HttpsServlet;

public class AddCart extends HttpsServlet {

	Connection con;
	PreparedStatement ps;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Date now = new Date();
		String username = request.getParameter("user");
		String productid = request.getParameter("productid");
		String category =request.getParameter("category");
		if(category ==  null)
		{
			category = "Mobile";
		}
		String model = request.getParameter("model");
		String price = request.getParameter("price");
		String company = request.getParameter("company");
		
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String mysqlDateString = formatter.format(now);
		
		System.out.println("--"+username+"--"+productid+"--"+model+"---"+price+"---"+mysqlDateString);
		try
		{
			con = (Connection)getServletContext().getAttribute("Connection");
			ps = con.prepareStatement("select * from cart where productid=?");
			ps.setString(1, productid);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{	
				RequestDispatcher rd = request.getRequestDispatcher("SingleProductSpecification");
				rd.forward(request, response);
			}
			else
			{
				ps = con.prepareStatement("insert into cart values(?,?,?,?,?,?,?)");
				ps.setString(1, username);
				ps.setString(2, productid);
				ps.setString(3, company);
				ps.setString(4, model);
				ps.setString(5, price);
				ps.setString(6, mysqlDateString);
				ps.setString(7, category);
				int i = ps.executeUpdate();
				if(i == 1)
				{
					System.out.println("Product Added Successfully");
				}
				RequestDispatcher rd = request.getRequestDispatcher("SingleProductSpecification");
				rd.forward(request, response);
				
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
