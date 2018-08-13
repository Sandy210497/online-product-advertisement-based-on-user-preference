package com.banklogic;

import java.io.FileInputStream;
import java.util.Properties;
import com.opensymphony.xwork2.ActionSupport;

public class UserPaymentProc extends ActionSupport
{
	String firstbankholdernames,firstamtpin,firstopass;
	String url;
	String sessionId="";
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFirstbankholdernames() {
		return firstbankholdernames;
	}

	public void setFirstbankholdernames(String firstbankholdernames) {
		this.firstbankholdernames = firstbankholdernames;
	}

	public String getFirstamtpin() {
		return firstamtpin;
	}

	public void setFirstamtpin(String firstamtpin) {
		this.firstamtpin = firstamtpin;
	}

	public String getFirstopass() {
		return firstopass;
	}

	public void setFirstopass(String firstopass) {
		this.firstopass = firstopass;
	}

	public String execute()
	{
		String status="redirect";
		try
		{
			DbQueries db=new DbQueries();
			CommonBean com=new CommonBean();
			com.setAccountnumb(firstbankholdernames);
			com.setPinpass(firstamtpin);
			com.setOpass(firstopass);
			int dbst=db.checkTransaction(com);
			if(dbst==0)
			{
				status="error";
				addActionMessage("Transaction Failed!");
			}
			else
			{
				addActionMessage("Transaction Successfull!");
			}
			String det=commonMet();
			System.out.println(sessionId);
//				url="http://"+det.split("\\=")[0]+":"+det.split("\\=")[1]+"/LFIDMAKA/index.jsp";
//				url="http://"+det.split("\\=")[0]+":"+det.split("\\=")[1]+"/CloudBroker/PriceDetails";
//				url="http://"+det.split("\\=")[0]+":"+det.split("\\=")[1]+"/CloudBroker/PaymentSuccess?sessionId="+sessionId;
			url="http://"+det.split("\\=")[0]+":"+det.split("\\=")[1]+"/eCommerce/PaymentSuccess?sessionId="+sessionId;
							
		}
		catch(Exception e)
		
		
		{
			e.printStackTrace();
		}
		return status;
	}
	
	
	
	
	public String commonMet()
	{
		String servername="",portnum="";
		try
		{
			Properties prop=new Properties();
			FileInputStream fis=new FileInputStream("webapps/BankAdmin/common.properties");
			prop.load(fis);
			servername=prop.getProperty("distserver");
			portnum=prop.getProperty("distport");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return servername+"="+portnum;
	}
}
