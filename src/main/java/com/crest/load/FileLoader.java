package com.crest.load;

import com.crest.Document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;

import static java.nio.file.StandardOpenOption.*;

public class FileLoader implements Loader {

    @Override
    public Consumer<Document> load(String outputDir) {
        try {
            org.apache.commons.io.FileUtils.cleanDirectory(new File(outputDir));
        } catch (Exception e) {
            System.out.println("Output dir not present");
        }
        return d -> writeTo(outputDir + "/" + d.getName(), d.getContent());
    }

    public static void writeTo(String filename, List<String> content) {
        try {
            Files.write(Paths.get(filename),
                    (Iterable<String>) content.stream()::iterator,
                    CREATE, WRITE, APPEND);
        } catch (IOException e) {
            System.out.println("Unable to write content to file");
        }
    }
}
