<%@ page language="java" import="java.util.*" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<title>VLC Plugin test page</TITLE>
<style>

.inputTrackerInput 
{
        height:20;
        width:30;
        font-family : Arial, Helvetica, sans-serif;
        font-size : 12px;
}
body {background: grey;}
    
#bind
{
	 width:640;
     height:480;
	 position: relative;
}
#player
{
	width: 100%;
  	height: 100%;
  	position: absolute;
  	top: 0;
  	left: 0;
}
#ads
{
  position: relative;
  float:right;
  margin-top: 25%;
  width: 30%;
  height: 60%;
  z-index: 10;
}
</style>
</style>

<script language="JavaScript"><!--


function init()
{
    if( navigator.appName.indexOf("Microsoft Internet")==-1 )
    {
        onVLCPluginReady()
    }
    else if( document.readyState == 'complete' )
    {
        onVLCPluginReady();
    }
    else
    {
        /* Explorer loads plugins asynchronously */
        document.onreadystatechange=function()
        {
            if( document.readyState == 'complete' )
            {
                onVLCPluginReady();
            }
        }
    }
     var x = document.getElementById("ads");
    x.style.display = 'none';
}
function getVLC(name)
{
    if (window.document[name])
    {
        return window.document[name];
    }
    if (navigator.appName.indexOf("Microsoft Internet")==-1)
    {
        if (document.embeds && document.embeds[name])
            return document.embeds[name];
    }
    else // if (navigator.appName.indexOf("Microsoft Internet")!=-1)
    {
        return document.getElementById(name);
    }
}
function registerVLCEvent(event, handler)
{
    var vlc = getVLC("vlc");
    if (vlc) {
        if (vlc.attachEvent) {
            // Microsoft
            vlc.attachEvent (event, handler);
        } else if (vlc.addEventListener) {
            // Mozilla: DOM level 2
            vlc.addEventListener (event, handler, true);
        } else {
            // DOM level 0
            eval("vlc.on" + event + " = handler");
        }
    }
}
function unregisterVLCEvent(event, handler)
{
    var vlc = getVLC("vlc");
    if (vlc) {
        if (vlc.detachEvent) {
            // Microsoft
            vlc.detachEvent (event, handler);
        } else if (vlc.removeEventListener) {
            // Mozilla: DOM level 2
            vlc.removeEventListener (event, handler, true);
        } else {
            // DOM level 0
            eval("vlc.on" + event + " = null");
        }
    }
}
// JS VLC API callbacks
function handleMediaPlayerMediaChanged()
{
    document.getElementById("info").innerHTML = "Media Changed";
}
function handle_MediaPlayerNothingSpecial()
{
    document.getElementById("state").innerHTML = "Idle...";
}
function handle_MediaPlayerOpening()
{
    onOpen();
}
function handle_MediaPlayerBuffering(val)
{
    document.getElementById("info").innerHTML = val + "%";
}
function handle_MediaPlayerPlaying()
{
	
    onPlay();
}
function handle_MediaPlayerPaused()
{
	
    onPause();
}
function handle_MediaPlayerStopped()
{
	
    onStop();
}
function handle_MediaPlayerForward()
{
    document.getElementById("state").innerHTML = "Forward...";
}
function handle_MediaPlayerBackward()
{
    document.getElementById("state").innerHTML = "Backward...";
}
function handle_MediaPlayerEndReached()
{
	
    onEnd();
}
function handle_MediaPlayerEncounteredError()
{
    onError();
}
function handle_MediaPlayerTimeChanged(time)
{ 
    var vlc = getVLC("vlc");
    var info = document.getElementById("info");
    var frame = document.getElementById("frame");
    var frameCou = vlc.input.fps;
    
    if( vlc )
    {
        var mediaLen = vlc.input.length;
        if( mediaLen > 0 )
        {
            // seekable media
            info.innerHTML = formatTime(time)+"/"+formatTime(mediaLen);
            var fpcurr = Math.floor((time/1000)*frameCou);
            frame.innerHTML = fpcurr;
            ads(fpcurr);
        }
    }
}
function handle_MediaPlayerPositionChanged(val)
{
    // set javascript slider to correct position
}
function handle_MediaPlayerSeekableChanged(val)
{
    setSeekable(val);
}
function handle_MediaPlayerPausableChanged(val)
{
    setPauseable(val);
}
function handle_MediaPlayerTitleChanged(val)
{
    //setTitle(val);
    document.getElementById("info").innerHTML = "Title: " + val;
}
function handle_MediaPlayerLengthChanged(val)
{
    //setMediaLength(val);
}
// VLC Plugin
function onVLCPluginReady()
{
    registerVLCEvent("MediaPlayerMediaChanged", handleMediaPlayerMediaChanged);
    registerVLCEvent("MediaPlayerNothingSpecial", handle_MediaPlayerNothingSpecial);
    registerVLCEvent("MediaPlayerOpening", handle_MediaPlayerOpening);
    registerVLCEvent("MediaPlayerBuffering", handle_MediaPlayerBuffering);
    registerVLCEvent("MediaPlayerPlaying", handle_MediaPlayerPlaying);
    registerVLCEvent("MediaPlayerPaused", handle_MediaPlayerPaused);
    registerVLCEvent("MediaPlayerStopped", handle_MediaPlayerStopped);
    registerVLCEvent("MediaPlayerForward", handle_MediaPlayerForward);
    registerVLCEvent("MediaPlayerBackward", handle_MediaPlayerBackward);
    registerVLCEvent("MediaPlayerEndReached", handle_MediaPlayerEndReached);
    registerVLCEvent("MediaPlayerEncounteredError", handle_MediaPlayerEncounteredError);
    registerVLCEvent("MediaPlayerTimeChanged", handle_MediaPlayerTimeChanged);
    registerVLCEvent("MediaPlayerPositionChanged", handle_MediaPlayerPositionChanged);
    registerVLCEvent("MediaPlayerSeekableChanged", handle_MediaPlayerSeekableChanged);
    registerVLCEvent("MediaPlayerPausableChanged", handle_MediaPlayerPausableChanged);
    registerVLCEvent("MediaPlayerTitleChanged", handle_MediaPlayerTitleChanged);
    registerVLCEvent("MediaPlayerLengthChanged", handle_MediaPlayerLengthChanged);
}
function close()
{
    unregisterVLCEvent("MediaPlayerMediaChanged", handleMediaPlayerMediaChanged);
    unregisterVLCEvent("MediaPlayerNothingSpecial", handle_MediaPlayerNothingSpecial);
    unregisterVLCEvent("MediaPlayerOpening", handle_MediaPlayerOpening);
    unregisterVLCEvent("MediaPlayerBuffering", handle_MediaPlayerBuffering);
    unregisterVLCEvent("MediaPlayerPlaying", handle_MediaPlayerPlaying);
    unregisterVLCEvent("MediaPlayerPaused", handle_MediaPlayerPaused);
    unregisterVLCEvent("MediaPlayerStopped", handle_MediaPlayerStopped);
    unregisterVLCEvent("MediaPlayerForward", handle_MediaPlayerForward);
    unregisterVLCEvent("MediaPlayerBackward", handle_MediaPlayerBackward);
    unregisterVLCEvent("MediaPlayerEndReached", handle_MediaPlayerEndReached);
    unregisterVLCEvent("MediaPlayerEncounteredError", handle_MediaPlayerEncounteredError);
    unregisterVLCEvent("MediaPlayerTimeChanged", handle_MediaPlayerTimeChanged);
    unregisterVLCEvent("MediaPlayerPositionChanged", handle_MediaPlayerPositionChanged);
    unregisterVLCEvent("MediaPlayerSeekableChanged", handle_MediaPlayerSeekableChanged);
    unregisterVLCEvent("MediaPlayerPausableChanged", handle_MediaPlayerPausableChanged);
    unregisterVLCEvent("MediaPlayerTitleChanged", handle_MediaPlayerTitleChanged);
    unregisterVLCEvent("MediaPlayerLengthChanged", handle_MediaPlayerLengthChanged);
}
//--></script>

<body onLoad="init();" onClose="close();">
<%--<div style="position: fixed; left: 100px; width: 100px; height: 100px; z-index: 1000; background-color: red;"></div>--%>

<%
String curvideo="";
	Vector starttime = new Vector();
	Vector endtime = new Vector();
	Vector imgurl = new Vector();
	int adscount=0;
	HttpSession hs = request.getSession(false);
	HashMap<String,String> prefproduct = (HashMap<String,String>)hs.getAttribute("userprefmap");
	Vector prefproductkey = new Vector();
	HashMap<String,String> framecal = new HashMap<String,String>();
	String mobileseq="",cameraseq="";
	String url = request.getParameter("path");
	String videoname = request.getParameter("videoname");
	if (videoname.indexOf(".") > 0)
	{
		curvideo = videoname.substring(0, videoname.lastIndexOf("."));
	}	
	Connection con = (Connection)getServletContext().getAttribute("DBConnection");
	PreparedStatement ps = con.prepareStatement("select * from keyframe where videoname=?");
	ps.setString(1, curvideo);
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		 mobileseq = rs.getString("mobile");
		 cameraseq = rs.getString("camera");	 
	}
	if(mobileseq!=null)
	{
		String[] mobarr = mobileseq.split("&");
		if(mobarr.length == 0)
		{
			framecal.put(mobileseq,"Mobile");
			prefproductkey.add(mobileseq);	
		}
		else
		{
			for(int i=0;i<mobarr.length;i++)
			{
				
				framecal.put(mobarr[i].toString(),"Mobile");
				prefproductkey.add(mobarr[i].toString());
			}
		}
		
	}
	if(cameraseq!=null)
	{
		String[] camarr = cameraseq.split("&");
		if(camarr.length == 0)
		{
			framecal.put(cameraseq,"Camera");
			prefproductkey.add(cameraseq);
		}
		else
		{
			for(int j=0;j<camarr.length;j++)
			{
				framecal.put(camarr[j].toString(),"Camera");
				prefproductkey.add(cameraseq);
			}
		}
	}
	System.out.println("___Prefpr__"+prefproductkey);
	for(int j=0;j<prefproductkey.size();j++)
	{
		String temp = prefproductkey.get(j).toString();
		StringTokenizer str = new StringTokenizer(temp,"*");
		starttime.add(str.nextToken());
		endtime.add(str.nextToken());
		imgurl.add(prefproduct.get(framecal.get(prefproductkey.get(j))));
	}
	
	
	System.out.println(imgurl+"----framecal-->>>>>>>>>>>>>>>"+framecal);

	Vector prefvect = (Vector)hs.getAttribute("prefvect");
	
	
	
%>





<div style="width: 800; height: 200; top:200px; position: absolute; border: 1pt solid black; background: blue; border-radius: 5px; display: none;" id="overlay"></div>

<!-- This div should appear on top of the player -->
<%--<div style="position: fixed; left: 100px; width: 100px; height: 100px; z-index: 1000; background-color: red;"></div>--%>
<table style="margin: 50px 50px 10px 300px;">
<tr><td align="center" colspan="2">
<div id="bind">
<div id=player>
<!--
Insert VideoLAN.VLCPlugin.2
-->
<object classid="clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921"
        width="640"
        height="480"
        
        windowless="false"
        events="True">
<param name="MRL" value="" />
<param name="ShowDisplay" value="True" />
<param name="AutoLoop" value="False" />
<param name="AutoPlay" value="False" />
<param name="Volume" value="50" />
<param name="toolbar" value="true" />
<param name="StartTime" value="0" />

<EMBED pluginspage="http://www.videolan.org"
	   id="vlc"
       type="application/x-vlc-plugin"
       version="VideoLAN.VLCPlugin.2"
       width="640"
       height="480"
       toolbar="true"
       loop="true"
       text="Waiting for video"
       windowless="false"
       name="vlc">
</EMBED>
</object>
</div>
<div id="ads" style="background-color:white;">
	
		<form method="get" target="block" action="test">
	    	<img src="" style="background-size:cover;" id="image" height="85%" width="100%" />
	    	<input type="hidden" id="category" value=""/>
	    	<input type="hidden" id="company" value=""/>
	    	<input type="hidden" id="model" value=""/>
	    	<input type="submit" value="buy"></label>			
    	</form>

</div>
</div>

</td></tr>
<tr><td colspan="2">
MRL:

<input type=hidden size="90" id="targetTextField" value="<%=url%>">
<input type=submit value="play" onClick="doGo(document.getElementById('targetTextField').value);">
<%--<input type=submit value="Add" onClick="doAdd(document.getElementById('targetTextField').value);">--%>
</td></tr>

<tr>
<td>
<input type=button id="PlayOrPause" value=" Play " onClick='doPlayOrPause();'>
<input type=button value="Stop" onClick='doStop();'>
<input type=button value="Fast" onClick='doPlayFaster();'>
<input type=button value="Slow" onClick='doPlaySlower();'>

</td>
<td width="15%">
<div id="info" style="text-align:center">-:--:--/-:--:--</div>
<div id="state" style="text-align:center">Stopped...</div>

</td>
</tr>
<tr>
    <td> 
    </td>
    <td>
    	<div id="frame" style="text-align:center"></div>
    	<div id="advs" style="text-align:center"></div>
    </td>
    
</tr>
<tr>
	<td><div id="imagecal"></div></td>
	
	
</tr>
<tr>
	<td></td>
</tr>
	
</table>
<SCRIPT language="javascript">

var rate = 0;
var prevState = 0;
var telxState = false;
var canPause = true;
var canSeek = true;
var cc = 0;
function setPauseable(val)
{
    canPause = val;
}
function setSeekable(val)
{
    canSeek = val;
}
function doSetSlider()
{
    var vlc = getVLC("vlc");
    // set slider to new position
    if( vlc )
        vlc.input.time = (vlc.input.length/2);
}
function doGetPosition()
{
    var vlc = getVLC("vlc");
    // set slider to new position
    if (vlc)
        alert( "position is " + vlc.input.time);
}
function doReverse(rate)
{
    var vlc = getVLC("vlc");
    if( vlc )
        vlc.input.rate = -1.0 * vlc.input.rate;
}
function doAudioChannel(value)
{
    var vlc = getVLC("vlc");
    if( vlc )
        vlc.audio.channel = parseInt(value);
}
function doAudioTrack(value)
{
    var vlc = getVLC("vlc");
    if( vlc )
    {
        vlc.audio.track = vlc.audio.track + value;
        document.getElementById("trackTextField").innerHTML = vlc.audio.track;
    }
}
function doAspectRatio(value)
{
    var vlc = getVLC("vlc");
    if( vlc )
        vlc.video.aspectRatio = value;
}
function doSubtitle(value)
{
    var vlc = getVLC("vlc");
    if( vlc )
    {
        vlc.subtitle.track = vlc.subtitle.track + value;
        document.getElementById("spuTextField").innerHTML = vlc.subtitle.track;
    }
}
function doTelxPage(value)
{
    var vlc = getVLC("vlc");
    if( vlc )
        vlc.video.teletext = parseInt(value);
}
function doToggleTeletext()
{
    var vlc = getVLC("vlc");
    if( vlc )
    {
        vlc.video.toggleTeletext();
        if (telxState)
        {
            document.getElementById("telx").innerHTML = "Teletext on";
            telxState = true;
        }
        else
        {
            document.getElementById("telx").innerHTML = "Teletext off";
            telxState = false;
        }
    }
}
function doToggle3dTransform()
{
    var vlc = getVLC("vlc");
    var transform = "rotate3d(1,2,1, 45deg)";
    if( vlc )
    {
        if (!vlc.style.transform)
        {
            vlc.style.transform = transform;
            vlc.style.webkitTransform = transform;
        }
        else
        {
            vlc.style.transform = '';
            vlc.style.webkitTransform = '';
        }
    }
}
function doToggleOpacity()
{
    var vlc = getVLC("vlc");
    var opacity = "0.5";
    if( vlc )
    {
        if (!vlc.style.opacity)
        {
            vlc.style.opacity = opacity;
        }
        else
        {
            vlc.style.opacity = '';
        }
    }
}
function doToggleRound()
{
    var vlc = getVLC("vlc");
    var corner = "80px";
    if( vlc )
    {
        if (!vlc.style.borderRadius)
        {
            vlc.style.borderRadius = corner;
        }
        else
        {
            vlc.style.borderRadius = '';
        }
    }
}
function doItemCount()
{
    var vlc = getVLC("vlc");
    if( vlc )
    {
        var count = vlc.playlist.items.count;
        document.getElementById("itemCount").value = " Items " + count + " ";
    }
}
function doRemoveItem(item)
{
    var vlc = getVLC("vlc");
    if( vlc )
        vlc.playlist.items.remove(item);
}
function doPlaylistClearAll()
{
    var vlc = getVLC("vlc");
    if( vlc )
    {
        vlc.playlist.items.clear();
        while( vlc.playlist.items.count > 0)
        {
            // wait till playlist empties.
        }
        doItemCount();
    }
}
function updateVolume(deltaVol)
{
    var vlc = getVLC("vlc");
    if( vlc )
    {
        vlc.audio.volume += deltaVol;
        document.getElementById("volumeTextField").innerHTML = vlc.audio.volume+"%";
    }
}
function formatTime(timeVal)
{
    if( typeof timeVal != 'number' )
        return "-:--:--";
    var timeHour = Math.round(timeVal / 1000);
    var timeSec = timeHour % 60;
    if( timeSec < 10 )
        timeSec = '0'+timeSec;
    timeHour = (timeHour - timeSec)/60;
    var timeMin = timeHour % 60;
    if( timeMin < 10 )
        timeMin = '0'+timeMin;
    timeHour = (timeHour - timeMin)/60;
    if( timeHour > 0 )
        return timeHour+":"+timeMin+":"+timeSec;
    else
        return timeMin+":"+timeSec;
}
// Old method of querying current state
// function doState() - depreceated
function doState()
{
    var vlc = getVLC("vlc");
    var newState = 0;
    if( vlc )
        newState = vlc.input.state;
    if( newState == 0 )
    {
        // current media has stopped
        onEnd();
    }
    else if( newState == 1 )
    {
        // current media is openning/connecting
        onOpen();
    }
    else if( newState == 2 )
    {
        // current media is buffering data
        onBuffer();
    }
    else if( newState == 3 )
    {
        // current media is now playing
        onPlay();
    }
    else if( newState == 4 )
    {
        // current media is now paused
        onPause();
    }
    else if( newState == 5 )
    {
        // current media has stopped
        onStop();
    }
    else if( newState == 6 )
    {
        // current media has ended
        onEnd();
    }
    else if( newState == 7 )
    {
        // current media encountered error
        onError();
    }
}
/* actions */
function doGo(targetURL)
{
    var vlc = getVLC("vlc");
    if( vlc )
    {
        vlc.playlist.items.clear();
        while( vlc.playlist.items.count > 0 )
        {
            // clear() may return before the playlist has actually been cleared
            // just wait for it to finish its job
        }
        var options = [":rtsp-tcp"];
        var itemId = vlc.playlist.add(targetURL,"",options);
        options = [];
        if( itemId != -1 )
        {
            // play MRL
            vlc.playlist.playItem(itemId);
        }
        else
        {
            alert("cannot play at the moment !");
        }
        doItemCount();
    }
}
function doAdd(targetURL)
{
    var vlc = getVLC("vlc");
    var options = [":vout-filter=deinterlace", ":deinterlace-mode=linear"];
    if( vlc )
    {
        vlc.playlist.add(targetURL, "", options);
        options = [];
        doItemCount();
    }
}
function doPlayOrPause()
{
    var vlc = getVLC("vlc");
    if( vlc )
    {
        vlc.playlist.togglePause();
    }
}
function doStop()
{
    var vlc = getVLC("vlc");
    if( vlc )
        vlc.playlist.stop();
}
function doPlaySlower()
{
    var vlc = getVLC("vlc");
    if( vlc )
        vlc.input.rate = vlc.input.rate / 2;
}
function doPlayFaster()
{
    var vlc = getVLC("vlc");
    if( vlc )
        vlc.input.rate = vlc.input.rate * 2;
}

function doLogoOption(option, value)
{
    var vlc = getVLC("vlc");
    if( vlc )
    {
        if (option == 1)
            vlc.video.logo.file(value);
        if (option == 2)
            vlc.video.logo.position = value;
        val = parseInt(value);
        if (option == 3)
            vlc.video.logo.opacity = val;
        if (option == 4)
            vlc.video.logo.repeat = val;
        if (option == 5)
            vlc.video.logo.delay = val;
        if (option == 6)
            vlc.video.logo.x = val;
        if (option == 7)
            vlc.video.logo.y = val;
    }
}
/* events */
function onOpen()
{
    document.getElementById("state").innerHTML = "Opening...";
    document.getElementById("PlayOrPause").value = "Pause";
}
function onBuffer()
{
    document.getElementById("state").innerHTML = "Buffering...";
    document.getElementById("PlayOrPause").value = "Pause";
}
function onPlay()
{
    document.getElementById("state").innerHTML = "Playing...";
    document.getElementById("PlayOrPause").value = "Pause";
    onPlaying();
}
function onEnd()
{
    document.getElementById("state").innerHTML = "End...";
}
var liveFeedText = ["Live", "((Live))", "(( Live ))", "((  Live  ))"];
var liveFeedRoll = 0;
function onPlaying()
{
        var vlc = getVLC("vlc");
        var info = document.getElementById("info");
        if( vlc )
        {
            var mediaLen = vlc.input.length;
            if( mediaLen > 0 )
            {
                // seekable media
                info.innerHTML = formatTime(vlc.input.time)+"/"+formatTime(mediaLen);
            }
            else
            {
                // non-seekable "live" media
                liveFeedRoll = liveFeedRoll & 3;
                info.innerHTML = liveFeedText[liveFeedRoll++];
            }
        }
}
function onPause()
{
    document.getElementById("state").innerHTML = "Paused...";
    document.getElementById("PlayOrPause").value = " Play ";
}
function onStop()
{
    var vlc = getVLC("vlc");
    document.getElementById("info").innerHTML = "-:--:--/-:--:--";
    document.getElementById("state").innerHTML = "Stopped...";
    document.getElementById("PlayOrPause").value = " Play ";
}
function onError()
{
    var vlc = getVLC("vlc");
    document.getElementById("state").innerHTML = "Error...";
}

function ads(category)
{
	
	var d = document.getElementById("advs");
	var x = document.getElementById("ads");
	var image = document.getElementById("image");
	var imagecal = document.getElementById("imagecal");
	var prourl = document.getElementById("category");
	var start="<%=starttime%>";
    start= start.replace("[", "").replace("]", "");
    start=start.split(", ");
    
    var end="<%=endtime%>";
    
    end= end.replace("[", "").replace("]", "");
    end=end.split(", ");
    
    var imageurl="<%=imgurl%>";
    imageurl= imageurl.replace("[", "").replace("]", "");
    imageurl=imageurl.split(", ");
    
 
 	if(category >= start[cc] && category <= end[cc])
	{
		d.innerHTML = "ads";
		image.src = "<%=basePath%>"+imageurl[cc];		
		prourl.value= "mobile";	
		x.style.display = 'block';	
	}
	else
	{
		d.innerHTML = "";
		prourl.value= "";
		x.style.display = 'none';
	}
	
	if((category+1)>end[cc])
	{
		cc=cc+1;	
	}
   	
	
}

function hhh()
{
	
		var model =document.getElementById("category").value; 
		
		var url = "http://localhost:9999/eCommerce/test?user="+model;
		
		if(window.XMLHttpRequest)
		{
			request = new XMLHttpRequest();
		}
		else if(window.ActiveXObject)
		{
			request = new ActiveXObject("MicroSoft.XMLHTTP");
		}
		try
		{  
			alert("url "+url);
			request.onreadystatechange=getInfo;  
			request.open("GET",url,true);  
			request.send();  
		}
		catch(e)
		{
			alert("Unable to connect to server");
		}  
		function getInfo()
		{
			if(request.readyState == 4)
			{
				var val = request.responseText;
		
			}
		}
}

//-->
</SCRIPT>
</body>
</HTML>