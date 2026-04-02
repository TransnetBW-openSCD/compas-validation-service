package org.lfenergy.compas.validation.xpath;

public class XPathRule {
    
    private String context;
    private String assertion;

    public XPathRule() {
    }

    public XPathRule(String context, String assertion) {
        this.context = context;
        this.assertion = assertion;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getAssertion() {
        return assertion;
    }

    public void setAssertion(String assertion) {
        this.assertion = assertion;
    }

    @Override
    public String toString() {
        return "{context='" + context + "'" + ", assertion='" + assertion + "'}";
    }
}
