package org.lfenergy.compas.validation.xpath;

import net.sf.saxon.s9api.XPathExecutable;

public class CompiledXPathRule {
    private XPathRule rule;
    private XPathExecutable contextExecutable;
    private XPathExecutable assertionExecutable;

    public CompiledXPathRule(XPathRule rule,
                             XPathExecutable contextExecutable,
                             XPathExecutable assertionExecutable) {
        this.rule = rule;
        this.contextExecutable = contextExecutable;
        this.assertionExecutable = assertionExecutable;
    }

    public XPathRule getRule() {
        return rule;
    }

    public void setRule(XPathRule rule) {
        this.rule = rule;
    }

    public XPathExecutable getContextExecutable() {
        return contextExecutable;
    }

    public void setContextExecutable(XPathExecutable contextExecutable) {
        this.contextExecutable = contextExecutable;
    }

    public XPathExecutable getAssertionExecutable() {
        return assertionExecutable;
    }

    public void setAssertionExecutable(XPathExecutable assertionExecutable) {
        this.assertionExecutable = assertionExecutable;
    }
}
