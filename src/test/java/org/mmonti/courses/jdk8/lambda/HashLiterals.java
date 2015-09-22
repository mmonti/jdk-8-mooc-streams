package org.mmonti.courses.jdk8.lambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mmonti on 9/16/15.
 */
public class HashLiterals {

    public static void main(String[] args) {
        Map<String, Object> values = hash(
                hello -> "world",
                bob -> bob,
                bill -> hash(another -> 1)
        );

        System.out.println(values);
    }

    public static <T> Map<String, T> hash(NamedValue<T>... keyValuePairs) {
        Map<String, T> map = new HashMap<>();
        Arrays.asList(keyValuePairs)
                .stream()
                .forEach(kvp ->
                                map.put(
                                        kvp.name(),
                                        kvp.value())
                );
        return map;
    }
}
