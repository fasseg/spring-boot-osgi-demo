package de.isb.demo.model.rest;

import java.time.ZonedDateTime;

public class RestResponse {
    private ZonedDateTime timestamp;

    public RestResponse() {
        timestamp = ZonedDateTime.now();
    }
}
