package com.connect.accountApp.global.utils;

import java.math.BigDecimal;

public class NvlUtils {

    public static BigDecimal nvl(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return BigDecimal.ZERO;
        }
        return bigDecimal;
    }
}
