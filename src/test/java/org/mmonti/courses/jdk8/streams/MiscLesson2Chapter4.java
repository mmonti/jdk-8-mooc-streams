package org.mmonti.courses.jdk8.streams;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.MAX_VALUE;
import static java.nio.file.Files.*;
import static java.nio.file.Paths.get;
import static org.junit.Assert.assertEquals;

/**
 * Created by mmonti on 9/16/15.
 */
public class MiscLesson2Chapter4 {

    @Test
    public void findTest() throws IOException {
        Stream<Path> stream = find(get("."), MAX_VALUE, (path, basicFileAttributes) ->
                path.getFileName().toString().equals("MiscLesson2Chapter4.java")
        );
        assertEquals(1, stream.distinct().count());
    }

    @Test
    public void listTest() throws IOException {
        Stream<Path> stream = list(get("./src/test/java/org/mmonti/courses/jdk8"));
        assertEquals(1, stream.count());
    }

    @Test
    public void linesTest() throws IOException {
        Stream<String> stream = lines(get("./src/test/java/org/mmonti/courses/jdk8/streams/MiscLesson2Chapter4.java"));
        System.out.println(stream.map(e -> Stream.of(e.split(" "))).count());
    }

}
