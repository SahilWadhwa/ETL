package com.crest.load;

import com.crest.Document;

import java.util.function.Consumer;

public class ConsoleLoader implements Loader {
    @Override
    public Consumer<Document> load(String outputDestination) {
        return document -> {
            System.out.println(document.getName());
            document.getContent().stream().forEach(System.out::println);
        };
    }
}
