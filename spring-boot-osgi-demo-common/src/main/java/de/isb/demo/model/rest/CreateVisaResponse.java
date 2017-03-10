package de.isb.demo.model.rest;

public class CreateVisaResponse extends RestResponse {
    private final String id;

    public CreateVisaResponse(String id) {
        this.id = id;
    }

    public static CreateVisaResponse ok(String id) {
        return new CreateVisaResponse(id);
    }

    public String getId() {
        return id;
    }
}
