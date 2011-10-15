package datatypes;

/**
 * Class to represent colour data
 * Primarily used with OpenGL
 *
 * @author Tom
 * @version 0.1
 * @history Oct 14, 2011: Created class
 */
public class Colour
{
	// stored as objects as can represent multiple 
	private byte _red;
	private byte _green;
	private byte  _blue;
	
	/**
	 * Constructor - byte
	 * @param r red value
	 * @param g green value
	 * @param b blue value
	 */
	public Colour(byte r, byte g, byte b)
	{
		this._red = r;
		this._green = g;
		this._blue = b;
	}

	/**
	 * Public getters
	 * @return the colour
	 */
	public byte red() { return _red; }
	public byte green() { return _green; }
	public byte blue() { return _blue; }
}
