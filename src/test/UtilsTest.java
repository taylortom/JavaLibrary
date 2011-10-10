package test;

// Java imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

// Java Library imports
import database.DatabaseConnection;
import display.CubeWindow;
import display.GLWindow;
import utils.ErrorLog;
import utils.Utils;

/**
 * Class for testing the various Utils functions
 * 
 * @author Tom
 * @version 0.2
 * @history 30.08.2011: Created class
 */
public class UtilsTest 
{    
	public static void main(String[] args)
	{	
		System.out.println("CodeTest.main");
		
		UtilsTests();
		checkLog();
	}

	
	/**
	 * Tests the ErrorLog class
	 */
	private static void checkLog()
	{
		System.out.println("UtilsTest.checkLog");
		
		// check the error log
		ErrorLog el = ErrorLog.getInstance();
		el.exportLog();
		el.printLog();
	}

	/**
	 * Some basic code to test the functionality of my database code
	 */
	private static void UtilsTests()
	{
		System.out.println("UtilsTest.UtilsTests");
		
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
}
