package cmpe273.lab1.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

import cmpe273.lab1.db.Operations;

@Path("/search")
public class Search {
	
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertBook(String incomingData) throws Exception {

		Operations operation = new Operations();
		Response rb = null;

		try {
			JSONObject partsData = new JSONObject(incomingData);
			System.out.println("Search Category: " + partsData.toString());

			rb = operation.searchBooks(partsData.optString("CATEGORY"));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(500)
					.entity("Server was not able to process your Request.")
					.build();
		}
		return rb;
	}

}
