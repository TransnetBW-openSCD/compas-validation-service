package org.lfenergy.compas.validation.model;

public class ValidationInput {

    private final String sclContent;
    private final String rulesContent;
    private final SclFileType fileType;

    public ValidationInput(String sclContent, String rulesContent) {
        this(sclContent, rulesContent, null);
    }

    public ValidationInput(String sclContent, String rulesContent,  SclFileType fileType) {
        this.sclContent = sclContent;
        this.rulesContent = rulesContent;
        this.fileType = fileType;
    }

    public String getSclContent() {
        return sclContent;
    }

    public String getRulesContent() {
        return rulesContent;
    }

    public SclFileType getFileType() {
        return fileType;
    }
}
