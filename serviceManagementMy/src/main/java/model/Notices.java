package model;

import java.sql.*;

public class Notices {

	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");//database connection
		 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/notices", "root", "2230166");
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }

	 return con;
	}
	
	//Read function
	public String readNotes()
	{
	 String output = "";
	 
	 try
	 {
	  Connection con = connect();
		  if (con == null)
		  {
			  return "Error while connecting to the database for reading.";
		  }
		 
				// Prepare the HTML table to be displayed
				 output = "<table border='1' class='table table-striped'><tr>"
				 + "<th>Phone Number</th>"
				 + "<th>District</th>"
				 + "<th>Time</th>"
				 + "<th>Zip Code</th>"
				 + "<th>Update</th><th>Delete</th></tr>";
				 
				 String query = "select * from service";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
	           // Iterate through the rows in the result set
	 while (rs.next())
	 {
				 String noticeId = Integer.toString(rs.getInt("noticeId"));
				 String phone = rs.getString("phone");
				 String address = rs.getString("address");
				 String note = rs.getString("note");
				 String zipcode = rs.getString("zipcode");
			 
				 // Add a row into the HTML table
				 output += "<tr><td><input id=\'hidItemIDUpdate\' name=\'hidItemIDUpdate\' type=\'hidden\' value=\'"
							+ noticeId + "'>" + phone + "</td>";
				 output += "<td>" + address + "</td>";
				 output += "<td>" + note + "</td>";
				 output += "<td>" + zipcode + "</td>";
				
		 
		 // Buttons for table
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-success' data-noticeid='" + noticeId +"'></td>"
		 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-noticeid='" + noticeId + "'></td></tr>";
	 }
	 con.close();
	 
	 // Complete the HTML table
	 output += "</table>";
	}
	 
	catch (Exception e)
	{
		output = "Error while reading the notes.";
		System.err.println(e.getMessage());
	}
	 
	return output;
	}
	
	//Insert function notices
	public String insertNotes(String phone, String address, String note, String zipcode)
	{
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 
	 // Prepared statement for notice
	 String query = " insert into service(`noticeId`,`phone`,`address`,`note`, `zipcode`)" + " values (?, ?, ?, ?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // Binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, phone);
	 preparedStmt.setString(3, address);
	 preparedStmt.setString(4, note); 
	 preparedStmt.setString(5, zipcode);
	 
	 //Execute the statement
	 preparedStmt.execute();
	 con.close();
	 
	 String newNotices = readNotes();
	   output = "{\"status\":\"success\", \"data\": \"" + newNotices + "\"}"; 
	 }
	
	catch (Exception e)
	{
	 //output = "Error while inserting the notice";
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the notices...\"}"; 
	 System.err.println(e.getMessage());
	 }
	return output; 
	}
	
	//Update function
	public String updateNotes(String noticeId, String phone, String address, String note, String zipcode) 
	{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating..."; }
			 
			 // Create a prepared statement
			 String query = "update service set phone=?, address=?, note=?, zipcode=? where noticeId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // Binding values
			 preparedStmt.setString(1, phone);
			 preparedStmt.setString(2, address);
			 preparedStmt.setString(3, note);
			 preparedStmt.setString(4, zipcode);
			 preparedStmt.setInt(5, Integer.parseInt(noticeId));
			 
			 // Execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 String newNotices = readNotes();
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 	newNotices + "\"}"; 
     		 //output = "Updated successfully";
			 }
			 
			 catch (Exception e)
			 {
			 //output = "Error while updating the notice.";
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the notice...\"}";
			 System.err.println(e.getMessage());
			 }
			 return output;
	}
	
	//Delete function
	public String deleteNotes(String noticeId)
	{
	 String output = "";
	 try
	  {
	  Connection con = connect();
	  if (con == null)
	  {
	  return "Error while connecting to the database for deleting....";
	  }
	  
	  // Create a prepared statement
	  String query = "delete from service where noticeId=?";
	  PreparedStatement preparedStmt = con.prepareStatement(query);
	  
	  // Binding values
	  preparedStmt.setInt(1, Integer.parseInt(noticeId));

	  // Execute the statement
	  preparedStmt.execute();
	  con.close();
	  
	  String newNotices = readNotes();
	  output = "{\"status\":\"success\", \"data\": \"" + newNotices + "\"}"; 
	  //output = "Deleted successfully";
	  }
	 catch (Exception e)
	  {
	  //output = "Error while deleting the notice.";
	  output = "{\"status\":\"error\", \"data\": \"Error while deleting the notice.\"}";
	  System.err.println(e.getMessage());
	  }
	 return output; 
	}
}
