package io.WINGS.calc;

public interface Chance {
	public static Boolean go(double chance) {
	    return Math.random() <= chance;
	}
}
