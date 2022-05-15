<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Notices" %>    
<%
if (request.getParameter("noticeId") != null)
{
	System.out.println("noticeId");
	Notices noteObj = new Notices();
	String stsMsg = "";
			
	

	stsMsg = noteObj.updateNotes(request.getParameter("hidItemUpdate"),
	request.getParameter("phone"),
	request.getParameter("address"),
	request.getParameter("note"),
	request.getParameter("zipcode"));
	
	System.out.println("noticeId");
	session.setAttribute("statusMsg", stsMsg); 
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<title>Service Management</title>
</head>
<body>

	<div class="container">
	<br><h1>Service Management</h1><hr>

	<form method="post" action="Notices.jsp">
	
	
	Phone Number:<input name="phone" type="text" value= "" id ="phone" class="form-control"><br>
	District <input name="address" type="text"  value= "" class="form-control"><br>
	Time :<input name="note" type="text"  value= "" class="form-control"><br>
	Zip Code:<input name="zipcode" type="text"  value= ""  class="form-control"><br>
	
	<input name="btnSubmit" type="submit" value="update" class="btn btn-primary"><br>
	<input type ="hidden" id="hidItemIDSave" name="hidItemIDSave" value='"+ noticeId +"'>
	</form>
	</div>
</body>



</html>