<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!--
author: W3layouts
author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<title>Movies Pro an Entertainment Category Flat Bootstrap Responsive Website Template | Genre :: w3layouts</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Movies Pro Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- pop-up -->
<link href="css/popuo-box.css" rel="stylesheet" type="text/css" media="all" />
<!-- //pop-up -->
<link href="css/easy-responsive-tabs.css" rel='stylesheet' type='text/css'/>
<link rel="stylesheet" type="text/css" href="css/zoomslider.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/prByE.css" />
<link href="css/font-awesome.css" rel="stylesheet"> 
<script type="text/javascript" src="js/modernizr-2.6.2.min.js"></script>
<script type="text/javascript" src="js/prByE.js"></script>
<!--/web-fonts-->
<link href='//fonts.googleapis.com/css?family=Tangerine:400,700' rel='stylesheet' type='text/css'>
<link href="//fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900" rel="stylesheet">
<link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<!--//web-fonts-->
</head>
<body>
<!--/main-header-->
  <!--/banner-section-->
	<div id="demo-1" class="banner-inner">
	 <div class="banner-inner-dott">
		<!--/header-w3l-->
			   <div class="header-w3-agileits" id="home">
			     <div class="inner-header-agile part2">	
				<nav class="navbar navbar-default">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<h1><a  href="index.jsp"><span>M</span>ovies <span>P</span>ro</a></h1>
					</div>
					<!-- navbar-header -->
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
							<li><a href="admin.jsp">Home</a></li>
<%--							<li><a href="extraction.jsp">Feature Extraction</a></li>--%>
							<li><a href="preprocess.jsp">Frame</a></li>
							<li><a href="upload.jsp">Upload</a></li>
							<li><a href="Logout">Signout</a></li>
						</ul>


					</div>
					<div class="clearfix"> </div>	
				</nav>
					<div class="w3ls_search">
									<div class="cd-main-header">
										<ul class="cd-header-buttons">
											<li><a class="cd-search-trigger" href="#cd-search"> <span></span></a></li>
										</ul> <!-- cd-header-buttons -->
									</div>
										<div id="cd-search" class="cd-search">
											<form action="#" method="post">
												<input name="Search" type="search" placeholder="Search...">
											</form>
										</div>
								</div>
	
			</div> 

			   </div>
			   </div>
			   </div>
		<!--//header-w3l-->

		<div class="w3_content_agilleinfo_inner">
		<div class="agile_featured_movies">
						<!--/comedy-movies-->
		<h3 class="agile_w3_title hor-t">Video<span>Upload</span> </h3>
		<h2><center>Choose Video</center></h2>
					 
		<%
			String status=""; 
			if(request.getAttribute("message")!=null)
			{
				status = (String)request.getAttribute("message");	
			}
		
		%>
		

	<div class="upload_form_cont" align="left" style="margin-left: 100px; margin-right: 100px; padding-left: 60px; " >
              <form id="upload_form" enctype="multipart/form-data" method="post" action="VideoUpload">
              <div>
               	<label for="image_file"><h4>Category </h4></label> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<%--              	<input type="text" name="category" requried="true"/>--%>
              	<select name="category">
              		<option value="COMEDY">Comedy</option>
              		<option value="HORROR">Horror</option>
              		<option value="DOCUMENTARY">Documentary</option>
              		<option value="EDUCATION">Education</option>
              		
              	</select>
              </div>
                  <div >
                     
                      
                      <input type="file" name="image_file" id="image_file" onchange="fileSelected();" />
                  </div>
                  <div>
                      <input type="button" value="Upload" onclick="startUploading()" />
                     
                  </div>
                  <div id="fileinfo">
                      <div id="filename"></div>
                      <div id="filesize"></div>
                      <div id="filetype"></div>
                      <div id="filedim"></div>
                  </div>
                  <div id="error">You should select valid image files only!</div>
                  <div id="error2">An error occurred while uploading the file</div>
                  <div id="abort">The upload has been canceled by the user or the browser dropped the connection</div>
                  <div id="warnsize">Your file is very big. We can't accept it. Please select more small file</div>
				<h4 align="left">Upload Status</h4>
                  <div id="progress_info" align="right">
                 
                      <div id="progress"></div>
                      <div id="progress_percent">&nbsp;</div>
                      <div class="clear_both"></div>
                      <div>
                          <div id="speed">&nbsp;</div>
                          <div id="remaining">&nbsp;</div>
                          <div id="b_transfered">&nbsp;</div>
                          <div class="clear_both"></div>
                      </div>
                      <div id="upload_response">status</div>
                  </div>
              </form>

            		<img id="preview" />
       			 </div>
		
			</div>
		</div>
		
			
									
			<div class="clearfix"></div>
		</div>
		</div>
		</div>


		</div>
		
		</body>
	

</html>

