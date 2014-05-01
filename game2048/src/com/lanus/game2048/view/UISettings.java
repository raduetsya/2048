package com.lanus.game2048.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseArray;

/**
 * Workaround to convert ID from xml to simple dictionary
 * 
 * usage:
 * new UISettings(getResources().getXml(R.xml.settings))
 * 
 * uisett.getInt("grid-margins", 0);
 * uisett.getColor("default-tile color");
 * uisett.getColor( tileRang, "text-color" );
 */

public class UISettings {
	
	// see getDesc()
	private String description = "Empty setting manager";
	
	// data
	private Map<String, Integer> data_int;
	private Map<String, String> data_str;
	private Map<String, Integer> data_color;
	
	private SparseArray<Map<String, String>> tile_attributes;
	
	public UISettings(XmlResourceParser xml) throws XmlPullParserException, IOException{
		parseXml(xml, "default");
	}
	
	public int getInt(String strkey, int defaultval) {
		if (data_int.containsKey(strkey))
			return data_int.get(strkey);
		else {
			Log.w("UISettigs", "Value " + strkey + " (integer) was requested but not found");
			return defaultval;
		}
	}
	
	public String getStr(String strkey, String defaultval) {
		if (data_str.containsKey(strkey))
			return data_str.get(strkey);
		else {
			Log.w("UISettigs", "Value " + strkey + " (string) was requested but not found");
			return defaultval;
		}
	}
	
	public int getColor(String strkey) {
		if (data_color.containsKey(strkey))
			return data_color.get(strkey);
		else {
			Log.w("UISettigs", "Value " + strkey + " (color) was requested but not found");
			return Color.MAGENTA;
		}
	}
	
	public int getInt(int tile, String strkey, int defaultval) {
		return getInt(tile_attributes.get(tile).get(strkey), defaultval);
	}

	public String getStr(int tile, String strkey, String defaultval) {
		return getStr(tile_attributes.get(tile).get(strkey), defaultval);
	}
	
	public int getColor(int tile, String strkey) {
		return getColor(tile_attributes.get(tile).get(strkey));
	}
	
	/**
	 * User-readable description of this settings.
	 * Example: name of color scheme in list, or name of different game rules.
	 */
	public String getDesc() {
		return description;
	}
	
	/**
	 * Parse one tag at parser position. Add any new tags here. 
	 */
	private void addCurrentXmlTag(XmlResourceParser parser) throws XmlPullParserException, IOException {
		
		/**
		 * Description
		 */
		if (parser.getName().equals("description")) {
			description = parser.nextText();
		} else

		/**
		 * tile
		 * tile_attributes looks like:
		 * 		0:
		 * 			"label": "tile 0 label"
		 * 			"color": "tile 0 color"
		 * 			"text-color": "tile 0 text-color"
		 * 		1:
		 * 			"label": "tile 1 label"
		 * 			"color": "tile 1 color"
		 * 			"text-color": "tile 1 text-color"
		 * ...
		 * 
		 * and data_str + data_color + data_int:
		 * 
		 * 		"tile 0 label": "Hello"
		 * 		"tile 0 color": #ffffff
		 * 		"tile 0 text-color": #000000
		 */
		if (parser.getName().equals("tile")) {
			try {
				int rang = Integer.parseInt(parser.getAttributeValue(null, "rang"));
				String label = parser.getAttributeValue(null, "label");
				int color = Color.parseColor(parser.getAttributeValue(null, "color"));
				int textColor = Color.parseColor(parser.getAttributeValue(null, "text-color"));
				
				String settStr = new String("tile " + rang);
				
				data_str.put(settStr + " label", label);
				data_color.put(settStr + " color", color);
				data_color.put(settStr + " text-color", textColor);
				
				// TODO: check SparseArray.indexOfKey()
				if ( tile_attributes.indexOfKey(rang) < 0 ) {
					tile_attributes.put(rang, new HashMap<String, String>());
				}
				
				tile_attributes.get(rang).put("label", settStr + " label");
				tile_attributes.get(rang).put("color", settStr + " color");
				tile_attributes.get(rang).put("text-color", settStr + " text-color");
				
			} catch ( IndexOutOfBoundsException e ) {
				
			}
		} else
			
		/**
		 * tile-default			
		 */
		if (parser.getName().equals("tile-default")) {
			int color = Color.parseColor(parser.getAttributeValue(null, "color"));
			int textcolor = Color.parseColor(parser.getAttributeValue(null, "text-color"));
			
			data_color.put("default-tile color", color);
			data_color.put("default-tile text-color", textcolor);
		}
		
		/**
		 * any other tag
		 */
		else {
			addTag(parser.getName(), parser.nextText());
		}
	}
	
	/**
	 * Any other tag
	 */
	private void addTag(String key, String val) {
		try {
			data_int.put(key, Integer.parseInt(val));
		} catch (NumberFormatException e) { }			

		try {
			data_color.put(key, Color.parseColor(val));
		} catch (IllegalArgumentException e) { }
		
		data_str.put(key, val);
	}
	
	/**
	 * Parse desiredSection from parser
	 */
	private void parseXml(XmlResourceParser parser,String desiredSection) throws XmlPullParserException, IOException {
		// some annoying parsing
		
		data_int = new HashMap<String, Integer>();
		data_str = new HashMap<String, String>();
		data_color = new HashMap<String, Integer>();
		tile_attributes = new SparseArray<Map<String,String>>();
		
		boolean sectionFound = false;
		
		// search <uisettings>
		while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() == XmlPullParser.START_TAG && 
            		parser.getName().contentEquals("uisettings")) 
            {
            	String settsId = parser.getAttributeValue(null, "id");
            	if (settsId.equals(desiredSection)) {
            		sectionFound = true;
            		parser.next();
            		break;
            	} else {
            		while (parser.getEventType() != XmlPullParser.END_TAG ||
            				parser.getName().contentEquals("uisettings"))
            			parser.next();
            	}
            }
            parser.next();
		}
		if (sectionFound) {
    		while ( !(parser.getEventType() == XmlPullParser.END_TAG &&
    				parser.getName().contentEquals("uisettings")) ) {
    			if (parser.getEventType() == XmlPullParser.START_TAG) {
    				addCurrentXmlTag(parser);
    			}
    			parser.next();
    		}
		}
		parser.close();
		
	}
}
