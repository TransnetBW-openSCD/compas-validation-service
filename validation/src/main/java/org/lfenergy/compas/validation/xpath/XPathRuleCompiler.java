package org.lfenergy.compas.validation.xpath;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.UnprefixedElementMatchingPolicy;
import net.sf.saxon.s9api.XPathCompiler;
import net.sf.saxon.s9api.XPathExecutable;

public class XPathRuleCompiler {

    private final XPathCompiler compiler;

    public XPathRuleCompiler(Processor processor) {
        this.compiler = processor.newXPathCompiler();
        this.compiler.setUnprefixedElementMatchingPolicy(UnprefixedElementMatchingPolicy.ANY_NAMESPACE);
    }

    public CompiledXPathRule compileRule(XPathRule rule) throws SaxonApiException {
        XPathExecutable contextExec = compiler.compile(rule.getContext());
        XPathExecutable assertionExec = compiler.compile(rule.getAssertion());
        return new CompiledXPathRule(rule, contextExec, assertionExec);
    }
}
