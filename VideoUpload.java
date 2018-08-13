package com.logic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.http.servlet.HttpsServlet;

public class VideoUpload extends HttpsServlet {

	


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
	
		File ff = new File(new File("").getAbsolutePath()+"/webapps/"+request.getContextPath()+"/videos/");
		File catef = null;

		
		if(ServletFileUpload.isMultipartContent(request))
		{
			try
			{
                 List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                 for(FileItem item : multiparts)
                 {
                	 String fieldvalue="";
                     if(item.isFormField())
                     {
                    	
                    	 String fieldname = item.getFieldName();
                    	 fieldvalue = item.getString();
                    	 System.out.println(fieldname+"--&--"+fieldvalue);
                    	 catef = new File(ff.getAbsolutePath()+"/"+fieldvalue);
	                     	if(catef.exists())
	                 		{
	                 			
	                 		}
	                 		else
	                 		{
	                 			catef.mkdir();
	                 		}
                     }
                     else
                     {
                    	
                    	
                    	System.out.println("catef -- "+catef.getAbsolutePath());
                    	 
                    	 String name = new File(item.getName()).getName();
                         item.write( new File(catef.getAbsolutePath()+ File.separator + name));
                     }
 
                 }         
			        //File uploaded successfully
		
			      request.setAttribute("message", "File Uploaded Successfully");
		
		    } 
			catch (Exception ex) 
			{
			      request.setAttribute("message", "File Upload Failed due to " + ex);
		    }               
			 
		 }
		else
		{
			request.setAttribute("message","Sorry this Servlet only handles file upload request");
		}
		
//		RequestDispatcher rd = request.getRequestDispatcher("upload.jsp");
//		rd.include(request, response);
		out.flush();
		out.close();
	}

}
