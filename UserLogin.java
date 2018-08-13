package com.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.http.servlet.HttpsServlet;

public class UserLogin extends HttpsServlet {

	Connection con;
	PreparedStatement ps;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try
		{
			System.out.println("------Login call------");
			String user = request.getParameter("username");
			String password = request.getParameter("password");
			con = (Connection)getServletContext().getAttribute("Connection");
			System.out.println(user+"----------"+password);
			ps = con.prepareStatement("select * from registration where email=?");
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				String upassword = rs.getString("password");
				if(password.equalsIgnoreCase(upassword))
				{
					HttpSession hs = request.getSession(true);
					Vector userpref = new Vector();
					Vector usertimepref = new Vector();
					hs.setAttribute("username", user);
					hs.setAttribute("userpref", userpref);
					hs.setAttribute("usertimepref", usertimepref);
					System.out.println(user+"-----Session ID-----"+hs.getId());
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					System.out.println("user Creation Time : "+sdf.format(new Date(hs.getCreationTime())));
					System.out.println("user Last Access Time : "+hs.getLastAccessedTime());
					RequestDispatcher rd = request.getRequestDispatcher("usermain.jsp");
					rd.forward(request, response);
				}
				else
				{
					request.setAttribute("result","Invalid Password");
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}
			}
			else
			{
				request.setAttribute("result","Invalid UserName");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
