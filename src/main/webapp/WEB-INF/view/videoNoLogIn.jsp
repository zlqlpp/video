<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<style type="text/css">
.v{
 
    margin-left: 9%;
    margin-top: 10%;
 
}
</style>
    <head>
        <meta charset="UTF-8" />
        <title></title>
        <link href="favicon.ico" rel="icon" type="image/x-icon" />
        <link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
        <link href="tomcat.css" rel="stylesheet" type="text/css" />
       
    </head>

    <body>
	<div class="table-responsive">
	 	<video class="v" width="800" height="600" controls autoplay="autoplay">
		  	<source src="/video/v/${video}" type="video/mp4">
			Your browser does not support the video tag.
		</video>
	</div>
	<hr>
	<a href="mainNoLogIn" class="v" style="font-size:40px">返回首页</a>
    </body>

</html>