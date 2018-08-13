package com.logic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opencv.SURFDetector;

public class Keyframe extends HttpServlet 
{	
	SURFDetector suf = new SURFDetector();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		File f = new File(new File("").getAbsolutePath()+"/webapps/"+request.getContextPath()+"/Frame/");
		File traf = new File(new File("").getAbsolutePath()+"/webapps/"+request.getContextPath()+"/trainee/");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		String choosevideo = request.getParameter("checkbox");
		System.out.println("--Choose video --"+choosevideo);
		String[] videos = choosevideo.split(",");
		for(int i = 0; i < videos.length ; i++)
		{
			System.out.println("--Choose Video--"+videos[i]);
			String cate = videos[i].substring(0, videos[i].indexOf("$"));
			String vid = videos[i].substring( videos[i].indexOf("$")+1,videos[i].length());
			
			String path = new File(f.getAbsolutePath()+"/"+cate+"/"+vid).getAbsolutePath();
			String trapath = new File(traf.getAbsolutePath()+"/"+cate+"/"+vid).getAbsolutePath();
			System.out.println("----Servlet for loop Folder directory ----"+path);
			System.out.println("----Servlet for loop Folder trainee ------"+trapath);
			try 
			{
				suf.keyframeFind(trapath,path);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		out.flush();
		out.close();
	}

}
