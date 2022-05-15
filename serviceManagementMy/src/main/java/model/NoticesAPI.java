package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticesAPI
 */

@WebServlet("/NoticesAPI")
public class NoticesAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Notices noteObj = new Notices();
    public NoticesAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = noteObj.insertNotes(request.getParameter("phone"),
				 		request.getParameter("address"),
				 		request.getParameter("note"),
				 		request.getParameter("zipcode"));
		response.getWriter().write(output); 
	}
	
	// Convert request parameters to a Map
	private static Map<String, String> getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
	
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");//UTF-8 
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			 
			for (String param : params)
			{ 
				 String[] p = param.split("=");
				 map.put(p[0], p[1]);
			}
		 }
		catch (Exception e)
		{
			}
			return map;
			}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Map<String, String> paras = getParasMap(request);
			String output = noteObj.updateNotes(paras.get("hidItemIDSave").toString(),
							paras.get("phone").toString(),
							paras.get("address").toString(),
							paras.get("note").toString(),
							paras.get("zipcode").toString());
			response.getWriter().write(output); 
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Map<String, String> paras = getParasMap(request);
			String output = noteObj.deleteNotes(paras.get("noticeId").toString());
			response.getWriter().write(output); 
	}

}
