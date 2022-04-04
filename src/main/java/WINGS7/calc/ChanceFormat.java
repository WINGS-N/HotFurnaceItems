package WINGS7.calc;

public interface ChanceFormat {

	static double go(Integer chancein) {
		String chancepreout = "0." + chancein;
		if(chancein == 100) chancepreout = String.valueOf(1);
		return Double.parseDouble(chancepreout);
	}
}
