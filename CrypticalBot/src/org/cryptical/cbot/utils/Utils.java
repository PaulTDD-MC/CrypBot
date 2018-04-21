package org.cryptical.cbot.utils;

import java.awt.Color;

public class Utils {
	
	public static Color toColor(String color) {
		if (color.equalsIgnoreCase("black")) return Color.BLACK;
		if (color.equalsIgnoreCase("blue")) return Color.BLUE;
		if (color.equalsIgnoreCase("cyan")) return Color.CYAN;
		if (color.equalsIgnoreCase("dark_gray")) return Color.DARK_GRAY;
		if (color.equalsIgnoreCase("gray")) return Color.GRAY;
		if (color.equalsIgnoreCase("green")) return Color.GREEN;
		if (color.equalsIgnoreCase("light_gray")) return Color.LIGHT_GRAY;
		if (color.equalsIgnoreCase("magenta")) return Color.MAGENTA;
		if (color.equalsIgnoreCase("orange")) return Color.ORANGE;
		if (color.equalsIgnoreCase("pink")) return Color.PINK;
		if (color.equalsIgnoreCase("red")) return Color.RED;
		if (color.equalsIgnoreCase("white")) return Color.WHITE;
		if (color.equalsIgnoreCase("yellow")) return Color.YELLOW;
		return null;
	}
	
	public static String fromColor(Color color) {
		if (color.equals(Color.BLACK)) return "Black";
		if (color.equals(Color.BLUE)) return "Blue";
		if (color.equals(Color.CYAN)) return "Cyan";
		if (color.equals(Color.DARK_GRAY)) return "Dark Gray";
		if (color.equals(Color.GRAY)) return "Gray";
		if (color.equals(Color.GREEN)) return "Green";
		if (color.equals(Color.LIGHT_GRAY)) return "Light Gray";
		if (color.equals(Color.MAGENTA)) return "Magenta";
		if (color.equals(Color.ORANGE)) return "Orange";
		if (color.equals(Color.PINK)) return "Pink";
		if (color.equals(Color.RED)) return "Red";
		if (color.equals(Color.WHITE)) return "White";
		if (color.equals(Color.YELLOW)) return "Yellow";
		return "none";
	}
	
	public static String toId(String string) {
		return string.replace("<", "").replace(">", "").replace("!", "").replace("@", "").replace("#", "");
	}
}
