package org.lfenergy.compas.validation.resource;

import io.quarkus.security.Authenticated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import org.lfenergy.compas.validation.mapper.ValidationMapper;
import org.lfenergy.compas.validation.rest.api.ValidationApi;
import org.lfenergy.compas.validation.rest.api.dto.ValidationResponse;
import org.lfenergy.compas.validation.service.XPathValidationService;

import java.io.InputStream;


@Authenticated
@RequestScoped
public class ValidationResource implements ValidationApi {

    @Inject
    XPathValidationService xPathValidationService;

    @Override
    public ValidationResponse validateSclUpload(
                                                InputStream sclFileInputStream,
                                                String sclFileExtension,
                                                InputStream rulesFileInputStream,
                                                String rulesJson) {
        var input = ValidationMapper.toInput(
                sclFileInputStream,
                rulesFileInputStream,
                rulesJson,
                sclFileExtension
        );
        var result = xPathValidationService.validate(input);
        return ValidationMapper.toResponse(result);
    }
}
