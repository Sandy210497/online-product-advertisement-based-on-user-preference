package com.bankwebser;

import java.io.FileInputStream;
import java.util.Properties;

public class CommonAccess
{
	public String getPort()
	{
		String port="";
		try
		{
			Properties prop=new Properties();
	        FileInputStream fis=new FileInputStream("webapps/BankWebServer/commondata.properties");
	        prop.load(fis);
	        port=prop.getProperty("portnumber").trim();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return port;
	}
}
