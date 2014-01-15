package com.chaumoha;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class FightActivity extends Activity{
	
	private GLSurfaceView graphicsView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		graphicsView = new GraphicsView(this);
		setContentView(graphicsView);
	}
	
	class GraphicsView extends GLSurfaceView{

		public GraphicsView(Context context) {
			super(context);
			// Render the view only when there is a change in the drawing data
//			setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
			
			setRenderer(new GraphicsRenderer());
		}
		
	}
	
	class GraphicsRenderer implements GLSurfaceView.Renderer{
		Square s;
		
		public void onSurfaceCreated(GL10 unused, EGLConfig config) {
	        // Set the background frame color
	        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	        s = new Square();
	    }

	    public void onDrawFrame(GL10 unused) {
	        // Redraw background color
	        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
	    }

	    public void onSurfaceChanged(GL10 unused, int width, int height) {
	        GLES20.glViewport(0, 0, width, height);
	    
	    }
	}
	
	public class Square{
		private FloatBuffer vertexBuffer;
	    private ShortBuffer drawListBuffer;

	    // number of coordinates per vertex in this array
	    static final int COORDS_PER_VERTEX = 3;
	    float squareCoords[] = {
	            -0.5f,  0.5f, 0.0f,   // top left
	            -0.5f, -0.5f, 0.0f,   // bottom left
	             0.5f, -0.5f, 0.0f,   // bottom right
	             0.5f,  0.5f, 0.0f }; // top right

	    private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices

		
		public Square() {
	        // initialize vertex byte buffer for shape coordinates
	        ByteBuffer bb = ByteBuffer.allocateDirect(
	        // (# of coordinate values * 4 bytes per float)
	                squareCoords.length * 4);
	        bb.order(ByteOrder.nativeOrder());
	        vertexBuffer = bb.asFloatBuffer();
	        vertexBuffer.put(squareCoords);
	        vertexBuffer.position(0);

	        // initialize byte buffer for the draw list
	        ByteBuffer dlb = ByteBuffer.allocateDirect(
	        // (# of coordinate values * 2 bytes per short)
	                drawOrder.length * 2);
	        dlb.order(ByteOrder.nativeOrder());
	        drawListBuffer = dlb.asShortBuffer();
	        drawListBuffer.put(drawOrder);
	        drawListBuffer.position(0);
	    }

	}
}
