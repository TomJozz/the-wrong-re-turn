package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String content = DigitReader.readFileAsString("./hyperskill-dataset.txt");
        var frequencies = DigitCounter.countDigits(content);
        var topDigits = TopDigitsFinder.findTopNDigits(frequencies, 5);
        for (var topDigit : topDigits) {
            System.out.println(topDigit.getKey() + ":" + topDigit.getValue() + " times" );
        }
    }
}

class DigitReader {
    public static String readFileAsString(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath))).trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class DigitCounter {
    public static Map<Character, Integer> countDigits(String input) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
        }
        return frequencyMap;
    }
}

class TopDigitsFinder {
    public static List<Map.Entry<Character, Integer>> findTopNDigits(Map<Character, Integer> frequencyMap, int n) {
        return frequencyMap.entrySet()
                .stream()
                .sorted((a, b) -> {
                    int freq = b.getValue().compareTo(a.getValue());
                    if (freq != 0) {
                        return freq;
                    }
                    return Character.compare(b.getKey(), a.getKey());
                })
                .limit(n)
                .collect(Collectors.toList());
    }
}
