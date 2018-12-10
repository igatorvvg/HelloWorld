import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.URISyntaxException;
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
	 System.out.println("dbUri is "+dbUri);

	 String username = dbUri.getUserInfo().split(":")[0];
	 System.out.println("username  is "+username );
	 String password = dbUri.getUserInfo().split(":")[1];
	 System.out.println(" password is "+ password );
	 String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
	 System.out.println(" dbUrl is "+ dbUrl +"...... getting connection now.........");
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
	catch (URISyntaxException e) {
			System.out.println("URI exception caused Connection failure.");
			e.printStackTrace();
		
	}
	catch (SQLException e) {
			System.out.println("SQL Exception caused Connection failure.");
			e.printStackTrace();
		
	}
	catch(Exception e)
	{
			System.out.println(" general exception caused Connection failure.");
			e.printStackTrace();
	}

}
}

