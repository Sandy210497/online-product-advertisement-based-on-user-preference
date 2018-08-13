package com.bankwebser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class DbQueries
{
	String qu="";
	Connection con=null;
	PreparedStatement pst=null;
	
	public DbQueries()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con=DriverManager.getConnection("jdbc:mysql://localhost/bankadmindb","root","root");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void insertOpassUser(String mailid,String opass,String transamount)
	{
		try
		{
			String accnt="";
			pst=con.prepareStatement("select accountno from userinfo where mailid=?");
			pst.setString(1,mailid);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				accnt=rs.getString(1);
			}
			pst=con.prepareStatement("update useraccountinfo set opass=?,transferamount=? where accountno=?");
			pst.setString(1,opass);
			pst.setString(2,transamount);
			pst.setString(3,accnt);
			pst.executeUpdate();
			pst.close();
		}
		catch (Exception e)	
		{
			e.printStackTrace();
		}
	}
}