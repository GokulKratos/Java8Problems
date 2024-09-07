package com.advance.java8.problems;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Problems {
    public static void main(String[] args) {
        Java8Problems java = new Java8Problems();

        //12.Java 8 program to check if two strings are anagrams or not?
        java.anagramOrNot();

        //13.Find sum of all digits of a number in Java 8?
        java.sumOfDigits(15623);
    }

    private void sumOfDigits(int number) {
        //normal
        Integer sumOfDigits = Arrays.stream(String.valueOf(number).split(""))
                .map(Integer::valueOf)
                .reduce(0, Integer::sum);
        System.out.println("sumOfDigits of "+ number+ ": "+sumOfDigits);

        //optimized
        Integer sum = Stream.of(String.valueOf(number).split(""))
                .collect(Collectors.summingInt(Integer::parseInt));
        System.out.println("sum of "+ number+ ": "+sum);
    }

    private void anagramOrNot() {
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
