package com.bankwebser;

import java.io.FileInputStream;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import freemarker.cache.StringTemplateLoader;

public class BankWebService
{
	public String setPayment(String mailid,String payment)
	{
		String url="";
		String sessionid = "";
		String mail="";
		try
		{
			StringTokenizer st = new StringTokenizer(mailid,"$");
			while(st.hasMoreTokens())
			{
				mail = st.nextToken();
				sessionid = st.nextToken();
			}
			Random r=new Random();
			//--------------------------------------------------
			char[] map1 = new char[68];
            int i = 0;
            for(char c = 'A'; c <= 'Z'; c++)
            {
                map1[i++] = c;
            }
            for(char c = 'a'; c <= 'z'; c++)
            {
                map1[i++] = c;
            }
            for (char c = '0'; c <= '9'; c++)
            {
                map1[i++] = c;
            }
            map1[i++] = '+';
            map1[i++] = '/';
            map1[i++] = '$';
            map1[i++] = '#';
            map1[i++] = '&';
            map1[i++] = '*';

            String p1=String.valueOf(map1[r.nextInt(67)]);
            String p2=String.valueOf(map1[r.nextInt(67)]);
            String p3=String.valueOf(map1[r.nextInt(67)]);
            String p4=String.valueOf(map1[r.nextInt(67)]);
            String p5=String.valueOf(map1[r.nextInt(67)]);
            String p6=String.valueOf(map1[r.nextInt(67)]);
            String p7=String.valueOf(map1[r.nextInt(67)]);
            String p8=String.valueOf(map1[r.nextInt(67)]);
            String onetypepassword=p1+p2+p3+p4+p5+p6+p7+p8;
			//--------------------------------------------------
			
			String str=commonMet();
			String spl[]=str.split("\\=");			
			String to=mail.trim();
			String from = spl[0].trim();
			String subject = "MAIL ALERT - PASSCODE";			
			String messagge="Your OPASS :"+onetypepassword+"";
			String host = "smtp.gmail.com";			
			
			DbQueries db=new DbQueries();
			db.insertOpassUser(mail,onetypepassword,payment);
			
			String ipaddr=InetAddress.getLocalHost().getHostAddress();
			String port=new CommonAccess().getPort();
//			Mail msend = new Mail(host,from,spl[1].trim(),from,to,subject,messagge);
			url="http://"+ipaddr+":"+port+"/BankAdmin/setuserpayment.jsp?sessionId="+sessionid;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return url;
	}
	
	public String commonMet()
	{
		String accmail="",mailpassword="";
		try
		{
			Properties prop=new Properties();
			FileInputStream fis=new FileInputStream("webapps/BankWebServer/Mail.properties");
			prop.load(fis);
			accmail=prop.getProperty("mailid");
			mailpassword=prop.getProperty("mailpassword");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return accmail+"="+mailpassword;
	}
}
