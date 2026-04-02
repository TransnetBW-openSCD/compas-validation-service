package org.lfenergy.compas.validation.util;

import javax.xml.namespace.NamespaceContext;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SclNamespaceContext implements NamespaceContext {

    private static final String SCL_NAMESPACE_URI = "http://www.iec.ch/61850/2003/SCL";
    private static final String SCL_PREFIX = "scl";

    private static final Map<String, String> PREFIX_TO_URI = Map.of(
            SCL_PREFIX, SCL_NAMESPACE_URI,
            "xml", "http://www.w3.org/XML/1998/namespace"
    );

    private static final Map<String, String> URI_TO_PREFIX = Map.of(
            SCL_NAMESPACE_URI, SCL_PREFIX,
            "http://www.w3.org/XML/1998/namespace", "xml"
    );

    @Override
    public String getNamespaceURI(String prefix) {
        return PREFIX_TO_URI.getOrDefault(prefix, "");
    }

    @Override
    public String getPrefix(String namespaceURI) {
        return URI_TO_PREFIX.get(namespaceURI);
    }

    @Override
    public Iterator<String> getPrefixes(String namespaceURI) {
        String prefix = getPrefix(namespaceURI);
        return prefix != null ? List.of(prefix).iterator() : Collections.emptyIterator();
    }
}
