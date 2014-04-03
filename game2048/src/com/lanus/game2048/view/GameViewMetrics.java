package com.lanus.game2048.view;

import android.graphics.RectF;

/**
 * Calculates various sizes based on screen resolution.
 * Don't forget to call onSizeChanged() before using.
 */
public class GameViewMetrics {
	
	private Context context;
	
	public GameViewMetrics(Context context) {
		this.context = context;

		// TODO Implement method
	}
	
	public boolean onSizeChanged(int width, int height) {
		// TODO Implement method
		return true;
	}
	
	/**
	 * Returns RectF for tile at (x,y) (grid basis) 
	 */
	public RectF getTileRect(int x, int y) {
		return null;
		// TODO Implement method
	}
	
	/**
	 * Returns RectF for entire board. Usually square.
	 */
	public RectF reqBoardRect() {
		return null;
		// TODO Implement method
	}
}
