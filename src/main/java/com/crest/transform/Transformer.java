package com.crest.transform;

import com.crest.Document;

import java.util.function.Function;

public interface Transformer {

    Function<Document, Document> transform();
}
