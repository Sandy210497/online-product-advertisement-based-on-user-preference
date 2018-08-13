package com.banklogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

public class XlApi implements Serializable,DataMapInterface
{
    BankLogin banklogin;
    public XlApi(String filePath,BankLogin banklogin)
    {
        try
        {
        	this.banklogin=banklogin;
            readLines(filePath);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void readLines(String filePath)
    {
        try
        {
            FileInputStream fstream1 = new FileInputStream(filePath);
            BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream1));
            String strLine1;
            int m1=0;
            File ff=new File("webapps/BankAdmin/citynames.txt");
            ff.delete();
            FileOutputStream fos=new FileOutputStream("webapps/BankAdmin/citynames.txt",true);
            while ((strLine1 = br1.readLine()) != null)
            {
                if(strLine1!="")
                {
                    if(strLine1.contains(","))
                    {
                         if((m1==0))
                         {
                        	 m1++;
                         }
                         else
                         if(m1>=1)
                         {
                        	 String dataArry[]=strLine1.split("\\,");
                        	 if(!pincodelistmap.containsKey(dataArry[0]))
							 {
                        		 pincodelistmap.put(dataArry[0].trim(),dataArry[1].trim()+"*"+dataArry[2].trim()+"*"+dataArry[3].trim());
                        		 if(m1==1)
                            	 {
                            		 fos.write(dataArry[0].trim().getBytes());
                            	 }
                            	 else
                            	 {
                            		 fos.write(("\n"+dataArry[0].trim()).getBytes());                        		 
                            	 }
							 }                        	 
                        	 m1++;
                         }
                    }
                }
            }
            fos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}