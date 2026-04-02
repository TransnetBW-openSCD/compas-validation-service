package org.lfenergy.compas.validation.model;

public class ValidationError {

    private String ruleName;
    private String message;
    private String xpath;
    private String severity;
    private Integer lineNumber;

    public ValidationError() {
    }

    public ValidationError(String ruleName, String message, String xpath, String severity) {
        this(ruleName, message, xpath, severity, null);
    }

    public ValidationError(String ruleName, String message, String xpath, String severity, Integer lineNumber) {
        this.ruleName = ruleName;
        this.message = message;
        this.xpath = xpath;
        this.severity = severity;
        this.lineNumber = lineNumber;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }
}
