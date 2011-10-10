package database;

//Java imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;

// Java Library
import utils.ErrorLog;

/**
 * Class to store data relating to an individual database connection
 *
 * @author Tom
 * @version 0.1
 * @history 07.09.2011: Created class
 */
public class DatabaseConnection
{
	// used to identify this particular connection
	public String name;
	// the name/location of the database
	private String database;
	// the driver used for the database
	private String driver;
	// the username and password
	private String username;
	private String password;
	private Connection connection;

	/**
	 * Constructor method
	 */
	public DatabaseConnection(String name, String database, String driver, String username, String password)
	{
		this.name = name;	
		this.database = database;
		this.driver = driver;
		this.username = username;
		this.password = password;

		this.open();
	}

	/**
	 * Runs a read SQL query on the database
	 * @param SQL query in String format
	 * @return the ResultSet from the query
	 */
	public ResultSet readData(String query)
	{	
		try
		{ 
			Statement s = this.connection.createStatement();
			return s.executeQuery(query);

		}
		catch (SQLException e)
		{
			ErrorLog el = ErrorLog.getInstance();
			el.addMessage("DatabaseConnection", "readData", e.toString() + " " + e.getErrorCode() + " " + e.getSQLState(), ErrorLog.Error.SQL);
			return null;
		}
	}

	public void writeData(String query, String[] valuesToWrite)
	{
		try
		{ 
			PreparedStatement ps = this.connection.prepareStatement(query);

			for (int i = 0; i < valuesToWrite.length; i++)
			{
				ps.setString(i+1, valuesToWrite[i]);
			}

			ps.executeUpdate();
			this.close();
			this.open();
		}
		catch (SQLException e)
		{
			ErrorLog el = ErrorLog.getInstance();
			el.addMessage("DatabaseConnection", "writeData", e.toString() + " " + e.getErrorCode() + " " + e.getSQLState(), ErrorLog.Error.SQL);
		}
	}

	/**
	 * Establishes a connection to the database
	 */
	public void open()
	{
		try
		{
			Class.forName(driver);
			// database name, username, password
			this.connection = DriverManager.getConnection(database, username, password);
		}
		catch (SQLException e)
		{
			ErrorLog el = ErrorLog.getInstance();
			el.addMessage("DatabaseConnection", "open", e.toString() + " " + e.getErrorCode() + " " + e.getSQLState(), ErrorLog.Error.SQL);
		}
		catch (ClassNotFoundException e)
		{
			ErrorLog el = ErrorLog.getInstance();
			el.addMessage("DatabaseConnection", "open", e.toString(), ErrorLog.Error.ERROR);
		}
	}

	public void close()
	{
		try
		{
			this.connection.close();
		}
		catch (SQLException e)
		{
			ErrorLog el = ErrorLog.getInstance();
			el.addMessage("DatabaseConnection", "close", "Error closing connection - " + e.getMessage(), ErrorLog.Error.SQL);
		}
	}
}
