package com.crest;

import com.crest.extract.Extractor;
import com.crest.extract.FileExtractor;
import com.crest.load.ConsoleLoader;
import com.crest.load.FileLoader;
import com.crest.load.Loader;
import com.crest.transform.Transformer;
import com.crest.transform.UpperCaseTransformer;
import com.crest.transform.WordCountTransformer;
import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ETLProcessorTest extends TestCase {

    public static final String INPUT_FOLDER = "./Input";
    public static final String OUTPUT_FOLDER = "./Output";
    public static final String TEST_FILE = "/test.txt";
    public static final String TEST_CONTENT = "I wish to wish the wish you wish to wish , but if you wish the wish the witch wishes, I won't wish the wish you wish to wish";



    private Extractor fileExtractor = new FileExtractor();
    private Transformer transformer = new UpperCaseTransformer();
    private Loader fileLoader = new FileLoader();



    public void testETLWithToUpperCaseTransformation() throws Exception {

        ETLProcessor.process(fileExtractor, INPUT_FOLDER,transformer,fileLoader,OUTPUT_FOLDER);

        String outputContent = Files.lines(Paths.get(OUTPUT_FOLDER + TEST_FILE)).reduce((s, a) -> s + " " + a).get();
        String inputContent = Files.lines(Paths.get(INPUT_FOLDER + TEST_FILE)).reduce((s, a) -> s + " " + a).get();

        assertEquals("Upper case conversion didn't happened", inputContent.toUpperCase(), outputContent);

    }

    public void testETLWithWordCountTransformation() throws Exception {

        transformer = new WordCountTransformer();

        ETLProcessor.process(fileExtractor, INPUT_FOLDER,transformer,fileLoader,OUTPUT_FOLDER);

        String outputContent = Files.lines(Paths.get(OUTPUT_FOLDER + TEST_FILE)).reduce((s, a) -> s + " " + a).get();
        String inputContent = Files.lines(Paths.get(INPUT_FOLDER + TEST_FILE)).reduce((s, a) -> s + " " + a).get();

        assertEquals("Input content Got Changed !! ", inputContent, TEST_CONTENT);
        assertTrue("word count for 'wish' calculated incorrectly", outputContent.contains("wish -> 11"));
        assertTrue("word count for 'witch' calculated incorrectly", outputContent.contains("witch -> 1"));
        assertTrue("word count for 'the' calculated incorrectly", outputContent.contains("the -> 4"));

    }

    public void testRunETLWithConsoleOutput() throws Exception {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        fileLoader = new ConsoleLoader();
        transformer = new UpperCaseTransformer();
        ETLProcessor.process(fileExtractor, INPUT_FOLDER,transformer,fileLoader,OUTPUT_FOLDER);

        assertTrue("Incorrect Console Output", outputStream.toString().contains(TEST_CONTENT.toUpperCase()));

    }
}