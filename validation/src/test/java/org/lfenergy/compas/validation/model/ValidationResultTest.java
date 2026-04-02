package org.lfenergy.compas.validation.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ValidationResultTest {

    @Test
    void shouldBeValidWhenNoErrors() {
        var result = new ValidationResult();

        assertTrue(result.isValid());
        assertTrue(result.getErrors().isEmpty());
    }

    @Test
    void shouldBeInvalidWhenErrorAdded() {
        var result = new ValidationResult();
        result.addError(new ValidationError("rule1", "Element missing", "//SCL/Header", "ERROR", 5));

        assertFalse(result.isValid());
        assertEquals(1, result.getErrors().size());
        assertEquals("rule1", result.getErrors().getFirst().getRuleName());
    }
}
