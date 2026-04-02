package org.lfenergy.compas.validation.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.lfenergy.compas.validation.model.ValidationInput;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
public class XPathValidationServiceTest {

    private static final String XPATH_RULES = """
        [
            {"context": "/*[local-name()='SCL']", "assertion": "count(*[local-name()='Substation']) = 2"},
            {"context": "/*[local-name()='SCL']", "assertion": "*[local-name()='Substation' and @name='Substation Eins']"}
        ]
    """;

    private static final String SCD_WITH_2_SUBSTATIONS = """
        <?xml version="1.0" encoding="UTF-8"?>
        <SCL xmlns="http://www.iec.ch/61850/2003/SCL" version="2007" revision="B" release="4" xmlns:compas="https://www.lfenergy.org/compas/extension/v1">
            <Private type="compas_scl">
                <compas:Labels/>
            </Private>
            <Header id="project"/>
            <Substation name="Substation Eins1" desc=""/>
            <Substation name="Substation Eins" desc=""/>
        </SCL>
    """;

    private static final String SCD_WITH_1_SUBSTATION = """
        <?xml version="1.0" encoding="UTF-8"?>
        <SCL xmlns="http://www.iec.ch/61850/2003/SCL" version="2007" revision="B" release="4" xmlns:compas="https://www.lfenergy.org/compas/extension/v1">
            <Private type="compas_scl">
                <compas:Labels/>
            </Private>
            <Header id="project"/>
            <Substation name="Substation Eins1" desc=""/>
        </SCL>
    """;

    @Inject
    XPathValidationService xPathValidationService;

    @Test
    void shouldReturnValidResultForXPathValidation() {
        var input = new ValidationInput(SCD_WITH_2_SUBSTATIONS.trim(), XPATH_RULES.trim());
        var result = xPathValidationService.validate(input);

        assertNotNull(result);
        assertTrue(result.isValid());
        assertTrue(result.getErrors().isEmpty());
    }

    @Test
    void shouldReturnInvalidResultForXPathValidation() {
        var input = new ValidationInput(SCD_WITH_1_SUBSTATION.trim(), XPATH_RULES.trim());
        var result = xPathValidationService.validate(input);

        assertNotNull(result);
        assertFalse(result.isValid());
        assertFalse(result.getErrors().isEmpty());
        assertEquals("ERROR", result.getErrors().getFirst().getSeverity());
    }

//    @Test
//    void shouldReportLineNumbersForNodeSelectingRules() {
//        var rules = """
//        [
//            {"context": "//", "assertion": "not(Substation[@name=''])"}
//        ]
//        """;
//        var scl = """
//            <?xml version="1.0" encoding="UTF-8"?>
//            <SCL xmlns="http://www.iec.ch/61850/2003/SCL">
//                <Substation name="Valid"/>
//                <Substation name=""/>
//            </SCL>
//        """;
//        var result = orchestrator.validate(input);
//
//        assertFalse(result.isValid());
//        assertEquals(1, result.getErrors().size());
//        assertNotNull(result.getErrors().getFirst().getLineNumber());
//        assertEquals(4, result.getErrors().getFirst().getLineNumber());
//    }

}
