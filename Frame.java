package com.logic;

import java.io.File;
import org.eclipse.swt.program.Program;

public class Frame extends Thread
{
	File f = null;
	Frame(File f)
	{
		this.f = f;
	}
	public void run()
	{
		synchronized(this)
		{
			Process proc = null;
			try
			{
				proc = Runtime.getRuntime().exec(f.getAbsolutePath()+ "\\" + "Run.bat");
		        proc.getOutputStream();
		        proc.getInputStream();
		        Program.launch(f.getAbsolutePath() + "\\" + "Run.bat");
		        sleep(6000);
        		notify();  
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
	}
}
