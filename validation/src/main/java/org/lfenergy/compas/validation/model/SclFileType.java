package org.lfenergy.compas.validation.model;

public enum SclFileType {
    ICD,
    SCD,
    SSD,
    CID,
    IID,
    SED;

    public String extension() {
        return name().toLowerCase();
    }
}
