package com.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.http.servlet.HttpsServlet;

public class TimeFrequency extends HttpsServlet {

	
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
		Vector v = new Vector();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String time = request.getParameter("val");
		String model = request.getParameter("model");
		String company = request.getParameter("companyname");
		String category = request.getParameter("category");
		v.add(time);
		
		FrequencyPrefBean fpb = new FrequencyPrefBean(); 
		fpb.setCategory(category);
		fpb.setModel(model);
		fpb.setCompanyname(company);
		fpb.setTime(time);
		
		HttpSession hs = request.getSession();
		Vector usertimepref = (Vector) hs.getAttribute("usertimepref");
		usertimepref.add(fpb);
		
		out.println("success");
	}

}
