package model;

import java.awt.Color;

public class ColorConvert {
	
	public static Color getColor(String value) {
		if (value == null) {
			return Color.black;
		}
		try {
			return Color.decode(value);
		} catch (NumberFormatException nfe) {
			try {
				final java.lang.reflect.Field f = Color.class.getField(value);

				return (Color) f.get(null);
			} catch (Exception ce) {
				return Color.black;
			}
		}
	}
}

