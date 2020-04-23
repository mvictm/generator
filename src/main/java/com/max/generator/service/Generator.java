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
public class Generator {
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
                .parallel()
                .limit(count)
                .forEach(string -> writeToFile(fileName, string));
    }

    private String getRandomText() {
        return new StringJoiner(" ")
                .add(RandomTimeGenerator.getRandomDate())
                .add(RandomTimeGenerator.getRandomTime())
                .add(salePoints.getRandomSalePoint())
                .add(String.valueOf(counter.incrementAndGet()))
                .add(RandomAmountGenerator.getRandomAmount())
                .add("\n").toString();
    }

    private void writeToFile(String fileName, String data) {
        try {
            Files.write(Paths.get(fileName), data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + e);
        }
    }
}
