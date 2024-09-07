package com.advance.java8.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8Problems {
    public static void main(String[] args) {
        Java8Problems java = new Java8Problems();

        //12.Java 8 program to check if two strings are anagrams or not?
        java.anagramOrNot();

        //13.Find sum of all digits of a number in Java 8?
        java.sumOfDigits(15623);

        //14.Find second largest number in an integer array?
        List<Integer> list14 = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
        java.secondLargest(list14);

        //15.Given a list of strings, sort them according to increasing order of their length?
        List<String> list15 = Arrays.asList("Java", "Python", "C#", "HTML", "Kotlin", "C++", "COBOL", "C");
        java.sortAsc(list15);

        //16.Given an integer array, find sum and average of all elements?
        int[] arr16 = new int[]{45, 12, 56, 15, 24, 75, 31, 89};
        java.sumAndAvg(arr16);

        //17.How do you find common elements between two arrays?
        List<Integer> list17a = Arrays.asList(71, 21, 34, 89, 56, 28);
        List<Integer> list17b = Arrays.asList(12, 56, 17, 21, 94, 34);
        java.commonElements(list17a, list17b);

        //18.Reverse each word of a string using Java 8 streams?
        String str18 = "Java Concept Of The Day";
        java.reverseEachString(str18);
    }

    private void reverseEachString(String words) {
        String result= Arrays.stream(words.split(" "))
                .map(word -> new StringBuilder(word).reverse())
                        .collect(Collectors.joining(" "));
        System.out.println("reverseEachString: "+result);
    }

    private void commonElements(List<Integer> numbers1, List<Integer> numbers2) {
        System.out.println("common elements:");
        //normal
        List<Integer> resultlist = new ArrayList<>();
        resultlist.addAll(numbers1);
        resultlist.addAll(numbers2);
        resultlist
                .stream()
                .collect(Collectors.groupingBy(Function.identity()))
                .entrySet()
                .stream()
                .forEach(entry -> {
                    if (entry.getValue().size() > 1) {
                        System.out.print(entry.getKey() + ",");
                    }
                });
        System.out.println();
        System.out.println("common elements:");
        //optimized
        numbers1.stream()
                .filter(numbers2::contains)
                .forEach(num-> {
                    System.out.print(num + ",");
                });
    }

    private void sumAndAvg(int[] numbers) {
        Integer sum = Arrays.stream(numbers)
                .reduce(0, Integer::sum);
        Integer avg = sum / numbers.length;

        System.out.println("sum: " + sum + " avg:" + avg);

        //optimized
        Integer sumOptimized = Arrays.stream(numbers).sum();
        Double avgOptimized = Arrays.stream(numbers).average().getAsDouble();

        System.out.println("sumOptimized: " + sumOptimized + " avgOptimized:" + avgOptimized);
    }

    private void sortAsc(List<String> words) {
        List<String> sortedList = words
                .stream()
                .sorted(Comparator.comparing(String::length))
                .toList();

        System.out.println("sortedList: " + sortedList);
    }

    private void secondLargest(List<Integer> numbers) {
        //normal
        Integer secondLargest = (Integer) numbers
                .stream()
                .sorted(Comparator.reverseOrder())
                .toArray()[1];

        System.out.println("secondLargest: " + secondLargest);

        //optimized
        Integer secondLargestOptimized = numbers
                .stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst().get();

        System.out.println("secondLargestOptimized: " + secondLargestOptimized);
    }

    private void sumOfDigits(int number) {
        //normal
        Integer sumOfDigits = Arrays.stream(String.valueOf(number).split(""))
                .map(Integer::valueOf)
                .reduce(0, Integer::sum);
        System.out.println("sumOfDigits of " + number + ": " + sumOfDigits);

        //optimized
        Integer sum = Stream.of(String.valueOf(number).split(""))
                .collect(Collectors.summingInt(Integer::parseInt));
        System.out.println("sum of " + number + ": " + sum);
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
