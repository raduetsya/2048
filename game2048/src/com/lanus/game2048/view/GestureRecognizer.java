package com.lanus.game2048.view;

import com.lanus.game2048.GameGrid.DIRECTION;

/**
 * Convert touch gestures to tile offsets.
 * 
 * Note: methods onPress(), onUnpress(), onMove() return true, if user 
 * made an action with his gesture. Usually onPress() and onMove()
 * returns true, but who knows how are you going to upgrade this class.
 */
public class GestureRecognizer {
	
	private Context context;
	private DIRECTION direction;
	int gestOffsetX, gestOffsetY;
	
	public GestureRecognizer(Context context) {
		this.context = context;
		
		// TODO Implement method
	}

	public boolean onPress(int x, int y) {
		// TODO Implement method
		return false;
	}
	
	public boolean onUnpress(int x, int y) {
		// TODO Implement method
		return false;
	}
	
	public boolean onMove(int x, int y) {
		// TODO Implement method
		return false;		
	}
	
	/**
	 * Returns true if gesture is recognized
	 */
	public boolean isDirectionRecognized() {
		return (direction != DIRECTION.NONE);
	}
	
	/**
	 * Returns offset in px for current gesture.
	 * If you want direction, use getSignX() and getSignY().
	 */
	public int getOffset() {
		return Math.max( Math.abs(gestOffsetX), Math.abs(gestOffsetY) );
	}
	
	/**
	 * Returns -1 for LEFT, 1 for RIGHT.
	 * Otherwise 0.
	 */
	public int getSignX() {
		if ( Math.abs(gestOffsetX) > Math.abs(gestOffsetY) ) {
			return (int)Math.signum(gestOffsetX);
		} else {
			return 0;
		}
	}
	
	/**
	 * Returns -1 for UP, 1 for DOWN.
	 * Otherwise 0.
	 */
	public int getSignY() {
		if ( Math.abs(gestOffsetX) < Math.abs(gestOffsetY) ) {
			return (int)Math.signum(gestOffsetY);
		} else {
			return 0;
		}		
	}
}
