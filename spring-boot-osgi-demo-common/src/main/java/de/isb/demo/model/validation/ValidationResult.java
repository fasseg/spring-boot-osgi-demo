package de.isb.demo.model.validation;

import java.util.Map;

public class ValidationResult {
    private ValidationResultType result;
    private Map<String, Object> constraints;

    private ValidationResult() {

    }

    public ValidationResult(ValidationResultType result, Map<String, Object> contraints) {
        this.result = result;
        this.constraints = contraints;
    }

    public ValidationResultType getResult() {
        return result;
    }

    public void setResult(ValidationResultType result) {
        this.result = result;
    }

    public Map<String, Object> getConstraints() {
        return constraints;
    }

    public void setConstraints(Map<String, Object> constraints) {
        this.constraints = constraints;
    }

    public static ValidationResult ok() {
        return new ValidationResult(ValidationResultType.VALIDATION_SUCCESSFUL, null);
    }

    public static ValidationResult error(Map<String, Object> constraints) {
        return new ValidationResult(ValidationResultType.VALIDATION_ERROR, constraints);
    }
}
