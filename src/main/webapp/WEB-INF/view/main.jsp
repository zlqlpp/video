<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<style type="text/css">
.vn{
	    margin-left: 25%;
    font-family: serif;
    font-size: 50px;
}
.t{
    font-family: serif;
    font-size: 40;
    margin-top: 8%;
    text-align: right;
}
</style>
<head>
	<meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
	 
		function video(v){
			var f = $(v).attr('value');
			if(''!=$('#u').val()){
				if(confirm("当前总金币100，此次观看将使用2金币")){
					 　　$('#video').val(f);
						$('#sf').submit();
					}
			}else{
				$('#sf').attr("action","login");
				$('#sf').submit();
			}
			
		}
	</script>
</head>
<body>
<form id="sf" action="goVideo" method="post">
<input id="video" name="video" type="hidden" ></input>
</form>

<div>
<form role="form" class="t">
  <div class="form-group" style="margin-right: 10%;">
    <label for="name">用户：${userName }</label>
    <label for="name">金币:${gold }</label> 
    <input id="u" type="hidden" value="${userName }"/>
  </div>
 </form>
</div>
<hr>
			<c:forEach items="${files}" var="f" varStatus="id">
				
					<div class="vn"><a onclick="video(this);" value="${f}">${f}</a> </div>
				
			</c:forEach>
  	

</body>
</html>