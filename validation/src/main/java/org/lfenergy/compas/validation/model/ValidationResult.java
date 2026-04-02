package org.lfenergy.compas.validation.model;

import java.util.ArrayList;
import java.util.List;


public class ValidationResult {

    private List<ValidationError> errors = new ArrayList<>();
    private boolean valid;

    public ValidationResult() {
        this.valid = true;
    }


    public void addError(ValidationError error) {
        this.errors.add(error);
        this.valid = false;
    }


    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
        this.valid = errors.isEmpty();
    }

    public boolean isValid() {
        return valid;
    }
}
