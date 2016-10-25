package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBConnection {

	public static void main(String []args)
	{
		Connection conn;
		String dbUrl="jdbc:mysql://triton.qa.telkomsa.net:3306/";
		String databaseName = "scapdev";
		String UserName ="developer";
		String Password="jb055";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl+databaseName, UserName, Password);
			String sqlQuerry = "SELECT * FROM AuditTrailLog A";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sqlQuerry);
			result.next();
			System.out.println(result.getString("action"));
			System.out.println(result.getString("detail"));
			System.out.println(result.getString("methodName"));
			System.out.println(result.getString("parameters"));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			
		}

		
	}
}





