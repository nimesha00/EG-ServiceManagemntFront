<%@page import="model.Notices" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Service Management EG</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Component/jquery-3.6.0.min.js"></script>
<script src="Component/noticemain.js"></script>
</head>
<body>

	<div class="container">
		<br><center><h1>Service Management EG</h1></center>
	
		<form id="formNotice" name="formNotice" method="post" action="Notices.jsp">
				Phone Number : <input id="phone" name="phone" type="text" class="form-control"><br>
				District :<input id="address" name="address" type="text" class="form-control"><br>
				Time : <input id="note" name="note" type="text" class="form-control"><br>
				Zip Code : <input id="zipcode" name="zipcode" type="text" class="form-control"><br>
				
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
				<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
		</form>
	
	<br>
	<hr>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
	<hr>
	<br>
	
	<div id="divItemsGrid">
			 <%
			 		Notices noteObj = new Notices();
			 		out.print(noteObj.readNotes());
		     %>
	</div>
	
	</div>
</body>
</html>