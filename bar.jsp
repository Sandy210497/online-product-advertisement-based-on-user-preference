<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bar.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
	
	
		.barlittle {
    background-color: #2187e7;
    background-image: -moz-linear-gradient(45deg, #2187e7 25%, #a0eaff);
    background-image: -webkit-linear-gradient(45deg, #2187e7 25%, #a0eaff);
    border-left: 1px solid #111;
    border-top: 1px solid #111;
    border-right: 1px solid #333;
    border-bottom: 1px solid #333;
    width: 10px;
    height: 10px;
    float: left;
    margin-left: 5px;
    opacity: 0.1;
    -moz-transform: scale(0.7);
    -webkit-transform: scale(0.7);
    -moz-animation: move 1s infinite linear;
    -webkit-animation: move 1s infinite linear;
}

#block_1 {
    -moz-animation-delay: .4s;
    -webkit-animation-delay: .4s;
}

#block_2 {
    -moz-animation-delay: .3s;
    -webkit-animation-delay: .3s;
}

#block_3 {
    -moz-animation-delay: .2s;
    -webkit-animation-delay: .2s;
}

#block_4 {
    -moz-animation-delay: .3s;
    -webkit-animation-delay: .3s;
}

#block_5 {
    -moz-animation-delay: .4s;
    -webkit-animation-delay: .4s;
}

@-moz-keyframes move {
    0% {
        -moz-transform: scale(1.2);
        opacity: 1;
    }

    100% {
        -moz-transform: scale(0.7);
        opacity: 0.1;
    };
}

@-webkit-keyframes move {
    0% {
        -webkit-transform: scale(1.2);
        opacity: 1;
    }

    100% {
        -webkit-transform: scale(0.7);
        opacity: 0.1;
    };
}
	</style>
  </head>
  
  <body>
   <div id="block_1" class="barlittle"></div>
<div id="block_2" class="barlittle"></div>
<div id="block_3" class="barlittle"></div>
<div id="block_4" class="barlittle"></div>
<div id="block_5" class="barlittle"></div>
  </body>
</html>
