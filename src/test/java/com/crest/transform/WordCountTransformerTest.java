package com.crest.transform;

import com.crest.Document;
import junit.framework.TestCase;

import java.util.Arrays;

public class WordCountTransformerTest extends TestCase{

    public static final String HELLO = "Hello world , hello";

    public void testWordCountTransformation() throws Exception {
        Transformer wordCountTransformer = new WordCountTransformer();
        Document transformedDocument = wordCountTransformer.transform().apply(new Document("testFileName", Arrays.asList(HELLO)));
        assertTrue("Word Count for 'hello' Transformation did not happened", transformedDocument.getContent().contains("hello -> 2") );
        assertTrue("Word Count for 'world' Transformation did not happened", transformedDocument.getContent().contains("world -> 1") );

    }
}