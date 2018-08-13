package com.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.http.servlet.HttpsServlet;
import com.payment.BankWebServiceDelegate;
import com.payment.BankWebServiceService;


public class PaymentProcess extends HttpsServlet 
{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String category ="";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Date now = new Date();
		Connection con = (Connection)getServletContext().getAttribute("Connection");
		String username = request.getParameter("username");
		String amount = request.getParameter("amount");
		String productid = request.getParameter("productid");
		String procate = request.getParameter("category");
		if(procate == null)
		{
			category = "Mobile";
		}
		else
		{
			category = procate;
		}
		String model = request.getParameter("model");
		String company = request.getParameter("companyname");
		
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String mysqlDateString = formatter.format(now);
		System.out.println(username+"<------->"+amount);
		HttpSession hs = request.getSession();
		String id = hs.getId();
		getServletContext().setAttribute(id, hs);
		try
		{
			PreparedStatement pss = con.prepareStatement("delete from cart where user=? and productid=? and company=? and model=?");
			pss.setString(1, username);
			pss.setString(2, productid);
			pss.setString(3, company);
			pss.setString(4, model);
			pss.executeUpdate(); 
			
			PreparedStatement ps = con.prepareStatement("insert into purchase values(?,?,?,?,?,?,?)");
			ps.setString(1,username);
			ps.setString(2,productid);
			ps.setString(3, category);
			ps.setString(4, company);
			ps.setString(5, model);
			ps.setString(6, mysqlDateString);
			ps.setString(7, "paid");
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		BankWebServiceService bwss = new BankWebServiceService();
		BankWebServiceDelegate bwsd = bwss.getBankWebServicePort();
		String status = bwsd.setPayment(username+"$"+id, amount);
		response.sendRedirect(response.encodeRedirectURL(status));
		
	}

}
