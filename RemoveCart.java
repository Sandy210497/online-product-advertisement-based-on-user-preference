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

public class RemoveCart extends HttpsServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try
		{
			String companyname = request.getParameter("company");
			String model = request.getParameter("model");
			System.out.println("--"+companyname+"=="+model);
			Connection con = (Connection)getServletContext().getAttribute("Connection");
			PreparedStatement ps = con.prepareStatement("delete from cart where company=? and model=?");
			ps.setString(1, companyname);
			ps.setString(2, model);
			int i = ps.executeUpdate();
			if(i == 1)
			{
				System.out.println("Product Remove Successfully");
			}
			RequestDispatcher rd = request.getRequestDispatcher("checkout.jsp");
			rd.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
