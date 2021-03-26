package io.WINGS.calc;

public interface ChanceFormat {

	public static double go(Integer chancein) {
		String chancepreout = "0." + chancein;
		if(chancein == 100) chancepreout = String.valueOf(1);
		double chanceout = Double.valueOf(chancepreout);
		return chanceout;
	}
}
