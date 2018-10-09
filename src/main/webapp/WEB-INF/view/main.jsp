<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		function video(v){
			var f = $(v).attr('value');
			 
			if(confirm("当前总金币100，此次观看将使用2金币")){
			 　　$('#video').val(f);
				$('#sf').submit();
			}
		}
	</script>
</head>
<body>
<form id="sf" action="goVideo" methon="post">
<input id="video" name="video" type="hidden" ></input>
</form>

<div>
<form role="form">
  <div class="form-group">
    <label for="name">当前用户：${userName }</label>
    <label for="name">金币总数:${gold }</label> 
  </div>
 </form>
</div>
<div class="table-responsive">
	<table class="table">
		<tbody>
			<c:forEach items="${files}" var="f" varStatus="id">
				<tr>
					<td><a onclick="video(this);" value="${f}">${f}</a> </td>
				</tr>
			</c:forEach>
			
		</tbody>
</table>
</div>  	

</body>
</html>