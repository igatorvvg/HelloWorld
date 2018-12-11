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

	 /*String username = dbUri.getUserInfo().split(":")[0];
	 System.out.println("username  is "+username );
	 String password = dbUri.getUserInfo().split(":")[1];
	 System.out.println(" password is "+ password );
	 String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
	 System.out.println(" dbUrl is "+ dbUrl +"...... getting connection now.........");
	 Connection con= DriverManager.getConnection(dbUrl, username, password);		
	 String username = "jyqvcvchuuoqmc";
	 System.out.println("username  is "+username );
	 String password = "6f9d65aa72de601e057373cc87686b53051c381fbb6ba844aee84121ebe7c749";
	 System.out.println(" password is "+ password );
	 String dbUrl = "jdbc:postgresql://ec2-23-21-65-173.compute-1.amazonaws.com:5432/d1sddcrm86kr5n";
	 System.out.println(" dbUrl is "+ dbUrl +"...... getting connection now.........");
	 Connection con= DriverManager.getConnection(dbUrl, username, password);*/
		
	//Connection con=DriverManager.getConnection("DATABASE_URL");

	 String dbUrl = System.getenv("JDBC_DATABASE_URL");
    	Connection con= DriverManager.getConnection(dbUrl);	
		
	System.out.println("Connected to PostgreSQL database!");
	Statement statement = con.createStatement();
	System.out.println("Reading db records...");
	
	ResultSet resultSet = statement.executeQuery("select * from salesforce.contact fetch first 3 rows only");
	while (resultSet.next()) 
	{
		System.out.printf(" Name is: "+resultSet.getString("name") + " and SFid is " + resultSet.getString("sfid"));
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

