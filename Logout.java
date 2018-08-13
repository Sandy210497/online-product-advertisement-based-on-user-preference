package com.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.http.servlet.HttpsServlet;

public class Logout extends HttpsServlet {

	Connection con;
	PreparedStatement pss;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		con= (Connection)getServletContext().getAttribute("Connection");
		try
		{
			HttpSession hs = request.getSession(false);
			System.out.println("User : "+hs.getAttribute("username"));
			String username = (String)hs.getAttribute("username");
			Vector userprefv = (Vector)hs.getAttribute("userpref");
			Vector usertimepref = (Vector)hs.getAttribute("usertimepref");
			
			if(userprefv != null)
			{
				for(int i = 0;i<userprefv.size();i++)
				{
					FrequencyPrefBean sps = (FrequencyPrefBean) userprefv.get(i);
					String cat = sps.getCategory();
					if(cat == null)
					{
						cat = "Mobile";
					}
					String com = sps.getCompanyname();
					String mod = sps.getModel();
					pss = con.prepareStatement("select * from userpreference where username=? and category=? and company=? and model=?");
					pss.setString(1, username);
					pss.setString(2, cat);
					pss.setString(3, sps.getCompanyname());
					pss.setString(4, sps.getModel());
					
					ResultSet rs = pss.executeQuery();
					if(rs.next())
					{
						System.out.println("Enter in IF Conditon");
						int count = rs.getInt("count");
						pss = con.prepareStatement("update userpreference set count=?  where username=? and category=? and company=? and model=?");
						pss.setInt(1, count+1);
						pss.setString(2, username);
						pss.setString(3, cat);
						pss.setString(4, sps.getCompanyname());
						pss.setString(5, sps.getModel());
						pss.executeUpdate();
					}
					else
					{
						pss = con.prepareStatement("insert into userpreference values(?,?,?,?,?)");
						pss.setString(1, username);
						pss.setString(2, cat);
						pss.setString(3, sps.getCompanyname());
						pss.setString(4, sps.getModel());
						pss.setInt(5, 1);
						pss.executeUpdate();
						System.out.println(sps.getCategory()+"<---->"+sps.getCompanyname()+"<---->"+sps.getModel());
					}
					
				}
			}
			if(usertimepref!=null)
			{
				for(int i = 0;i<usertimepref.size();i++)
				{
					FrequencyPrefBean sps = (FrequencyPrefBean) usertimepref.get(i);
					System.out.println(sps.getCategory()+"<---->"+sps.getCompanyname()+"<---->"+sps.getModel()+"<--->"+sps.getTime());
					String cat = sps.getCategory();
					if(cat == null)
					{
						cat = "Mobile";
					}
					String com = sps.getCompanyname();
					String mod = sps.getModel();
					pss = con.prepareStatement("select * from userpreferencetime where username=? and category=? and company=? and model=?");
					pss.setString(1, username);
					pss.setString(2, cat);
					pss.setString(3, sps.getCompanyname());
					pss.setString(4, sps.getModel());
					
					ResultSet rs = pss.executeQuery();
					if(rs.next())
					{
						System.out.println("Enter in IF Conditon");
						int count = rs.getInt("time");
						pss = con.prepareStatement("update userpreferencetime set time=?  where username=? and category=? and company=? and model=?");
						pss.setInt(1, count+Integer.parseInt(sps.getTime()));
						pss.setString(2, username);
						pss.setString(3, cat);
						pss.setString(4, sps.getCompanyname());
						pss.setString(5, sps.getModel());
						pss.executeUpdate();
					}
					else
					{
						pss = con.prepareStatement("insert into userpreferencetime values(?,?,?,?,?)");
						pss.setString(1, username);
						pss.setString(2, cat);
						pss.setString(3, sps.getCompanyname());
						pss.setString(4, sps.getModel());
						pss.setInt(5, Integer.parseInt(sps.getTime()));
						pss.executeUpdate();
						System.out.println(sps.getCategory()+"<---->"+sps.getCompanyname()+"<---->"+sps.getModel());
					}
					
				}
			}
			System.out.println("-----------------------------------------------------");
			
			if(hs != null)
			{
				getServletContext().removeAttribute(hs.getId());
				hs.removeAttribute("username");
				hs.invalidate();
				response.setHeader("Cache-Control","no-cache");
				response.setHeader("Cache-Control","no-store");
				response.setHeader("Pragma","no-cache");
				response.setDateHeader ("Expires", 0);
				HttpSession hs1 = request.getSession(false);
				if(hs1==null)
				{
					System.out.println("---Session Object---"+hs);
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				    rd.forward(request, response);
				}
				else
				{
					out.println("Session Valid");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
