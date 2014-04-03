package com.lanus.game2048.view;

/**
 * Workaround to convert ID from xml to simple dictionary
 */

public class UISettings {
	
	// see getDesc()
	private String description = "Abstract setting manager";

	public int getInt(String strkey, int defaultval) {
		return defaultval;
	}
	
	public String getString(String strkey, String defaultval) {
		return defaultval;
	}
	
	/**
	 * User-readable description of this settings.
	 * Example: name of color scheme in list, or name of different game rules.
	 */
	public String getDesc() {
		return description;
	}
}
