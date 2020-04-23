package com.max.generator.util;

import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

@Log
@UtilityClass
public class Validator {
    public void checkParams(String[] args) {
        log.info(">> Validation params");
        if (args[0].isEmpty()) {
            throw new RuntimeException("File with sale points is empty!");
        }
        if (args[1].isEmpty() || Integer.parseInt(args[1]) < 1) {
            throw new RuntimeException("Incorrect value of count lines!");
        }
        if (args[2].isEmpty()) {
            throw new RuntimeException("No one file is not specified!");
        }
        log.info("<< All params are valid");
    }
}
