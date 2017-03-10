package de.isb.demo.service.api;

import de.isb.demo.model.validation.ValidationResult;

public interface ValidationService {
    ValidationResult validate(Object o);
}
