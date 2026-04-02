package org.lfenergy.compas.validation.mapper;

import java.io.IOException;
import java.io.InputStream;

import org.lfenergy.compas.validation.exception.CompasInvalidInputException;
import org.lfenergy.compas.validation.model.SclFileType;
import org.lfenergy.compas.validation.model.ValidationInput;
import org.lfenergy.compas.validation.model.ValidationResult;
import org.lfenergy.compas.validation.rest.api.dto.ValidationError;
import org.lfenergy.compas.validation.rest.api.dto.ValidationResponse;

public class ValidationMapper {

    private ValidationMapper() {
    }


    public static ValidationInput toInput(InputStream sclFileInputStream,
                                          InputStream rulesFileInputStream,
                                          String rules,
                                          String sclFileExtension) {
        try (InputStream sclIn = sclFileInputStream;
             InputStream rulesIn = rulesFileInputStream) {

            SclFileType fileType = sclFileExtension != null ? SclFileType.valueOf(sclFileExtension) : SclFileType.SCD;
            String sclContent = new String(sclIn.readAllBytes());
            String rulesContent = rulesIn != null ? new String(rulesIn.readAllBytes()) : rules;

            return new ValidationInput(
                    sclContent,
                    rulesContent,
                    fileType
            );
        } catch (IllegalArgumentException | IOException e) {
            throw new CompasInvalidInputException("Invalid validation type or file extension.");
        } catch (NullPointerException e) {
            throw new CompasInvalidInputException("SCL file is required for validation.");
        }
    }

    public static ValidationResponse toResponse(ValidationResult result) {
        var errors = result.getErrors().stream()
                .map(ValidationMapper::toErrorResponse)
                .toList();

        var response = new ValidationResponse();
        response.setErrors(errors);
        response.setValid(result.isValid());
        return response;
    }

    private static ValidationError toErrorResponse(org.lfenergy.compas.validation.model.ValidationError error) {
        var dto = new ValidationError();
        dto.setRuleName(error.getRuleName());
        dto.setMessage(error.getMessage());
        dto.setXpath(error.getXpath());
        if (error.getSeverity() != null) {
            try {
                dto.setSeverity(ValidationError.SeverityEnum.fromValue(error.getSeverity()));
            } catch (IllegalArgumentException ignored) {
                dto.setSeverity(ValidationError.SeverityEnum.ERROR);
            }
        }
        dto.setLineNumber(error.getLineNumber());
        return dto;
    }
}
