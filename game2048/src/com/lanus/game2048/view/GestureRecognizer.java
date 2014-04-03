package com.lanus.game2048.view;

import com.lanus.game2048.GameGrid.DIRECTION;

/**
 * Convert touch gestures to tile offsets.
 * 
 * Note: methods onPress(), onUnpress(), onMove() return true, if user 
 * made an action with his gesture. Usually onPress() and onMove()
 * returns true, but who knows how are you going to upgrade this class.
 * getLastDir() returns direction from last user action, if you mind.
 * 
 */
public class GestureRecognizer {
	
	private static final int MINIMAL_OFFSET = 15;
	
	private Context context;
	private DIRECTION direction;
	
	private DIRECTION lastDir;
	
	int gestCenterX, gestCenterY;
	int gestOffsetX, gestOffsetY;
	
	public GestureRecognizer(Context context) {
		this.context = context;
	}

	public boolean onPress(int x, int y) {
		gestCenterX = x; gestCenterY = y;
		gestOffsetX = 0; gestOffsetY = 0;
		direction = DIRECTION.NONE;
		return true;
	}
	
	public boolean onUnpress(int x, int y) {
		if (getOffset() >= MINIMAL_OFFSET) {
			lastDir = getDirection();
			return true;
		}
		return false;
	}
	
	public boolean onMove(int x, int y) {
		gestOffsetX = x - gestCenterX;
		gestOffsetY = y - gestCenterY;
		return false;		
	}
	
	/**
	 * Returns true if gesture is recognized
	 */
	public boolean isDirectionRecognized() {
		return (direction != DIRECTION.NONE);
	}
	
	public DIRECTION getLastDir() {
		return lastDir;
	}

	/**
	 * Returns offset in px for current gesture.
	 * If you want direction, use getSignX()/getSignY().
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
	
	private DIRECTION getDirection() {
		if (getSignX() == 1) return DIRECTION.RIGHT;
		if (getSignX() == -1) return DIRECTION.LEFT;
		if (getSignY() == 1) return DIRECTION.DOWN;
		if (getSignY() == -1) return DIRECTION.UP;
		return DIRECTION.NONE;
	}
}
