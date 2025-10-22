package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String content = DigitReader.readFileAsString("./hyperskill-dataset.txt");


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
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(n)
                .collect(Collectors.toList());
    }
}
