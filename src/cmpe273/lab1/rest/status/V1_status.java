package cmpe273.lab1.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/v1/status")
public class V1_status {
	
	private static final String api_version = "00.01.00";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version: " + api_version + "</p>";
	}
	
/*	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String retrunDatabseStatus() throws Exception {
		
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		
		try {
			conn = OracleDB.oracleDBConn().getConnection();
			query = conn.prepareStatement("select to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') DATETIME " +
					"from sys.dual");
			ResultSet rs = query.executeQuery();
			
			while(rs.next()) {
				myString = rs.getString("DATETIME");
			}
			
			query.close();
			returnString = "<p>Database Status</p>" + 
					"<p>Database Date/Time return: " + 
					myString + "</p>";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
		return returnString;
	}
*/
}
