package WINGS7.calc;

public interface Chance {
	static Boolean go(double chance) {
	    return Math.random() <= chance;
	}
}
