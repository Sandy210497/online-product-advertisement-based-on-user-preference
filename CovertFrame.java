package com.logic;
 

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;

import org.eclipse.swt.program.Program;


public class CovertFrame 
{
	String path,videoname,category;
	File f;
//	CovertFrame(String path, File ff, String videoname, String category)
//	{
//		this.path = path;
//		this.f = ff;
//		this.videoname = videoname;
//		this.category = category;
//	}
	 public void convert_To_Frame(String path , File f,String videoname)
    {
        File createFolder;
        Process proc;
        try
        {
        	String video = videoname.substring(0, videoname.indexOf("."));
            createFolder = new File("");
            System.out.println("--path---"+createFolder.getAbsolutePath());
            createFolder.mkdir();
            String frame = "ffmpeg -i " + path + " " + createFolder + "\\" + "image%%d.jpg";
            FileWriter fstream = new FileWriter(createFolder + "\\" + "Run.bat");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(frame);
            out.close();
            proc = Runtime.getRuntime().exec(createFolder + "\\" + "Run.bat");
            proc.getOutputStream();
            proc.getInputStream();
            Program.launch(createFolder + "\\" + "Run.bat");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    
	public static void main()
	{
		File ff = new File("");
		CovertFrame fds =  new CovertFrame();
		fds.convert_To_Frame("D:/Videos/vm.mp4",ff,"vm.mp4");
	}
//		// TODO Auto-generated method stub
//		File createFolder;
//        Process proc;
//        try
//        {
//        	String video = videoname.substring(0, videoname.indexOf("."));
//            createFolder = new File(f.getAbsolutePath()+"/Frame/"+category+"/"+video);
//            System.out.println("--path---"+createFolder.getAbsolutePath());
//            String frame = "ffmpeg -i " + path + " " + createFolder + "\\" + "image%%d.jpg";
//            FileWriter fstream = new FileWriter(createFolder + "\\" + "Run.bat");
//            BufferedWriter out = new BufferedWriter(fstream);
//            out.write(frame);
//            out.close();
//            proc = Runtime.getRuntime().exec(createFolder + "\\" + "Run.bat");
//            proc.getOutputStream();
//            proc.getInputStream();
//            Program.launch(createFolder + "\\" + "Run.bat");
//        }
//        catch(Exception exception)
//        {
//            exception.printStackTrace();
//        }
//		
//	}

	
}
