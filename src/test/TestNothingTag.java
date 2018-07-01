package test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class TestNothingTag {
	public static void main(String[] args) {
		BigDecimal includeTaxAmount = new BigDecimal("119842.21");
		BigDecimal j = new BigDecimal("0.027500");
		BigDecimal k = includeTaxAmount.multiply(j);
		includeTaxAmount = includeTaxAmount.add(k).setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println("includeTaxAmount===" + includeTaxAmount);
		BigDecimal a = new BigDecimal("54699.5276250").setScale(3, BigDecimal.ROUND_HALF_UP);
		BigDecimal b = new BigDecimal("54699.5276250").setScale(3, BigDecimal.ROUND_HALF_UP);
		BigDecimal c = new BigDecimal("1269.5276250").setScale(3, BigDecimal.ROUND_HALF_UP);
		BigDecimal d = new BigDecimal("12469.2879000").setScale(3, BigDecimal.ROUND_HALF_UP);
		BigDecimal up = a.add(b).add(c).add(d);
		up = up.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println("upcludeTaxAmount===" + up);

	}

}
