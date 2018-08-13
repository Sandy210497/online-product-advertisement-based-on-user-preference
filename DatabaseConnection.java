package com.dbconnection;

import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DatabaseConnection implements ServletContextListener
{
	java.sql.Connection con;
	ServletContext sce;
	public void contextDestroyed(ServletContextEvent arg0)
	{
		// TODO Auto-generated method stub
		try
		{
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void contextInitialized(ServletContextEvent arg0) 
	{
		// TODO Auto-generated method stub
		sce = arg0.getServletContext();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","root");
			sce.setAttribute("DBConnection", con);
			System.out.println("------------------------Connection created video-----------------------------");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
