package com.max.generator.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class RandomTimeGenerator {
    private RandomTimeGenerator() {
    }

    private static final int START_SECONDS = LocalTime.MIN.toSecondOfDay();
    private static final int END_SECONDS = LocalTime.MAX.toSecondOfDay();
    private static final long START_EPOCH_DAY = LocalDate.now().minusYears(1).withDayOfYear(1).withMonth(1).toEpochDay();
    private static final long END_EPOCH_DAY = LocalDate.now().toEpochDay();

    public static String getRandomTime() {
        return LocalTime.ofSecondOfDay(ThreadLocalRandom.current().nextInt(START_SECONDS, END_SECONDS)).format(DateTimeFormatter.ofPattern("hh:mm"));
    }

    public static String getRandomDate() {
        return LocalDate.ofEpochDay(ThreadLocalRandom.current().nextLong(START_EPOCH_DAY, END_EPOCH_DAY)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
