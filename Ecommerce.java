package com.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.http.servlet.HttpsServlet;

public class Ecommerce extends HttpsServlet {

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
			HttpSession hs = (HttpSession)request.getSession(true);
			HashMap<String,Object> hm = (HashMap<String,Object>)request.getAttribute("session");
			System.out.println("JSP ==="+hm);
			Iterator itr = hm.entrySet().iterator();
			while(itr.hasNext())
			{
				Map.Entry pair = (Map.Entry)itr.next();
				hs.setAttribute((String)pair.getKey(),pair.getValue());  
			}
			System.out.print("------------"+hs.getAttribute("username"));
			RequestDispatcher rd = request.getRequestDispatcher("usermain.jsp");
			rd.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
