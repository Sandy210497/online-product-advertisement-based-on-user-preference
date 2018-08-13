package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class UserPreference
{
	public String userPref(String username)
	{
		String firstpref="",secondpref="",thirdpref="",fourthpref="",userpref="";
		Vector v = new Vector();
		PreparedStatement ps;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","root");
			String mail = username;
			ps = con.prepareStatement("SELECT * from purchase where purdate BETWEEN DATE_SUB(CURDATE(), INTERVAL 10 DAY) AND CURDATE() and name=?");
			ps.setString(1, mail);
			ResultSet rsssss = ps.executeQuery();
			while(rsssss.next())
			{
				String category = rsssss.getString("category");
				v.add(category);
			}
			System.out.println(" --- User Purchase Category --- "+v);
			ps = con.prepareStatement("SELECT * from cart where adddate BETWEEN DATE_SUB(CURDATE(), INTERVAL 10 DAY) AND CURDATE() and user=?");
			ps.setString(1, mail);
			ResultSet rss = ps.executeQuery();
			while(rss.next())
			{
				String company = rss.getString("company");
				String model = rss.getString("model");
				String category = rss.getString("category");
				if(!v.contains(category))
				{
					firstpref = firstpref+"#"+category+"&"+company+"&"+model;
				}
				
			}
			
			ps = con.prepareStatement("select * from userpreference WHERE count >= (SELECT MAX(count) FROM userpreference WHERE count < (SELECT MAX(count) FROM userpreference)) and username=?");
			ps.setString(1, mail);
			ResultSet rsss = ps.executeQuery();
			while(rsss.next())
			{
				String company = rsss.getString("company");
				String model = rsss.getString("model");
				String category = rsss.getString("category");
				if(!v.contains(category))
				{
					secondpref = secondpref+"#"+category+"&"+company+"&"+model;
				}
				
			}
			
			ps = con.prepareStatement("select * from userpreferencetime WHERE time >= (SELECT MAX(time) FROM userpreferencetime WHERE time < (SELECT MAX(time) FROM userpreferencetime)) and username=?");
			ps.setString(1, mail);
			ResultSet rssss = ps.executeQuery();
			while(rssss.next())
			{
				String company = rssss.getString("company");
				String model = rssss.getString("model");
				String category = rssss.getString("category");
				if(!v.contains(category))
				{
					thirdpref = thirdpref+"#"+category+"&"+company+"&"+model;
				}
			}
			
			userpref=firstpref+"@"+secondpref+"@"+thirdpref;
			System.out.println("----"+userpref);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userpref;
	}
}
