<%@ page language="java" import="java.util.*" import="java.io.*" pageEncoding="ISO-8859-1"%>
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
<link rel="stylesheet" href="css/awesome-bootstrap-checkbox.css" type="text/css">
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
		<style>
.circle {
	background-color: rgba(0, 0, 0, 0);
	border: 5px solid rgba(0, 183, 229, 0.9);
	opacity: .9;
	border-right: 5px solid rgba(0, 0, 0, 0);
	border-left: 5px solid rgba(0, 0, 0, 0);
	border-radius: 50px;
	box-shadow: 0 0 35px #2187e7;
	width: 50px;
	height: 50px;
	margin: 0 auto;
	-moz-animation: spinPulse 1s infinite ease-in-out;
	-webkit-animation: spinPulse 1s infinite linear;
}

.circle1 {
	background-color: rgba(0, 0, 0, 0);
	border: 5px solid rgba(0, 183, 229, 0.9);
	opacity: .9;
	border-left: 5px solid rgba(0, 0, 0, 0);
	border-right: 5px solid rgba(0, 0, 0, 0);
	border-radius: 50px;
	box-shadow: 0 0 15px #2187e7;
	width: 30px;
	height: 30px;
	margin: 0 auto;
	position: relative;
	top: -50px;
	-moz-animation: spinoffPulse 1s infinite linear;
	-webkit-animation: spinoffPulse 1s infinite linear;
}

@
-moz-keyframes spinPulse { 0% {
	-moz-transform: rotate(160deg);
	opacity: 0;
	box-shadow: 0 0 1px #2187e7;
}

50%
{
-moz-transform
:
 
rotate
(145deg);

        
opacity
:
 
1;
}
100%
{
-moz-transform
:
 
rotate
(-320deg);

        
opacity
:
 
0;
}
;
}
@
-moz-keyframes spinoffPulse { 0% {
	-moz-transform: rotate(0deg);
}

100%
{
-moz-transform
:
 
rotate
(360deg);

    
}
;
}
@
-webkit-keyframes spinPulse { 0% {
	-webkit-transform: rotate(160deg);
	opacity: 0;
	box-shadow: 0 0 1px #2187e7;
}

50%
{
-webkit-transform
:
 
rotate
(145deg);

        
opacity
:
 
1;
}
100%
{
-webkit-transform
:
 
rotate
(-320deg);

        
opacity
:
 
0;
}
;
}
@
-webkit-keyframes spinoffPulse { 0% {
	-webkit-transform: rotate(0deg);
}
100%
{
-webkit-transform
:
 
rotate
(360deg);

    
}
;
}
</style>
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
		<div class="agile_featured_movies" align="left" style="padding-left: 40px; margin-left: 30px;">
						<!--/comedy-movies-->
		<h3 class="agile_w3_title hor-t">PreProcess</h3>
		
					 
		<form action="Preprocess" method="get">
		<table>
		<%
		System.out.println("------------Dispatcher called preprocess-----------");
			File f = new File(new File("").getAbsolutePath()+"/webapps/"+request.getContextPath()+"/videos/");
			System.out.println("<---path---->"+f.getAbsolutePath());
			File[] filearray = f.listFiles();
			for(File ff : filearray)
			{
				String folder = ff.getName();
				%>
					
					<tr>
						<th>
							<h4><%=folder %></h4>
						</th>
					</tr>	
							
				<%
				File[] fff = ff.listFiles();
				
				for(File farr : fff)
				{
					String filename = farr.getName();
					%>
						<br/>	
							<tr>
							<td>
								<input type="checkbox" name="check" value="<%=folder+"$"%><%=filename %>"/>
							</td>
							<td>
								<%=filename %>
							</td>
						</tr>
							
					
					<%
				}
			}
		
		%>
		<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>
			</td>
			<td>
				<input type="submit" data-toggle="modal" data-target="#myModal4" value="process">
			</td>
		</tr>
		</table>
		</form>

		
			</div>
		</div>
		
			
									
			<div class="clearfix"></div>
		

		</div>
		
				<div class="modal fade" id="myModal4" tabindex="-1" role="dialog" >

							<div class="modal-dialog">
							<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
<%--										<div style="margin-top: 250px; margin-left: 450px; width: 400px; height: 80px; padding: 40px; background-color: #D3D3D3; border-radius: 10px;" align="center">--%>
  				<div class="circle"></div>
				<div class="circle1"></div>
<%-- 		 </div>--%>
										
									</div>
								</div>
							</div>
						</div>
		
		</body>
	

</html>

