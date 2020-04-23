package com.max.generator.service;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class SalePoints {
    private final List<Integer> points;

    public String getRandomSalePoint() {
        return String.valueOf(points.get(new Random().nextInt(points.size())));
    }

    @SneakyThrows
    public SalePoints(String pathToSalePoints) {
        points = Files.lines(Paths.get(pathToSalePoints))
                .filter(Objects::nonNull)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
