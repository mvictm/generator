package com.max.generator.service;

import com.max.generator.util.Validator;
import lombok.extern.java.Log;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log
public class RandomExecutor {
    private RandomExecutor() {
    }

    private static int countLines;
    private static String pathToSalePoints;
    private static List<String> fileNames;

    public static void execute(String[] args) {
        Validator.checkParams(args);
        initializeParams(args);
        fileNames.forEach(fileName -> new Thread(new Generator(pathToSalePoints, fileName, countLines)).start());
    }

    private static void initializeParams(String[] args) {
        log.info(">> Initialize parameter");
        pathToSalePoints = args[0];
        countLines = Integer.parseInt(args[1]);
        fileNames = Stream.of(args)
                .skip(2)
                .collect(Collectors.toList());
    }
}
