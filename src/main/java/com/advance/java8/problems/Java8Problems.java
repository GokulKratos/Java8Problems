package com.advance.java8.problems;

import java.util.*;
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

        //19.How do you find sum of first 10 natural numbers?
        java.sumOfNaturalNumbers();

        //20.Reverse an integer array
        int[] arr20 = new int[]{5, 1, 7, 3, 9, 6};
        java.reverseIntegerArr(arr20);

        //21.Print first 10 even numbers
        java.first10EvenNumbers();

        //22.How do you find the most repeated element in an array?
        List<String> list22 = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Pen", "Note Book", "Pencil");
        java.mostRepeatedElement(list22);

        //23.Palindrome program using Java 8 streams
        String word23 = "ROTATOR";
        java.isPalidromeOrNot(word23);

        //24.Given a list of strings, find out those strings which start with a number?
        List<String> list24 = Arrays.asList("One", "2wo", "3hree", "Four", "5ive", "Six");
        java.startWithNumber(list24);

        //25.How do you extract duplicate elements from an array?
        List<Integer> list25 = Arrays.asList(111, 222, 333, 111, 555, 333, 777, 222);
        java.extractDuplicates(list25);

        //26.Print duplicate characters in a string?
        String str26 = "Java Concept Of The Day".replaceAll("\\s+", "").toLowerCase();
        java.duplicatesInString(str26);
    }

    private void duplicatesInString(String word) {
        Set<String> set = new HashSet<>();
        Set<String> duplicateSet = Arrays.stream(word.split(""))
                .filter(str -> !set.add(str))
                .collect(Collectors.toSet());
        System.out.println("duplicateSet: "+duplicateSet);
    }

    private void extractDuplicates(List<Integer> numbers) {
        List<Integer> duplicatesList = numbers.stream()
                .collect(Collectors.groupingBy(Function.identity()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() > 1)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println("duplicatesList: " + duplicatesList);

        //optimized
        Set<Integer> set = new HashSet<>();
        numbers.stream()
                .filter(number -> !set.add(number))
                .collect(Collectors.toSet());
        System.out.println("duplicatesListOptimized: " + duplicatesList);
    }

    private void startWithNumber(List<String> words) {
        List<String> numbers = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        List<String> startWithNumbers = words.stream()
                .filter(word -> numbers.contains(String.valueOf(word.charAt(0))))
                .toList();
        System.out.println("startWithNumbers: " + startWithNumbers);

        //optimized
        List<String> startWithNumbersOptimized = words.stream()
                .filter(word -> Character.isDigit(word.charAt(0)))
                .toList();
        System.out.println("startWithNumbersOptimized: " + startWithNumbersOptimized);
    }

    private void isPalidromeOrNot(String word) {
        Boolean isPalindrome = IntStream.range(0, word.length() / 2)
                .noneMatch(i -> word.charAt(i) != word.charAt(word.length() - i - 1));
        System.out.println("isPalindrome: " + isPalindrome);
    }

    private void mostRepeatedElement(List<String> words) {
        String repeatedElement = words.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
        System.out.println(repeatedElement);
    }

    private void first10EvenNumbers() {
        IntStream.iterate(2, i -> i < 21, i -> i + 2)
                .forEach(System.out::println);

        //optimized
        IntStream.rangeClosed(1, 10)
                .map(i -> i * 2)
                .forEach(System.out::println);
    }

    private void reverseIntegerArr(int[] numbers) {
        int[] arr = IntStream.iterate(numbers.length - 1, i -> i >= 0, i -> i - 1)
                .map(a -> numbers[a])
                .toArray();
        System.out.println(Arrays.toString(arr));
    }

    private void sumOfNaturalNumbers() {
        //normal
        Integer sum = IntStream.range(1, 11)
                .sum();
        System.out.println("sum of first 10 natural numbers: " + sum);

        //optimized
        int n = 10;
        Integer sumOptmized = ((n) * (n + 1)) / 2;
        System.out.println("sumOptmized of first 10 natural numbers: " + sumOptmized);
    }

    private void reverseEachString(String words) {
        String result = Arrays.stream(words.split(" "))
                .map(word -> new StringBuilder(word).reverse())
                .collect(Collectors.joining(" "));
        System.out.println("reverseEachString: " + result);
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
                .forEach(num -> {
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
