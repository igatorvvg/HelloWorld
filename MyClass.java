import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
class MyClass
{

public static void main(String [] str)
{
System.out.println("HelloWorld");
System.out.println("Creating a connection to postgress database");

	try
	{
	Connection con=DriverManager.getConnection("postgres://jyqvcvchuuoqmc:6f9d65aa72de601e057373cc87686b53051c381fbb6ba844aee84121ebe7c749@ec2-23-21-65-173.compute-1.amazonaws.com:5432/d1sddcrm86kr5n"
);
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
