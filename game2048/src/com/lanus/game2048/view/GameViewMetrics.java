package com.lanus.game2048.view;

import android.graphics.RectF;

/**
 * Calculates various sizes based on screen resolution.
 * Don't forget to call onSizeChanged() before using.
 */
public class GameViewMetrics {
	
	private Context context;
	
	private int screenW, screenH;
	
	private RectF tileRect = null;
	private RectF boardRect = null;
	
	// board
	private int gridMarginX, gridMarginY;
	private int gridSpacingX, gridSpacingY;

	// tile 
	private int tileLeftTopX, tileLeftTopY;
	private int tileOffsetX, tileOffsetY;
	private int tileSizeX, tileSizeY;
	
	
	public GameViewMetrics(Context context) {
		this.context = context;
		loadScheme();
	}
	
	private void loadScheme() {
		gridMarginX = context.getUiSettings().getInt("GridMarginX", 5);
		gridMarginY = context.getUiSettings().getInt("GridMarginY", 5);
		
		gridSpacingX = context.getUiSettings().getInt("GridSpacingX", 2);
		gridSpacingY = context.getUiSettings().getInt("GridSpacingY", 2);
	}
	
	public boolean onSizeChanged(int width, int height) {
		screenW = width;
		screenH = height;
		boardRect = null; getBoardRect();
		tileRect = null; getTileRect(0,0);
		return true;
	}
	
	/**
	 * Returns RectF for tile at (x,y) (grid basis) 
	 */
	public RectF getTileRect(int x, int y) {
		if (tileRect == null) {
			tileRect = new RectF();
			
			tileLeftTopX = (int) (boardRect.left + gridMarginX);
			tileLeftTopY = (int) (boardRect.top + gridMarginY);
			
			tileSizeX = (int) (((boardRect.width() - gridMarginX*2) - 
					gridSpacingX * (context.getModel().sizeX - 1)) / 
					context.getModel().sizeX);
			tileSizeY = (int) (((boardRect.height() - gridMarginY*2) - 
					gridSpacingY * (context.getModel().sizeY - 1)) / 
					context.getModel().sizeY);
			
			tileOffsetX = tileSizeX + gridSpacingX;
			tileOffsetY = tileSizeY + gridSpacingY;
		}
		tileRect.set(
				tileLeftTopX + tileOffsetX * x, 
				tileLeftTopY + tileOffsetY * y, 
				tileLeftTopX + tileOffsetX * x + tileSizeX, 
				tileLeftTopY + tileOffsetY * y + tileSizeY
				);
		return tileRect;
	}
	
	/**
	 * Returns RectF for entire board. Usually square.
	 */
	public RectF getBoardRect() {
		if (boardRect == null) {
			if (screenW <= screenH) {
				boardRect.set(0, screenH/2-screenW/2, screenW, screenH/2+screenW/2);
			} else {
				boardRect.set(screenW/2-screenH/2, 0, screenW/2+screenH/2, screenH);
			}
		}
		return boardRect;
	}
	
	/**
	 * Curve interpolation between start/end.
	 * Power - power of curve used in interpolation formula.
	 * 		1 for linear interp (not much useful)
	 * 		2 for parabolic
	 */
	public float interpolate(float cur, float start, float end, float power) {
		if (start <= end) {
			if (cur <= start) return start;
			if (cur >= end) return end;
		} else {
			if (cur >= start) return start;
			if (cur <= end) return end;
		}
		
		float lin = (cur - start)/(end - start);
		float curve = (float) Math.pow(lin, power);
		float rev = start + (end - start) * curve;
		return rev;
	}
}
