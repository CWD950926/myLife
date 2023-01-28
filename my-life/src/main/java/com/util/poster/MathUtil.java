package com.util.poster;

import java.math.BigDecimal;

public class MathUtil {

    public static double divide(int a, int b, int scale) {
        return new BigDecimal((float) a / b).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double divide(int a, int b) {
        return new BigDecimal((float) a / b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static BigDecimal divideBigDecimal(int a, int b) {
        return new BigDecimal((float) a / b).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal divideBigDecimal2(BigDecimal a, int b) {
        BigDecimal result = a.divide(BigDecimal.valueOf(b)).setScale(2, BigDecimal.ROUND_HALF_UP);
        return result;
    }

    public static String bigDecimal2Str(BigDecimal bigDecimal, int scale) {
        return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_EVEN).stripTrailingZeros().toPlainString();
    }

}
