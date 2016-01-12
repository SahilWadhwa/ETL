package com.crest.transform;

import com.crest.Document;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class WordCountTransformer implements Transformer {

    public static final String SPACE_REGEX = " ";
    public static final String IN_FIX = " -> ";

    @Override
    public Function<Document, Document> transform() {
        return d -> new Document(d.getName(), getStringCountFor(
                d.getContent().stream()
                        .flatMap(Pattern.compile(SPACE_REGEX)::splitAsStream).map(String::toLowerCase).collect(toList())
        ));
    }

    private static List<String> getStringCountFor(List<String> strings) {
        Map<String, Long> collect = strings.stream().collect(groupingBy(s -> s, Collectors.counting()));
        return collect.keySet().stream().map(key -> key + IN_FIX + collect.get(key)).collect(toList());
    }
}
