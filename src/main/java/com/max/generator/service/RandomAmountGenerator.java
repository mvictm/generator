package com.max.generator.service;

import java.math.BigDecimal;

public class RandomAmountGenerator {
    private RandomAmountGenerator() {
    }

    private static final BigDecimal MIN_AMOUNT = new BigDecimal("10000.12");
    private static final BigDecimal MAX_AMOUNT = new BigDecimal("100000.50");

    public static String getRandomAmount() {
        return MIN_AMOUNT.add(BigDecimal.valueOf(Math.random())
                .multiply(MAX_AMOUNT.subtract(MIN_AMOUNT)))
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .toString();
    }
}
