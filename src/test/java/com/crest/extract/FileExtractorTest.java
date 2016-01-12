package com.crest.extract;

import com.crest.Document;
import junit.framework.TestCase;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class FileExtractorTest extends TestCase {

    public void testExtractTextFileIntoDocument() throws Exception {
        FileExtractor fileExtractor = new FileExtractor();
        Supplier<Stream<Document>> extract = fileExtractor.extract("./Input");
        extract.get().findFirst().ifPresent(document -> assertTrue(document.getName().equals("test.txt")));

    }
}