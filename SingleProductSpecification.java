package com.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.http.servlet.HttpsServlet;

public class SingleProductSpecification extends HttpsServlet {

	Connection con;
	PreparedStatement ps;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try
		{
			ProductSpecification p = new ProductSpecification();
			CameraSpecification cs = new CameraSpecification();
			TeleSpecification ts = new TeleSpecification();
			FrequencyPrefBean fpb = new FrequencyPrefBean();
			
			String category = request.getParameter("category");
			String companyname = request.getParameter("company");
			String model = request.getParameter("model");
			
			HttpSession hs = request.getSession(false);
			Vector userprefv = (Vector)hs.getAttribute("userpref");
			fpb.setCategory(category);
			fpb.setCompanyname(companyname);
			fpb.setModel(model);
			userprefv.add(fpb);
			System.out.println("UserPreference Servlet vector------"+userprefv);
			
			con = (Connection)getServletContext().getAttribute("Connection");
			if(category == null)
			{
				ps = con.prepareStatement("select * from productspec where companyname=? and model=?");
				ps.setString(1,companyname);
				ps.setString(2,model);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					p.setProductid(rs.getString(1));
					p.setCategory(rs.getString(2));
					p.setCompanyname(rs.getString(3));
					p.setModel(rs.getString(4));
	                p.setTechnology(rs.getString(5));
	                p.setSecondg(rs.getString(6));
	                p.setThreeg(rs.getString(7));
	                p.setFourg(rs.getString(8));
	                p.setSpeed(rs.getString(9));
	                p.setGprs(rs.getString(10));
	                p.setEdge(rs.getString(11));
	                p.setFirstpre(rs.getString(12));
	                p.setStatus(rs.getString(13));
	                p.setMeasurement(rs.getString(14));
	                p.setWeight(rs.getString(15));
	                p.setSim(rs.getString(16));
	                p.setType(rs.getString(17));
	                p.setSize(rs.getString(18));
	                p.setResolution(rs.getString(19));
	                p.setMultitouch(rs.getString(20));
					 p.setOs(rs.getString(21));
					 p.setChipset(rs.getString(22));
					 p.setCpu(rs.getString(23));
					 p.setGpu(rs.getString(24));
					 p.setSmc(rs.getString(25));
					 p.setBuildin(rs.getString(26));
					 p.setPrimary(rs.getString(27));
					 p.setFeature(rs.getString(28));
					 p.setVideo(rs.getString(29));
					 p.setSecondary(rs.getString(30));
					 p.setDalarmtype(rs.getString(31));
					 p.setMainspeaker(rs.getString(32));
					 p.setAudiojack(rs.getString(33));
					 p.setWlan(rs.getString(34));
					 p.setBluetooth(rs.getString(35));
					 p.setGps(rs.getString(36));
					 p.setRadio(rs.getString(37));
					 p.setUsb(rs.getString(38));
					 p.setSensor(rs.getString(39));
					 p.setMessage(rs.getString(40));
					 p.setBrower(rs.getString(41));
					 p.setJava(rs.getString(42));
					 p.setBattery(rs.getString(43));
					 p.setColor(rs.getString(44));
					 p.setPrice(rs.getString(45)); 	 
				}
				System.out.println("Technology "+p.getTechnology());
				request.setAttribute("data", p);
				RequestDispatcher rd = request.getRequestDispatcher("single.jsp");
				rd.forward(request, response);
			}
			else if(category.equalsIgnoreCase("Camera"))
			{
				System.out.println("------------------->"+category+"---"+companyname+"---"+model+"<-------------------");
				ps = con.prepareStatement("select * from cameraspec where companyname=? and model=?");
				ps.setString(1,companyname);
				ps.setString(2,model);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					System.out.println("<--connectivitiy-->"+rs.getString("connectivity"));
					cs.setProductid(rs.getString(1));
					cs.setCompanyname(rs.getString(2));
					cs.setModel(rs.getString(3));
					cs.setConnectivity(rs.getString(4));
					cs.setZoom(rs.getString(5));
					cs.setSensor(rs.getString(6));
					cs.setBattery(rs.getString(7));
					cs.setResolution(rs.getString(8));
					cs.setResolutionavailble(rs.getString(9));
					cs.setFps(rs.getString(10));
					cs.setFileformat(rs.getString(11));
					cs.setSlowmotion(rs.getString(12));
					cs.setMemorycardtype(rs.getString(13));
					cs.setPrice(rs.getString(14));
				}
				request.setAttribute("data", cs);
				request.setAttribute("category", category);
				RequestDispatcher rd = request.getRequestDispatcher("camsingle.jsp");
				rd.forward(request, response);
			}
			else if(category.equalsIgnoreCase("Television"))
			{
				System.out.println("------------------->"+category+"---"+companyname+"---"+model+"<-------------------");
				
				request.setAttribute("category", category);
				request.setAttribute("company", companyname);
				request.setAttribute("model",model);
				RequestDispatcher rd = request.getRequestDispatcher("televisionsingle.jsp");
				rd.forward(request, response);
			}
			else if(category.equalsIgnoreCase("headphones"))
			{
				System.out.println("------------------->"+category+"---"+companyname+"---"+model+"<-------------------");
				request.setAttribute("category", category);
				request.setAttribute("company", companyname);
				request.setAttribute("model",model);
				RequestDispatcher rd = request.getRequestDispatcher("headphonesingle.jsp");
				rd.forward(request, response);
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}
	}

}
