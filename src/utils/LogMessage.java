package utils;

/**
 * A basic class to store individual error information
 *
 * @author Tom
 * @version 0.1
 * @history 30.08.2011: Created class
 */
public class LogMessage 
{
    private final String NODE_NAME = "log_message";
	
    private String className;
    private String methodName;
    private String message;
    private ErrorLog.Error errorLevel;
    private String date;

    /**
     * Constructor method
     * @param msg
     * @param lvl 
     */
    public LogMessage(String classString, String method, String msg, ErrorLog.Error lvl)
    {
	this.className = classString;
	this.methodName = method;
	this.message = msg;
	this.errorLevel = lvl;
	this.date = Utils.getTimeStamp("");
    }

    /**
     * Concatenates the log data into a single string
     * @return the log data string
     */
    public String getMessage()
    {
	return this.date + ": " + this.className + "::" + this.methodName + "::" + this.errorLevel + " " + this.message;
    }
	
    /**
     * Formats the log data into an XML-friendly format
     * @return the data formatted for XMl
     */
    public String getXML()
    {
	String output = "<" + this.NODE_NAME + ">\n";
	output += "<date>" + this.date + "</date>\n";
	output += "<caller>" + this.className + "::" + this.methodName + "</caller>\n";
	output += "<error_level>" + this.errorLevel + "</error_level>\n";
	output += "<message>" + this.message + "</message>\n";
	output += "</" + this.NODE_NAME + ">";
		
	return output;
    }
    
    /**
     * Just prints out the error message
     */
    public void printMessage()
    {
	System.out.println(this.getMessage());
    }
}