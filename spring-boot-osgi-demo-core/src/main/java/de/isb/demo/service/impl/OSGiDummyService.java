package de.isb.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OSGiDummyService {
    private static final Logger log = LoggerFactory.getLogger(OSGiDummyService.class);
    void doStuff() {
        log.info("Doing stuff");
    }
}
