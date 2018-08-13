package com.banklogic;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DbListener implements ServletContextListener
{
	Connection conn=null;	
	public void contextDestroyed(ServletContextEvent arg0)
	{
		try
		{
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void contextInitialized(ServletContextEvent arg1)
	{
		ServletContext sc=arg1.getServletContext();
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=DriverManager.getConnection("jdbc:mysql://localhost/bankadmindb","root","root");
			sc.setAttribute("connection",conn);
			System.out.println("BankAdmin Connection Created");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
 	}
}