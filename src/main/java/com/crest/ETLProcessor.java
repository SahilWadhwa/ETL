package com.crest;

import com.crest.extract.Extractor;
import com.crest.extract.FileExtractor;
import com.crest.load.FileLoader;
import com.crest.load.Loader;
import com.crest.transform.Transformer;
import com.crest.transform.UpperCaseTransformer;

public class ETLProcessor {

    public static final String INPUT = "./Input";
    public static final String OUTPUT = "./Output";

    public static void process(Extractor extractor, String extractSource, Transformer transformer, Loader loader, String outputDestination) throws Exception {
        extractor.extract(extractSource).get().map(transformer.transform()).forEach(loader.load(outputDestination));
    }

    public static void main( String[] args ) throws Exception {
        Extractor fileExtractor = new FileExtractor();
        Transformer transformer = new UpperCaseTransformer();
        Loader fileLoader = new FileLoader();

        ETLProcessor.process(fileExtractor, INPUT,transformer,fileLoader, OUTPUT);
    }
}

