package test;

// Java Library imports
import display.CubeWindow;

/**
 * A class used to test the display package code
 * 
 * @author Tom
 * @version 0.1
 * @history Oct 11, 2011: Created class
 */
public class DisplayTest
{
	public static void main(String[] args)
	{
		System.out.println("DisplayTest.main");
		
		Thread t1 = new Thread();
		t1.start();
		
		CubeWindow window = new CubeWindow("Test", 800, 600);
	}
}
