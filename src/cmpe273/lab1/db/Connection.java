package cmpe273.lab1.db;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Connection {
	
	private static MongoClient mongo = null;
	private static DB db = null;
	private static DBCollection table = null;
	
	public static DBCollection getTable() throws Exception {
		
		mongo = new MongoClient("localhost", 27017);
		db = mongo.getDB("test");
		table = db.getCollection("cmpe273");
		return table;
		
	}
	
}
