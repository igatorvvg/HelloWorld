import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.URI;
class MyClass
{

public static void main(String [] str)
{
System.out.println("HelloWorld");
System.out.println("Creating a connection to postgress database");

	try
	{
	 URI dbUri = new URI(System.getenv("DATABASE_URL"));

	 String username = dbUri.getUserInfo().split(":")[0];
	 String password = dbUri.getUserInfo().split(":")[1];
	 String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
         Connection con= DriverManager.getConnection(dbUrl, username, password);		
		
		
	//Connection con=DriverManager.getConnection("DATABASE_URL");
	System.out.println("Connected to PostgreSQL database!");
	Statement statement = con.createStatement();
	System.out.println("Reading db records...");
	
	ResultSet resultSet = statement.executeQuery("select * from salesforce.contact fetch first 3 rows only");
	while (resultSet.next()) 
	{
		System.out.printf(resultSet.getString("name"), resultSet.getString("sfid"));
	}

	}
	catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		
	}

}
}

