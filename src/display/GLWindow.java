package display;

// Java imports
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.*;
import javax.swing.JFrame;
import com.jogamp.opengl.util.FPSAnimator;

// Java Library imports
import utils.ErrorLog;

/**
 * A basic window class.
 * Creates a window for the good stuff
 * 
 * @author Tom
 * @version 0.1
 * @history 10.11.2011: Created class
 */

public class GLWindow implements GLEventListener
{
	private static final GLU glu = new GLU();
	
	// a reference to the frame
	private static JFrame frame;
	
	// the title of the window
	private static String title = ""; 
	
	// should never be accessed directly, instead use the setSize method
	private static int width = 0;
	private static int height = 0;
	
	private static int framerate = 60;
	
	public GLWindow(String windowTitle, int windowWidth, int windowHeight) 
	{
		System.out.println("Window.Window");
		
		title = windowTitle;
		width = windowWidth;
		height = windowHeight;
		
		frame = new JFrame(title);
		GLCanvas canvas = new GLCanvas();
		canvas.addGLEventListener(this);
		frame.add(canvas);
		frame.setSize(width, height);
		
		showWindow(true);
		
		//call the display() method 60 time per second
		FPSAnimator animator = new FPSAnimator(canvas, framerate);
		animator.add(canvas);
		animator.start();
	}
	
	@Override
	public void display(GLAutoDrawable glDrawable)
	{
		final GL2 gl = (GL2)glDrawable.getGL();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -7.0f);
	}

	@Override
	public void dispose(GLAutoDrawable arg0) { }

	@Override
	public void init(GLAutoDrawable glDrawable)
	{
		final GL2 gl = (GL2)glDrawable.getGL();
		
		// Set the state of our new OpenGL context
		gl.glViewport (0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION); 
		gl.glLoadIdentity();
		glu.gluPerspective(45.0f, (float)(width)/(float)(height), 1.0f, 100.0f); 
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		// Enable Smooth Shading
		gl.glShadeModel(GL2.GL_SMOOTH);
		
		// Black Background
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		
		// Depth Buffer Setup
		gl.glClearDepth(1.0f);

		// Enables Depth Testing
		gl.glEnable(GL.GL_DEPTH_TEST);
		
		// The Type Of Depth Testing To Do
		gl.glDepthFunc(GL.GL_LEQUAL);
		
		// Start culling back faces
		gl.glEnable(GL.GL_CULL_FACE);
		
		// Really Nice Perspective Calculations
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {	}
	
	public void resizeWindow(int newWidth, int newHeight)
	{
		width = newWidth;
		height = newHeight;
		frame.setSize(width, height);
	}
	
	private void showWindow(Boolean visible)
	{
		frame.setVisible(visible);
	}
}

