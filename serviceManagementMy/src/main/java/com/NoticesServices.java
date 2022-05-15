package com;

import model.Notices;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/Notices")
public class NoticesServices
{
	Notices noteObj = new Notices();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readNotes()
	{
	return noteObj.readNotes();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertNotes(@FormParam("phone") String phone,
	 @FormParam("address") String address,
	 @FormParam("note") String note,
	 @FormParam("zipcode") String zipcode)
	{
	 String output = noteObj.insertNotes(phone, address,note,zipcode);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateNotes(String noteData)
	{
	//Convert the input string to a JSON object
	 JsonObject noteObject = new JsonParser().parse(noteData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 String noticeId = noteObject.get("noticeId").getAsString();
	 String phone = noteObject.get("phone").getAsString();
	 String address = noteObject.get("address").getAsString();
	 String note = noteObject.get("note").getAsString();
	 String zipcode = noteObject.get("zipcode").getAsString();
	 String output = noteObj.updateNotes(noticeId, phone, address, note,zipcode);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteNotes(String noteData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(noteData, "", Parser.xmlParser());

	//Read the value from the element <noticeId>
	 String noticeId = doc.select("noticeId").text();
	 String output = noteObj.deleteNotes(noticeId);
	return output;
	}
}
