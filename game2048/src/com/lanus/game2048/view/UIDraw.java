package com.lanus.game2048.view;

import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Draw typical board and tiles
 */
public class UIDraw {
	
	private Context context;
	
	public UIDraw(Context context) {
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Draws tile at rect (px).
	 * type:
	 * 		0 = no tile
	 * 		1 = "2"
	 * 		2 = "4"
	 * 		3 = "8"
	 * 		...
	 */
	public void drawTile( Canvas c, RectF rect, int type) {
		// TODO Implement method
	}
	
	/**
	 * Draws tile with born animation.
	 * age:
	 * 		0.0 = begin of born animation
	 * 		1.0 = end of born animation
	 * 		linear interpolation
	 * 
	 * See also drawTile()
	 */
	public void drawNewbornTile( Canvas c, RectF rect, int type, float age) {
		// TODO Implement method
	}
	
	/**
	 * Draw everything except tiles
	 */
	public void preGridDraw( Canvas c ) {
		// TODO Implement method
	}
	
	/**
	 * Draw additional information: score, highscore, etc...
	 */
	public void postGridDraw( Canvas c ) {
		// TODO Implement method
	}
	
}
