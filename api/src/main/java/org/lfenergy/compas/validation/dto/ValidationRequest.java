package org.lfenergy.compas.validation.dto;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public class ValidationRequest {

    @RestForm
    public FileUpload sclFile;

    @RestForm
    public FileUpload rulesFile;

    @RestForm
    public String rules;
}
