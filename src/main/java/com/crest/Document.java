package com.crest;

import java.util.List;

import static java.util.Collections.emptyList;

public class Document {
    private final String name;
    private final List<String> content;

    public Document(String name, List<String> content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }
    public List<String> getContent() {
        return content;
    }

    public static Document emptyDocument() {
        return new Document("", emptyList());
    }
}
