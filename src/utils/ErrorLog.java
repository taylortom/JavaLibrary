package utils;

// imports
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Basic class to handle the error log. 
 * A singleton class, there should only ever be one instance of ErrorLog
 * 
 * @author Tom
 * @version 0.1
 * @history 30.08.2011: Created class
 */
public class ErrorLog 
{
	private static final String LOG_NAME = "logs/error_log_" + Utils.getTimeStamp("yy_MM_dd") + ".xml";

	private static ErrorLog instance = null;

	// the possible error states
	public static enum Error { DEBUG, IO, SQL, FORMAT, ERROR, FATAL }

	// a list of the error log messages
	private ArrayList<LogMessage> logMessages = new ArrayList<LogMessage>();

	/**
	 * Returns the instance of the ErrorLog
	 * @return the ErrorLog instance
	 */
	public static ErrorLog getInstance() 
	{
		if(instance == null) { instance = new ErrorLog(); }
		return instance;
	}

	/**
	 * Adds a new error message to the error log
	 * @param msg
	 * @param error 
	 */
	public void addMessage(String clss, String mthd, String msg, Error error)
	{
		// should probably add some checks here...

		LogMessage lm = new LogMessage(clss, mthd, msg, error);	
		this.logMessages.add(lm);
	}

	/**
	 * Sorts the log according to the supplied param
	 * @param the sort parameter
	 */
	public void sortLog()
	{
		System.out.println("ErrorLog.sortLog"); 

		//TODO implement sortLog method. Possibly split into multiple methods?
	}

	/**
	 * Prints out the contents of the current error log
	 */
	public void printLog() 
	{ 
		System.out.println("\nError log\nLog genenerated on " + Utils.getTimeStamp("dd/MM/yyyy"));

		if (this.logMessages.size() > 0)
		{
			System.out.println("--begin log--");
			for (int i = 0; i < this.logMessages.size(); i++)
			{
				LogMessage lm = (LogMessage) this.logMessages.get(i);
				lm.printMessage();
			}
			System.out.println("--end log--\n");
		}
		else System.out.println("\nCongratulations, no errors to display.");
	}

	/**
	 * Processes the error log and saves as an external file
	 */
	public void exportLog()
	{	
		try
		{
			FileWriter fw = new FileWriter(LOG_NAME);

			// add file header
			fw.append("Error Log");
			fw.append('\n');
			fw.append("Log genenerated on " + Utils.getTimeStamp("dd/MM/yyyy - HH:mm:ss"));
			fw.append('\n');
			fw.append('\n');
			fw.append("--begin log--");
			fw.append('\n');

			// add log messages
			for (int i = 0; i < this.logMessages.size(); i++) 
			{
				LogMessage lm = (LogMessage)this.logMessages.get(i);

				fw.append(lm.getMessage());
				fw.append('\n');
			}

			// add footer and close FileWriter
			fw.append("--end log--");
			fw.flush();
			fw.close();
		}
		catch(IOException e)
		{
			// well this is embarrassing; encountered error when exporting the error log
			ErrorLog el = ErrorLog.getInstance();
			el.addMessage("ErrorLog", "exportLog", "Encountered an error when trying to export error log", ErrorLog.Error.IO);
		}
	}

	/**
	 * The constructor method, should never be called directly
	 */
	protected ErrorLog() { }
}