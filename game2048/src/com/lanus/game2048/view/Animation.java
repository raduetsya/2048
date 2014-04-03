package com.lanus.game2048.view;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

/**
 * Another game mode. 
 * Methods of active animation are called before usual draw cycle, 
 * and if they return true then usual draw cycle don't execute. 
 */
public class Animation {
	
	/**
	 * Moves tile to any new position.
	 */
	public boolean handleTileDraw( int x, int y, RectF rect ) {
		return false;
	}
	
	/**
	 * Called after full canvas rendering
	 * Note: nobody cares what this function returns 
	 */
	public boolean handleCanvasDraw( Canvas c ) {
		return false;
	}
	
	/**
	 * Called before any touch events are processed
	 */
	public boolean handleTouchEvent( MotionEvent ev ) {
		return false;
	}
	
	/**
	 * Returns true if animation is over.
	 * See also isEnded()
	 */
	public boolean isOver() {
		return true;
	}
	
	/**
	 * Returns true if object may be deleted from memory
	 */
	public boolean isEnded() {
		return false;
	}

}
