package com.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.http.servlet.HttpsServlet;

public class PaymentSuccess extends HttpsServlet {

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
		String sessionid = request.getParameter("sessionId");
		System.out.println("------------------Session ID-----------------"+sessionid);
		HttpSession hs = (HttpSession)getServletContext().getAttribute(sessionid);
		
		System.out.println("------------------Session Object-----------------"+hs);
		
		Vector usertimepref = (Vector) hs.getAttribute("usertimepref");
		System.out.println("-----user time pref-----"+usertimepref);
		System.out.println("-----user Name-----"+(String)hs.getAttribute("username"));
	
		
		
	    Enumeration keys = hs.getAttributeNames();     
	    HashMap<String,Object> hm=new HashMap<String,Object>();  
	    while (keys.hasMoreElements())
	    {
	      String key = (String)keys.nextElement();
	      hm.put(key,hs.getValue(key));
	      hs.removeAttribute(key);      
	    }
	    hs.invalidate();
	    System.out.println("HashMap ---"+hm);
		
//		Cookie ck=new Cookie("JSESSIONID",sessionid);//creating cookie object  
//		response.addCookie(ck);
		request.setAttribute("session",hm);
		RequestDispatcher rd = request.getRequestDispatcher("/Ecommerce");
		rd.forward(request, response);
		
	}

}
