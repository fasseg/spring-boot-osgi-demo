package de.isb.demo.model;

public class Visa {
    private String id;
    private VisaType type;
    private Applicant applicant;

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VisaType getType() {
        return type;
    }

    public void setType(VisaType type) {
        this.type = type;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
}
