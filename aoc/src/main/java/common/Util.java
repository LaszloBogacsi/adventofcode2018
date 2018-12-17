package main.java.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Util {
    public static List<String> readfile(String path) {
        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            lines = stream.collect(toList());
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
