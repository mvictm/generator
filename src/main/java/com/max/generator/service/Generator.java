package com.max.generator.service;

import lombok.extern.java.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Log
public class Generator implements Runnable {
    private final SalePoints salePoints;
    private final AtomicInteger counter;
    private final String fileName;
    private final int count;

    public Generator(String pathToSalePoints, String fileName, int count) {
        this.fileName = fileName;
        this.count = count;
        counter = new AtomicInteger(0);
        salePoints = new SalePoints(pathToSalePoints);
    }

    public void generate() {
        Stream.generate(this::getRandomText)
                .limit(count)
                .forEach(string -> writeToFile(fileName, string));
    }

    private String getRandomText() {
        StringJoiner stringJoiner = new StringJoiner(" ");
        stringJoiner.add(RandomTimeGenerator.getRandomDate());
        stringJoiner.add(RandomTimeGenerator.getRandomTime());
        stringJoiner.add(salePoints.getRandomSalePoint());
        stringJoiner.add(String.valueOf(counter.incrementAndGet()));
        stringJoiner.add(RandomAmountGenerator.getRandomAmount());
        stringJoiner.add("\n");

        return stringJoiner.toString();
    }

    private void writeToFile(String fileName, String data) {
        try {
            Files.write(Paths.get(fileName), data.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + e);
        }
    }

    @Override
    public void run() {
        log.info(">> " + Thread.currentThread().getName() + " start to generate lines");
        generate();
        log.info("<< " + Thread.currentThread().getName() + " generate " + counter.get() + " lines");
    }
}
