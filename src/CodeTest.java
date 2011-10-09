// imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import database.DatabaseConnection;
import utils.ErrorLog;
import utils.Utils;

/**
 * Basic class for testing purposes...
 * 
 * @author Tom
 * @version 0.2
 * @history 30.08.2011: Created class
 */
public class CodeTest 
{    
	public static void main(String[] args)
	{	
		oldTests();
	}

	private static void checkLog()
	{
		/**
		 *  check the error log
		 */
		ErrorLog el = ErrorLog.getInstance();
		if(false) el.exportLog();
		else el.printLog();
	}

	private static void oldTests()
	{
		ArrayList<String> al = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6"));

		System.out.println("al contains (int)2:    " + Utils.arraySearch(al, 2));
		System.out.println("al contains (String)2: " + Utils.arraySearch(al, "2"));

		System.out.println("Pre-shuffle:           " + al);
		Utils.arrayShuffle(al);
		System.out.println("Post-shuffle:          " + al);
		System.out.println();

		String s = "[test] is merely a test string... [test]";
		s = Utils.stringSearchAndReplace(s, "[test]", "doop");

		// test using instanceof
		if (s instanceof String) System.out.println(s);
		else System.err.println("Not a string!");

		// some error log testing
		Utils.stringToInt("1999");     // number
		Utils.stringToInt("hello");    // string
		Utils.stringToInt("ooooh");    // string
		Utils.stringToInt("0202");     // number
		Utils.stringToInt("another");  // string
	}
	
	private static void MySQLJDBCTests()
	{
		/**
		 *  some database testing
		 */
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
	}

	private static void JDBCODBCTests()
	{
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
			el.addMessage("JavaApplication", "main", e.getMessage(), ErrorLog.Error.SQL); 
		}

		// close the connection when we've finished
		dc.close();
	}
}
