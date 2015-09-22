package cmpe273.lab1.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import cmpe273.lab1.db.Connection;

@Path("/catalog")
public class Catalog {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBooks() throws Exception {
		
		String returnString = null;
		Response rb = null;
		DBCollection table = null;
		
		try {
			JSONArray json = new JSONArray();
			
			table = Connection.getTable();
			DBCursor cursor = table.find();
			while(cursor.hasNext()) {
				JSONObject jsonObject = new JSONObject();
				BasicDBObject obj = (BasicDBObject) cursor.next();
				//BasicDBList name = (BasicDBList) obj.get("book_name");
				jsonObject.put("book_name", obj.getString("book_name"));
				jsonObject.put("author_name", obj.getString("author_name"));
				jsonObject.put("category", obj.getString("category"));
				jsonObject.put("price", obj.getString("price"));
				jsonObject.put("isbn", obj.getString("isbn"));
				json.put(jsonObject);
		    }
			
			
			returnString = json.toString();
			
			rb = Response.ok(returnString).build();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rb;
	}

}
