package de.isb.demo.service.impl;

import de.isb.demo.model.validation.ValidationResult;
import de.isb.demo.service.api.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {
    public ValidationResult validate(Object o) {
        return ValidationResult.ok();
    }
}
