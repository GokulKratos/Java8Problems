package com.advance.java8.problems;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Java8Problems {
    public static void main(String[] args) {
        Java8Problems java = new Java8Problems();

        //12.Java 8 program to check if two strings are anagrams or not?
        java.anagramOrNot();
    }

    public void anagramOrNot() {
        BiFunction<String, String, Boolean> anagramsOrNot = (word1, word2) -> {
            String firstWord = Arrays.stream(word1.split(""))
                    .map(String::toLowerCase)
                    .sorted()
                    .collect(Collectors.joining());

            String secondWord = Arrays.stream(word2.split(""))
                    .map(String::toLowerCase)
                    .sorted()
                    .collect(Collectors.joining());

            return firstWord.equalsIgnoreCase(secondWord);
        };

        Boolean result12 = anagramsOrNot.apply("RaceCar", "CarRace");
        System.out.println("Anagram Or Not: " + result12);
    }
}
