package com.crest.transform;

import com.crest.Document;
import junit.framework.TestCase;

import java.util.Collections;

public class UpperCaseTransformerTest extends TestCase {

    public static final String HELLO = "Hello world , hello";


    public void testUpperCaseTransformation() throws Exception {
        Transformer upperCaseTransformer = new UpperCaseTransformer();
        Document transformedDocument = upperCaseTransformer.transform().apply(new Document("testFileName", Collections.singletonList(HELLO)));
        assertEquals("Transformation did not happened", HELLO.toUpperCase(), transformedDocument.getContent().get(0) );

    }
}