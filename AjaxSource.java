package com.banklogic;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

public class AjaxSource extends ActionSupport implements DataMapInterface
{
	public InputStream inputstream;
	String hidden,key;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public InputStream getInputstream()
	{
		return inputstream;
	}

	public void setInputstream(InputStream inputstream)
	{
		this.inputstream = inputstream;
	}

	public String execute()
	{
		try
		{
			if(hidden.equals("cityname"))
			{
				if(key==null || key.equals("") || !pincodelistmap.containsKey(key))
				{
					inputstream=new ByteArrayInputStream(("nil&nil&pincodeajax").trim().getBytes());
				}
				else
				{
					String spl[]=pincodelistmap.get(key).split("\\*");
					inputstream=new ByteArrayInputStream((spl[0]+"&"+spl[2]+"&pincodeajax").trim().getBytes());			
				}
			}
			else
			if(hidden.equals("editholder"))
			{
				DbQueries db=new DbQueries();
				String str=db.getUserAccountInd(key);
				inputstream=new ByteArrayInputStream((str+"&accntinfoajax").trim().getBytes());
			}
			else
			if(hidden.equals("addamountholderaccount"))
			{
				DbQueries db=new DbQueries();
				String str=db.retAccountAmount(key);
				inputstream=new ByteArrayInputStream((str+"&addamountinfoajax").trim().getBytes());
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "success";
	}
}