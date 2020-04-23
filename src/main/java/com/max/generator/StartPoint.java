package com.max.generator;

import com.max.generator.service.RandomExecutor;
import lombok.extern.java.Log;

@Log
public class StartPoint {
    public static void main(String[] args) {
        log.info(">> Start program");
        RandomExecutor.execute(args);
        log.info("<< Finish program");
    }
}
