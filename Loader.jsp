<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Loader.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style>
		.circle {
    background-color: rgba(0,0,0,0);
    border: 5px solid rgba(0,183,229,0.9);
    opacity: .9;
    border-right: 5px solid rgba(0,0,0,0);
    border-left: 5px solid rgba(0,0,0,0);
    border-radius: 50px;
    box-shadow: 0 0 35px #2187e7;
    width: 50px;
    height: 50px;
    margin: 0 auto;
    -moz-animation: spinPulse 1s infinite ease-in-out;
    -webkit-animation: spinPulse 1s infinite linear;
}

.circle1 {
    background-color: rgba(0,0,0,0);
    border: 5px solid rgba(0,183,229,0.9);
    opacity: .9;
    border-left: 5px solid rgba(0,0,0,0);
    border-right: 5px solid rgba(0,0,0,0);
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

@-moz-keyframes spinPulse {
    0% {
        -moz-transform: rotate(160deg);
        opacity: 0;
        box-shadow: 0 0 1px #2187e7;
    }

    50% {
        -moz-transform: rotate(145deg);
        opacity: 1;
    }

    100% {
        -moz-transform: rotate(-320deg);
        opacity: 0;
    };
}

@-moz-keyframes spinoffPulse {
    0% {
        -moz-transform: rotate(0deg);
    }

    100% {
        -moz-transform: rotate(360deg);
    };
}

@-webkit-keyframes spinPulse {
    0% {
        -webkit-transform: rotate(160deg);
        opacity: 0;
        box-shadow: 0 0 1px #2187e7;
    }

    50% {
        -webkit-transform: rotate(145deg);
        opacity: 1;
    }

    100% {
        -webkit-transform: rotate(-320deg);
        opacity: 0;
    };
}

@-webkit-keyframes spinoffPulse {
    0% {
        -webkit-transform: rotate(0deg);
    }

    100% {
        -webkit-transform: rotate(360deg);
    };
}
	
	</style>

  </head>
  
  <body >
  
  <div style="margin-top: 250px; margin-left: 450px; width: 400px; height: 80px; padding: 40px; background-color: #D3D3D3; border-radius: 10px;" align="center">
  	<div class="circle"></div>
	<div class="circle1"></div>
  </div>
  <div style="opacity: 0.2;">
  
  
  
  </div>
    
  </body>
</html>
