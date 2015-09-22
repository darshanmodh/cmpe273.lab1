package cmpe273.lab1.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import cmpe273.lab1.db.Operations;

@Path("/bookstore")
public class Bookstore {

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertBook(String incomingData,
			@Context HttpServletRequest requestContext,
			@Context SecurityContext context) throws Exception {

		String returnString = null;
		JSONArray json = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		Operations operation = new Operations();
		
		String yourIP = requestContext.getRemoteAddr().toString();

		try {
			JSONObject partsData = new JSONObject(incomingData);
			System.out.println("jsonData: " + partsData.toString());

			int http_code = operation.insertBook(
					partsData.optString("BOOK_NAME"),
					partsData.optString("AUTHOR_NAME"),
					partsData.optString("CATEGORY"),
					partsData.optString("PRICE"), partsData.optString("ISBN"));

			if (http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Book has been inserted successfully");
				returnString = json.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Unable to enter Item")
						.build();
			}
			System.out.println("IP:" + yourIP);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(500)
					.entity("Server was not able to process your Request.")
					.build();
		}
		return Response.ok(returnString).build();
	}

}
