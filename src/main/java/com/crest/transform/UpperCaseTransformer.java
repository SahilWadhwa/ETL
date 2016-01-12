package com.crest.transform;

import com.crest.Document;

import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class UpperCaseTransformer implements Transformer {


    @Override
    public Function<Document, Document> transform() {
        return document -> new Document(document.getName(), document.getContent().stream().map(String::toUpperCase).collect(toList()));
    }
}
