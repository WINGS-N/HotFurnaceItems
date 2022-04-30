package WINGS7N.providers.math;

public interface Chance {
	static Boolean go(double chance) {
	    return Math.random() <= chance;
	}
}
