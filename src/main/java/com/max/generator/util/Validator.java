package com.max.generator.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

import java.nio.file.Files;
import java.nio.file.Paths;

@Log
@UtilityClass
public class Validator {
    @SneakyThrows
    public void checkParams(String[] args) {
        log.info(">> Validation params");
        if (args.length < 3) {
            throw new RuntimeException("Not enough parameters.");
        }
        if (!Files.exists(Paths.get(args[0])) || Files.size(Paths.get(args[0])) == 0) {
            throw new RuntimeException("File " + args[0] + " is not exist or empty");
        }
        if (Integer.parseInt(args[1]) < 1) {
            throw new RuntimeException("Incorrect value of count lines!");
        }
        log.info("<< All params are valid");
    }
}
