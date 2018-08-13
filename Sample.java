package com.logic;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

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

public class Sample implements Runnable
{
	String trainee,path;
	File f;
	public Sample(String trainee,String path,File f)
	{
		this.trainee = trainee;
		this.path = path;
		this.f =f;
	}
	public void run()
	{
		
		
//		
		File lib = null;
		String os = System.getProperty("os.name");
        String bitness = System.getProperty("sun.arch.data.model");
		
		if (os.toUpperCase().contains("WINDOWS")) {
            if (bitness.endsWith("64")) {
                lib = new File("webapps//VideoHost//libs//x64//" + System.mapLibraryName("opencv_java2413"));
                System.out.println("--64bit--"+lib.getAbsolutePath());
            } 
            else 
            {
                lib = new File("webapps//VideoHost//libs//x86//" + System.mapLibraryName("opencv_java2413"));
                System.out.println("--32bit--"+lib.getAbsolutePath());
            }
        }

		System.load(lib.getAbsolutePath());
		String bookObject = trainee;
		System.out.println("Select object"+bookObject);
		String bookScene = path;
		
		
//		System.out.println("Started....");
//		System.out.println("Loading images...");
		Mat objectImage = Highgui.imread(bookObject,Highgui.CV_LOAD_IMAGE_COLOR);
		Mat sceneImage = Highgui.imread(bookScene,Highgui.CV_LOAD_IMAGE_COLOR);

		MatOfKeyPoint objectKeyPoints = new MatOfKeyPoint();
		FeatureDetector featureDetector = FeatureDetector
				.create(FeatureDetector.SURF);
		System.out.println("Detecting key points...");
		featureDetector.detect(objectImage, objectKeyPoints);
		KeyPoint[] keypoints = objectKeyPoints.toArray();
	//

		MatOfKeyPoint objectscene = new MatOfKeyPoint();
		featureDetector.detect(sceneImage, objectscene);
		KeyPoint[] scekeypoints = objectscene.toArray();
	//	System.out.println("scene : "+scekeypoints.length);
		MatOfKeyPoint objectDescriptors = new MatOfKeyPoint();
		DescriptorExtractor descriptorExtractor = DescriptorExtractor
				.create(DescriptorExtractor.SURF);
//		System.out.println("Computing descriptors...");
		descriptorExtractor.compute(objectImage, objectKeyPoints,
				objectDescriptors);

		// Create the matrix for output image.
		Mat outputImage = new Mat(objectImage.rows(), objectImage.cols(),
				Highgui.CV_LOAD_IMAGE_COLOR);
		Scalar newKeypointColor = new Scalar(255, 0, 0);

//		System.out.println("Drawing key points on object image...");
		Features2d.drawKeypoints(objectImage, objectKeyPoints, outputImage,
				newKeypointColor, 0);

		// Match object image with the scene image
		MatOfKeyPoint sceneKeyPoints = new MatOfKeyPoint();
		MatOfKeyPoint sceneDescriptors = new MatOfKeyPoint();
//		System.out.println("Detecting key points in background image...");
		featureDetector.detect(sceneImage, sceneKeyPoints);
//		System.out.println("Computing descriptors in background image...");
		descriptorExtractor.compute(sceneImage, sceneKeyPoints,
				sceneDescriptors);

		Mat outputsceImage = new Mat(sceneImage.rows(), sceneImage.cols(),
				Highgui.CV_LOAD_IMAGE_COLOR);
		Scalar newKeypointsceColor = new Scalar(0, 0, 255);

//		System.out.println("Drawing key points on object image...");
		Features2d.drawKeypoints(sceneImage, sceneKeyPoints, outputsceImage,
				newKeypointsceColor, 0);
		Mat matchoutput = new Mat(sceneImage.rows() * 2, sceneImage.cols() * 2,
				Highgui.CV_LOAD_IMAGE_COLOR);
		Scalar matchestColor = new Scalar(0, 255, 0);

		List matches = new LinkedList();
		DescriptorMatcher descriptorMatcher = DescriptorMatcher
				.create(DescriptorMatcher.BRUTEFORCE);
//		System.out.println("Matching object and scene images...");
		
		descriptorMatcher.knnMatch(objectDescriptors, sceneDescriptors,
				matches, 2);
		
//		System.out.println("Calculating good match list...");
		LinkedList goodMatchesList = new LinkedList();

		int matchthreshold = (int) (keypoints.length*0.1);
		
		System.out.println("Match Threshold :: "+matchthreshold);
		float nndrRatio = 0.7f;
		
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
		//tm.put(output, goodMatchesList.size());
		System.out.println("Match ---"+goodMatchesList.size());
		if (goodMatchesList.size() >= matchthreshold)
			
		{
			System.out.println("Object Found!!!");

			List objKeypointlist = objectKeyPoints.toList();
			List scnKeypointlist = sceneKeyPoints.toList();

			LinkedList objectPoints = new LinkedList();
			LinkedList scenePoints = new LinkedList();

			for (int i = 0; i < goodMatchesList.size(); i++) 
			{
				// queryIdx
				objectPoints.addLast(((KeyPoint) objKeypointlist
						.get(((DMatch) goodMatchesList.get(i)).queryIdx)).pt);
				scenePoints.addLast(((KeyPoint) scnKeypointlist
						.get(((DMatch) goodMatchesList.get(i)).trainIdx)).pt);
			}

//			MatOfPoint2f objMatOfPoint2f = new MatOfPoint2f();
//			objMatOfPoint2f.fromList(objectPoints);
//			MatOfPoint2f scnMatOfPoint2f = new MatOfPoint2f();
//			scnMatOfPoint2f.fromList(scenePoints);
//
//			Mat homography = Calib3d.findHomography(objMatOfPoint2f,
//					scnMatOfPoint2f, Calib3d.RANSAC, 3);
//
//			Mat obj_corners = new Mat(4, 1, CvType.CV_32FC2);
//			Mat scene_corners = new Mat(4, 1, CvType.CV_32FC2);
//
//			obj_corners.put(0, 0, new double[] { 0, 0 });
//			obj_corners.put(1, 0, new double[] { objectImage.cols(), 0 });
//			obj_corners.put(2, 0, new double[] { objectImage.cols(),
//					objectImage.rows() });
//			obj_corners.put(3, 0, new double[] { 0, objectImage.rows() });
//
//			System.out
//					.println("Transforming object corners to scene corners...");
//			Core.perspectiveTransform(obj_corners, scene_corners, homography);
//
//			Mat img = Highgui.imread(bookScene, Highgui.CV_LOAD_IMAGE_COLOR);
//
//			Core.line(img, new Point(scene_corners.get(0, 0)), new Point(
//					scene_corners.get(1, 0)), new Scalar(0, 255, 0), 4);
//			Core.line(img, new Point(scene_corners.get(1, 0)), new Point(
//					scene_corners.get(2, 0)), new Scalar(0, 255, 0), 4);
//			Core.line(img, new Point(scene_corners.get(2, 0)), new Point(
//					scene_corners.get(3, 0)), new Scalar(0, 255, 0), 4);
//			Core.line(img, new Point(scene_corners.get(3, 0)), new Point(
//					scene_corners.get(0, 0)), new Scalar(0, 255, 0), 4);
//
//			System.out.println("Drawing matches image...");
//			MatOfDMatch goodMatches = new MatOfDMatch();
//			goodMatches.fromList(goodMatchesList);
//
//			Features2d.drawMatches(objectImage, objectKeyPoints, sceneImage,
//					sceneKeyPoints, goodMatches, matchoutput, matchestColor,
//					newKeypointColor, new MatOfByte(), 2);	
//			String originalfile = source.getName();
//			status = Integer.valueOf(originalfile.substring(0, originalfile.indexOf(".")));
		}
		else
		{
			System.out.println("Object Not Found");
		}
		
		
		
	}

}
