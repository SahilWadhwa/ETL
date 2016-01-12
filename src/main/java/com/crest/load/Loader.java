package com.crest.load;

import com.crest.Document;

import java.util.function.Consumer;

public interface Loader {

    Consumer<Document> load(String outputDestination);
}
