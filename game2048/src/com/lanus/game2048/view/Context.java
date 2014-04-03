package com.lanus.game2048.view;

import com.lanus.game2048.GameGrid;

/**
 * See design pattern: Mediator
 */
public interface Context {
	
	public GameViewMetrics getMetrics();
	public GestureRecognizer getGest();
	public UISettings getUiSettings();
	public GameGrid getModel();
	
	/** 
	 * Mark animation as active until Animation.isOver() returns true.
	 */
	public void startAnim(Animation anim);

}
