package com.opencv;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.FilenameUtils;
import org.opencv.calib3d.Calib3d;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;

import com.logic.Sample;


public class SURFDetector 
{
	MatOfKeyPoint objectKeyPoints=null;
	String bookObject="";
	String bookScene="";
	
	KeyPoint[] keypoints=null;
	KeyPoint[] scekeypoints = null;
	MatOfKeyPoint objectDescriptors = null;
	DescriptorExtractor descriptorExtractor;
	MatOfKeyPoint objectscene;
	DescriptorMatcher descriptorMatcher;
	File pa = new File("");
	String os = System.getProperty("os.name");
	String bitness = System.getProperty("sun.arch.data.model");
	MatOfKeyPoint sceneKeyPoints,sceneDescriptors;
	File lib = null;
	LinkedList goodMatchesList = null;
	float nndrRatio = 0.7f;
	List matches =null;
	PreparedStatement ps=null;
	
	
	int matchthreshold=0;
	public void keyframeFind(String trainee, String path) throws InterruptedException
	{
		Connection con =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","root");
		
		
		
		int status = 0;	
		if (os.toUpperCase().contains("WINDOWS"))
		{
            if (bitness.endsWith("64")) 
            {
                lib = new File(pa.getAbsolutePath()+"//webapps//VideoHost//libs//x64//" + System.mapLibraryName("opencv_java2413"));
            } 
            else 
            {
                lib = new File(pa.getAbsolutePath()+"//webapps//VideoHost//libs//x86//" + System.mapLibraryName("opencv_java2413"));
                System.out.println("--32bit--"+lib.getAbsolutePath());
            }
        }
		System.load(lib.getAbsolutePath());
		if(new File(trainee).isDirectory())
		{
			File[] f = new File(trainee).listFiles();

			for(File cate:f)
			{
				String keyframecal = "";
				
				String category  = cate.getName();
				System.out.println("---------Video Category Folder---------"+category);
				File[] trains = cate.listFiles();
				for(File train : trains)
				{
					int i = 10;
					int check = 0;
					System.out.println("---------Trainee Image Name-----------"+train.getName());
					File[] sources = new File(path).listFiles();
					
					ArrayList storedata = new ArrayList();
					for(File source : sources)
					{
						
						String ext2 = FilenameUtils.getExtension(source.getName());
						System.out.println("Before : "+Runtime.getRuntime().freeMemory());
						if(ext2.equalsIgnoreCase("jpg"))
						{	
							int framenumber = objectDetection(train.getAbsolutePath(), source.getAbsolutePath(),source);
							if(framenumber!=0)
							{
								storedata.add(framenumber);
							}
						}
						if(check == i)
						{
							System.out.println("------------------Garbage collected :------------------- "+i);
							System.gc();
							i = i+10;
						}
						check++;
						Thread.sleep(10);
						System.out.println("After : "+Runtime.getRuntime().freeMemory());
						System.out.println("Store data ---- "+storedata);
					}
					Collections.sort(storedata);
					int previous=0,initialize=0;
					for(int z =0;z<storedata.size();z++)
			        {
			            int data = (Integer) storedata.get(z);
			            System.out.println("data "+data);
			            if(previous == 0)
			            {
			                previous = data;
			                initialize = data;
			            }
			            else
			            {
			                int diff = data-previous;
			                if(initialize == 0)
			                {
			                     initialize = data;
			                }
			                String keyframe = String.valueOf(initialize)+"*"+String.valueOf(previous);
			                if(diff>100)
			                {
			                     System.out.println(" KeyFrame "+keyframe);
			                     if(keyframecal.isEmpty())
			                     {
			                    	 keyframecal=keyframe;
			                     }
			                     else
			                     {
			                    	 keyframecal = keyframecal+"&"+keyframe;
			                     } 
			                     initialize = 0;	
			                }
			                if(z == storedata.size()-1)
			                {
			                    System.out.println(" LKeyFrame "+keyframe);
			                    if(keyframecal.isEmpty())
			                     {
			                    	 keyframecal=keyframe;
			                     }
			                     else
			                     {
			                    	 keyframecal = keyframecal+"&"+keyframe;
			                     } 
			                }
			                previous = data;
			            }
			        }
					System.out.println(train.getName()+"------------Key Frame-------------"+keyframecal);
					
				}
				String videoname = new File(path).getName();
				System.out.println("-----------videoname----------"+videoname);
				ps = con.prepareStatement("select * from keyframe where videoname=?");
				ps.setString(1, videoname);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					if(category.equalsIgnoreCase("mobile"))
					{
						ps = con.prepareStatement("update keyframe set mobile=? where videoname=?");
					}
					else if(category.equalsIgnoreCase("camera"))
					{
						ps = con.prepareStatement("update keyframe set camera=? where videoname=?");
					}
					else if(category.equalsIgnoreCase("television"))
					{
						ps = con.prepareStatement("update keyframe set television=? where videoname=?");
					}
					ps.setString(1,keyframecal);
					ps.setString(2,videoname);
					ps.executeUpdate();
					System.out.println("Updated!!!!!!!!!!");
				}
				else
				{
					if(category.equalsIgnoreCase("mobile"))
					{
						ps = con.prepareStatement("insert into keyframe(videoname,mobile) values(?,?)");
					}
					else if(category.equalsIgnoreCase("camera"))
					{
						ps = con.prepareStatement("insert into keyframe(videoname,camera) values(?,?)");
					}
					else if(category.equalsIgnoreCase("television"))
					{
						ps = con.prepareStatement("insert into keyframe(videoname,television) values(?,?)");
					}
					ps.setString(1,videoname);
					ps.setString(2,keyframecal);
					int i = ps.executeUpdate();
					if(i == 1)
					{
						System.out.println("Frame updated!!!!");
					}
				}
				
				
				System.out.println(category+" -------------- Total Frame ---------------"+keyframecal);
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public int objectDetection(String trainee, String scene,File source)
	{
		int status=0;
		bookObject = trainee;
		bookScene = scene;
		String originalfile = source.getName();
		System.out.println(originalfile+" processing....");
		Mat objectImage = Highgui.imread(bookObject,Highgui.CV_LOAD_IMAGE_COLOR);
		Mat sceneImage = Highgui.imread(bookScene,Highgui.CV_LOAD_IMAGE_COLOR);
		objectKeyPoints = new MatOfKeyPoint();
		FeatureDetector featureDetector = FeatureDetector
				.create(FeatureDetector.SURF);
		featureDetector.detect(objectImage, objectKeyPoints);
		keypoints = objectKeyPoints.toArray();
		objectscene = new MatOfKeyPoint();
		featureDetector.detect(sceneImage, objectscene);
		scekeypoints = objectscene.toArray();
		MatOfKeyPoint objectDescriptors = new MatOfKeyPoint();
		descriptorExtractor = DescriptorExtractor
				.create(DescriptorExtractor.SURF);
		descriptorExtractor.compute(objectImage, objectKeyPoints,
				objectDescriptors);
		sceneKeyPoints = new MatOfKeyPoint();
		sceneDescriptors = new MatOfKeyPoint();
		featureDetector.detect(sceneImage, sceneKeyPoints);		
		descriptorExtractor.compute(sceneImage, sceneKeyPoints,
				sceneDescriptors);
		matches = new LinkedList();
		descriptorMatcher = DescriptorMatcher
				.create(DescriptorMatcher.FLANNBASED);
		descriptorMatcher.knnMatch(objectDescriptors, sceneDescriptors,
				matches, 2);
		goodMatchesList = new LinkedList();

		matchthreshold = (int) (keypoints.length*0.1);
		System.out.println("Matches Threshold : "+matchthreshold);
		
		for (int i = 0; i < matches.size(); i++)
		{
			MatOfDMatch matofDMatch = (MatOfDMatch) matches.get(i);
			DMatch[] dmatcharray = matofDMatch.toArray();
			DMatch m1 = dmatcharray[0];
			DMatch m2 = dmatcharray[1];

			if (m1.distance <= m2.distance * nndrRatio) 
			{
				goodMatchesList.addLast(m1);
			}
		}
		if (goodMatchesList.size() >= matchthreshold)
		{
			System.out.println("Object Found!!!");
			
			status = Integer.valueOf(originalfile.substring(0, originalfile.indexOf(".")));
		}
		else
		{
			System.out.println("Object Not Found");
		}
		
		return status;
	}	
	
	public static void main(String args[])
	{
		SURFDetector sd = new SURFDetector();
		System.out.println("before : "+Runtime.getRuntime().freeMemory());
		sd.objectDetection("D:/ApacheSoftwareFoundation/Tomcat6.0/webapps/VideoHost/trainee/DOCUMENTARY/v2/mobile/mob.jpg", "D:/ApacheSoftwareFoundation/Tomcat6.0/webapps/VideoHost/trainee/DOCUMENTARY/v2/mobile/mob.jpg", new File(""));
		System.out.println("After : "+Runtime.getRuntime().freeMemory());
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("GC After : "+Runtime.getRuntime().freeMemory());
	}
}
