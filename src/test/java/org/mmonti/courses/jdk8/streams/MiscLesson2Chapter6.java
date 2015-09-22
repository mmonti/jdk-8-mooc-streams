package org.mmonti.courses.jdk8.streams;

import org.junit.Test;

import java.util.stream.IntStream;

import static java.util.Arrays.*;
import static junit.framework.Assert.assertEquals;

/**
 * Created by mmonti on 9/16/15.
 */
public class MiscLesson2Chapter6 {

    @Test
    public void findFirstTest() {
        int[] numbers = {1,5,3,4,7,2,4};
        IntStream stream = stream(numbers);
        assertEquals(5, stream.filter((e) -> e > 3).findFirst().getAsInt());
    }

    @Test
    public void countTest() {
        String[] vowels = {"a", "e", "i", "o", "u"};
        assertEquals(5, stream(vowels).count());
    }

    @Test
    public void reduceTest() {
        String[] vowels = {"a", "e", "i", "o", "u"};
        String result = stream(vowels).reduce((initial, d) -> initial = initial+d ).get();
        assertEquals("aeiou", result);
    }
}
