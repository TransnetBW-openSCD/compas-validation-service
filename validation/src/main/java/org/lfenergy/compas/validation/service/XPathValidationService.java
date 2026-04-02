package org.lfenergy.compas.validation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;


import jakarta.ws.rs.InternalServerErrorException;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XPathSelector;
import net.sf.saxon.s9api.XdmItem;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XdmValue;
import org.jboss.logmanager.Level;
import org.lfenergy.compas.validation.exception.CompasInvalidInputException;
import org.lfenergy.compas.validation.model.ValidationInput;
import org.lfenergy.compas.validation.model.ValidationResult;
import org.lfenergy.compas.validation.model.ValidationError;

import javax.xml.transform.stream.StreamSource;

import org.lfenergy.compas.validation.xpath.CompiledXPathRule;
import org.lfenergy.compas.validation.xpath.XPathRule;
import org.lfenergy.compas.validation.xpath.XPathRuleCompiler;

import java.io.StringReader;
import java.util.logging.Logger;

@ApplicationScoped
public class XPathValidationService {

    private static final Logger LOG = Logger.getLogger(XPathValidationService.class.getName());

    public ValidationResult validate(ValidationInput input) {
        LOG.log(Level.INFO, "Starting XPath validation");

        if (input == null
            || input.getSclContent().isBlank()
            || input.getRulesContent() == null
            || input.getRulesContent().isBlank()) {
            throw new CompasInvalidInputException("No XPATH rules or SCL content provided for validation");
        }

        var result = new ValidationResult();
        var processor = new Processor(false);
        var builder = processor.newDocumentBuilder();
        builder.setLineNumbering(true);

        var ruleCompiler = new XPathRuleCompiler(processor);

        try {
            var sclContent = input.getSclContent().trim();
            var document = builder.build(new StreamSource(new StringReader(sclContent)));

            LOG.log(Level.DEBUG,"Parsing XPATH rules: {}", input.getRulesContent());
            var xpathRules = new ObjectMapper().readValue(input.getRulesContent().trim(), XPathRule[].class);

            for (XPathRule xPathRule : xpathRules) {
                CompiledXPathRule compiledRule = ruleCompiler.compileRule(xPathRule);

                // Evaluate the context to find the relevant nodes
                XPathSelector contextSelector = compiledRule.getContextExecutable().load();
                contextSelector.setContextItem(document);
                XdmValue contextNodes = contextSelector.evaluate();

                if (contextNodes.isEmptySequence()) {
                    result.addError(new ValidationError(
                            "xpath rule",
                            "No context nodes found for: " + compiledRule.getRule().getContext(),
                            compiledRule.getRule().toString(),
                            "ERROR",
                            null
                    ));
                    return result;
                }

                for (XdmItem item : contextNodes) {
                    if (item instanceof XdmNode node) {
                        // Evaluate assertion for each relevant node
                        evaluateAssertionOnContextNodes(node, compiledRule, result);
                    }
                }
            }
        } catch (SaxonApiException | JsonProcessingException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
        return result;
    }

    private void evaluateAssertionOnContextNodes(XdmNode contextNode,
                                                 CompiledXPathRule compiledRule,
                                                 ValidationResult result)
            throws SaxonApiException {

        XPathSelector assertionSelector = compiledRule.getAssertionExecutable().load();
        assertionSelector.setContextItem(contextNode);
        boolean passed = assertionSelector.effectiveBooleanValue();

        if (!passed) {
//            LOG.log(Level.ERROR, "XPath rule violated: {}", compiledRule.getRule());
            result.addError(new ValidationError(
                    "xpath rule",
                    "XPath rule violated: " + compiledRule.getRule(),
                    compiledRule.getRule().toString(),
                    "ERROR",
                    contextNode.getLineNumber()
            ));
        }
    }
}
