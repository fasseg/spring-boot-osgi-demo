package de.isb.demo.model.rest;

public class AboutResponse extends RestResponse {
    private final String applicationName;
    private final String applicationVersion;

    public AboutResponse(String applicationName, String applicationVersion) {
        this.applicationName = applicationName;
        this.applicationVersion = applicationVersion;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }
}
