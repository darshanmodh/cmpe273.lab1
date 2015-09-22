package cmpe273.lab1.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import cmpe273.lab1.db.Operations;

@Path("/delete")
public class DeleteBook {

	@DELETE
	@Path("/{book_name}/{isbn}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBook(@PathParam("book_name") String book_name,
			@PathParam("isbn") String isbn, String incomingData)
			throws Exception {

		String book = null;
		int http_code;
		String returnString;
		JSONArray json = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		Operations operation = new Operations();

		try {

			JSONObject partData = new JSONObject(incomingData);
			book = partData.optString("book_name");
			System.out.println("BOOK:" + book);
			http_code = operation.deleteBook(book);

			if (http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been deleted successfully.");
			} else {
				return Response.status(500)
						.entity("Server was not able to process your request.")
						.build();
			}
			returnString = json.put(jsonObject).toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(500)
					.entity("Server was not able to process your request.")
					.build();
		}
		return Response.ok(returnString).build();

	}

}
