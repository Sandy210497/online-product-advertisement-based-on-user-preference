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

import com.http.servlet.HttpsServlet;

public class PreferenceFind extends HttpsServlet {

	Connection con;
	PreparedStatement ps;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try
		{
			con = (Connection)getServletContext().getAttribute("Connection");
			ps = con.prepareStatement("select email from registration");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String mail = rs.getString("email");

				ps = con.prepareStatement("SELECT * from cart where adddate BETWEEN DATE_SUB(CURDATE(), INTERVAL 10 DAY) AND CURDATE() and user=?");
				ps.setString(1, mail);
				ResultSet rss = ps.executeQuery();
				while(rss.next())
				{
					String company = rss.getString("company");
					String model = rss.getString("model");
					String category = rss.getString("category");
					System.out.println(mail+"=="+company+"---"+model+"---"+category);
				}
				
				ps = con.prepareStatement("select * from userpreference WHERE count >= (SELECT MAX(count) FROM userpreference WHERE count < (SELECT MAX(count) FROM userpreference)) and username=?");
				ps.setString(1, mail);
				ResultSet rsss = ps.executeQuery();
				while(rsss.next())
				{
					String company = rsss.getString("company");
					String model = rsss.getString("model");
					String category = rsss.getString("category");
					System.out.println(mail+"=count="+company+"---"+model+"---"+category);
				}
				
				ps = con.prepareStatement("select * from userpreferencetime WHERE time >= (SELECT MAX(time) FROM userpreferencetime WHERE time < (SELECT MAX(time) FROM userpreferencetime)) and username=?");
				ps.setString(1, mail);
				ResultSet rssss = ps.executeQuery();
				while(rssss.next())
				{
					String company = rssss.getString("company");
					String model = rssss.getString("model");
					String category = rssss.getString("category");
					System.out.println(mail+"=time="+company+"---"+model+"---"+category);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
