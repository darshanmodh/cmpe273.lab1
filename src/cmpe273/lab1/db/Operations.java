package cmpe273.lab1.db;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class Operations extends Connection {

	public int insertBook(String BOOK_NAME, String AUTHOR_NAME,
			String CATEGORY, String PRICE, String ISBN) throws Exception {

		DBCollection table = null;

		try {
			table = getTable();
			Map<String, Object> docMap = new HashMap<String, Object>();
			docMap.put("book_name", BOOK_NAME);
			docMap.put("author_name", AUTHOR_NAME);
			docMap.put("category", CATEGORY);
			docMap.put("price", PRICE);
			docMap.put("isbn", ISBN);
			table.insert(new BasicDBObject(docMap));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 500;
		}
		return 200;
	}

	public Response searchBooks(String CATEGOTY) throws Exception {

		String returnString = null;
		Response rb = null;
		DBCollection table = null;
		JSONArray json = new JSONArray();
		try {
			table = getTable();
			BasicDBObject query = new BasicDBObject();
			query.put("category", CATEGOTY);
			DBCursor cursor = table.find(query);
			if (cursor.size() == 0) {
				returnString = "No Record Found";
				return Response.ok().entity("No record found").build();
			}
			while (cursor.hasNext()) {
				JSONObject jsonObject = new JSONObject();
				BasicDBObject obj = (BasicDBObject) cursor.next();
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

	public int deleteBook(String book_name) throws Exception {
		// TODO Auto-generated method stub
		DBCollection table = null;
		try {
			table = getTable();
			BasicDBObject query = new BasicDBObject();
			query.put("book_name", book_name);
			table.remove(query);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 500;
		} 
		return 200;
	}
	
}
