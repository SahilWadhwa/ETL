package com.crest.extract;

import com.crest.Document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.crest.Document.emptyDocument;

public class FileExtractor implements Extractor {

    @Override
    public Supplier<Stream<Document>> extract(String source) {
        return () -> {
            try {
                return FileExtractor.getDocumentStreamFrom(source);
            } catch (Exception e) {
                return Stream.of();
            }
        };

    }

    private static Stream<Document> getDocumentStreamFrom(String dirPath) throws IOException {

        return Files.list(new File(dirPath).toPath()).map(path -> {
            try {
                return new Document(path.getFileName().toString(), Files.readAllLines(path));
            } catch (IOException e) {
                return emptyDocument();
            }
        });
    }
}
