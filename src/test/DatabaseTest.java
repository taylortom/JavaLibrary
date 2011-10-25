package test;

// Java imports
import java.sql.ResultSet;
import java.sql.SQLException;

// Java Library imports
import utils.ErrorLog;
import database.DatabaseConnection;

/**
 * A class to test out the database functionality
 * 
 * @author Tom
 * @version 0.1
 * @history Oct 10, 2011: Created class
 */
public class DatabaseTest
{
	public static void main(String[] args)
	{
		JDBCODBCTests();
	}
	
	/**
	 * Some basic code to test the functionality of my database code 
	 */
	private static void JDBCODBCTests()
	{
		System.out.println("DatabaseTest.JDBCODBCTests");
		
		DatabaseConnection dc = new DatabaseConnection("worldEnergy", "jdbc:odbc:WorldEnergy", "sun.jdbc.odbc.JdbcOdbcDriver", "", "");

		String writeQuery = "INSERT INTO Test(Name, Var3, Var4) VALUES(?, ?, ?)";
		String[] values = { "Tom", "dfkjsafkjdlslkfjdsla", "2010"};

		String readQuery = "SELECT * FROM Test";
		dc.writeData(writeQuery, values);

		ResultSet results = dc.readData(readQuery);

		// check the received results   	    
		try
		{
			while (results.next())
			{
				System.out.println(results.getString(1) + "\t" + results.getString(2) + "\t" + results.getString(3) + "\t" + results.getString(4));
			}
		}
		catch (SQLException e) 
		{ 
			ErrorLog el = ErrorLog.getInstance();
			el.addMessage("DatabaseTest", "main", e.getMessage(), ErrorLog.Error.SQL); 
		}

		// close the connection when we've finished
		dc.close();
	}
	
	/*// not currently working
	private static void MySQLJDBCTests()
	{
		// some database testing
		DatabaseConnection dc = new DatabaseConnection("javaTest", "jdbc:mysql://188.65.115.75/taylorto_java", "com.mysql.jdbc.Driver", "taylorto_tom", "taylortom0378");

		try
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://taylortom.co.uk/taylorto_java", "taylorto_tom", "taylortom0378");
		}
		catch (SQLException e)
		{
			ErrorLog el = ErrorLog.getInstance();
			el.addMessage("CodeTest", "main", e.getMessage(), ErrorLog.Error.ERROR);
		}

		String readQuery = "SELECT * FROM Users";

		ResultSet results = dc.readData(readQuery);

		// check the received results   	    
		try
		{
			while (results.next())
			{
				System.out.println(results.getString(1) + "\t" + results.getString(2) + "\t" + results.getString(3) + "\t" + results.getString(4));
			}
		}
		catch (SQLException e) 
		{ 
			ErrorLog el = ErrorLog.getInstance();
			el.addMessage("JavaApplication", "main", e.getMessage(), ErrorLog.Error.SQL); 
		}

		// close the connection when we've finished
		dc.close();
	}*/
}
