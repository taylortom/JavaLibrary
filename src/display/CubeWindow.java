package display;

//Java imports
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

/**
 * A temporary class to test OpenGL 
 *
 * @author Tom
 * @version 0.1
 * @history Oct 12, 2011: Created class
 */

public class CubeWindow extends GLWindow
{
	private static float rotation = 0.0f;	

	public CubeWindow(String windowTitle, int windowWidth, int windowHeight)
	{
		super(windowTitle, windowWidth, windowHeight);
	}
	
	@Override
	public void display(GLAutoDrawable glDrawable)
	{
		final GL2 gl = (GL2)glDrawable.getGL();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -7.0f);
		
		// Rotate The cube around the y axis
		gl.glRotatef(rotation, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(0.0f,1.0f,0.0f);	// Color Blue
		// front
		gl.glVertex3f(0.0f, 0.0f, 0.0f); 
		gl.glVertex3f(0.0f, 1.0f, 0.0f); 
		gl.glVertex3f(1.0f, 1.0f, 0.0f);
		gl.glVertex3f(1.0f, 0.0f, 0.0f);
		gl.glColor3f(1.0f,0.5f,0.0f);	// Color Orange
		// back
		gl.glVertex3f(1.0f, 0.0f, 1.0f);
		gl.glVertex3f(1.0f, 1.0f, 1.0f); 
		gl.glVertex3f(0.0f, 1.0f, 1.0f);
		gl.glVertex3f(0.0f, 0.0f, 1.0f);
		gl.glColor3f(1.0f,0.0f,0.0f);	// Color Red	
		// top
		gl.glVertex3f(0.0f, 1.0f, 0.0f);
		gl.glVertex3f(0.0f, 1.0f, 1.0f);	
		gl.glVertex3f(1.0f, 1.0f, 1.0f);
		gl.glVertex3f(1.0f, 1.0f, 0.0f);
		gl.glColor3f(1.0f,1.0f,0.0f);	// Color Yellow
		// bottom
		gl.glVertex3f(0.0f, 0.0f, 1.0f);
		gl.glVertex3f(0.0f, 0.0f, 0.0f); 
		gl.glVertex3f(1.0f, 0.0f, 0.0f);
		gl.glVertex3f(1.0f, 0.0f, 1.0f);
		gl.glColor3f(0.0f,0.0f,1.0f);	// Color Blue
		// left
		gl.glVertex3f(0.0f, 0.0f, 1.0f);
		gl.glVertex3f(0.0f, 1.0f, 1.0f); 
		gl.glVertex3f(0.0f, 1.0f, 0.0f);
		gl.glVertex3f(0.0f, 0.0f, 0.0f);
		gl.glColor3f(1.0f,0.0f,1.0f);	// Color Violet
		// right
		gl.glVertex3f(1.0f, 0.0f, 0.0f);
		gl.glVertex3f(1.0f, 1.0f, 0.0f); 
		gl.glVertex3f(1.0f, 1.0f, 1.0f);
		gl.glVertex3f(1.0f, 0.0f, 1.0f);
		gl.glEnd();	
		  
		rotation += 1.0;
	}

}
