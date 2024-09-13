package com.advance.java8.problems;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8Problems {
    public static void main(String[] args) {
        Java8Problems java = new Java8Problems();

        // 1.Given a list of integers, separate odd and even numbers?
        List<Integer> numbers = Arrays.asList(71, 18, 42, 21, 67, 32, 95, 14, 56, 87);
        java.separateOddAndEven(numbers);

        // 2.How do you remove duplicate elements from a list using Java 8 streams?
        List<String> words = Arrays.asList("Java", "Python", "C#", "Java", "Kotlin", "Python");
        java.removeDuplicates(words);

        // 3.How do you find frequency of each character in a string using Java 8 streams?
        String inputString = "Java Concept Of The Day";
        java.findFrequency(inputString);

        // 4. How do you find frequency of each element in an array or a list?
        List<String> stationeryList = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Stapler",
                "Note Book", "Pencil");
        java.findElementFrequency(stationeryList);

        // 5.How do you sort the given list of decimals in reverse order?
        List<Double> decimalList = Arrays.asList(12.45, 23.58, 17.13, 42.89, 33.78, 71.85, 56.98, 21.12);
        java.sortDesc(decimalList);

        // 6.Given a list of strings, join the strings with ‘[‘ as prefix, ‘]’ as suffix and ‘,’ as delimiter?
        List<String> listOfStrings = Arrays.asList("Facebook", "Twitter", "YouTube", "WhatsApp", "LinkedIn");
        java.joinString(listOfStrings);

        // 7.From the given list of integers, print the numbers which are multiples of 5?
        List<Integer> listOfIntegers = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
        java.multiplesOfFive(listOfIntegers);

        // 8.Given a list of integers, find maximum and minimum of those numbers?
        List<Integer> minMaxNumbers = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
        java.findMinAndMax(minMaxNumbers);

        // 9.How do you merge two unsorted arrays into single sorted array using Java 8 streams?
        int[] a = new int[] {4, 2, 7, 1};
        int[] b = new int[] {8, 3, 9, 5};
        java.combineBothArrays(a,b);

        // 10. How do you merge two unsorted arrays into single sorted array without duplicates?
        int[] one = new int[] {4, 2, 5, 1};
        int[] two = new int[] {8, 1, 9, 5};
        java.combineBothArraysDistinct(one, two);

        // 11.How do you get three maximum numbers and three minimum numbers from the given list of integers?
        List<Integer> threeMinMax = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
        java.findThreeMinAndMax(threeMinMax);
        
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

        //27.Find first repeated character in a string?
        String str27 = "Java Concept Of The Day".replaceAll("\\s+", "").toLowerCase();
        java.firstRepeatedCharacter(str27);

        //28.Find first non-repeated character in a string?
        String str28 = "Java Concept Of The Day".replaceAll("\\s+", "").toLowerCase();
        java.firstNonRepeatedCharacter(str28);

        //29.Fibonacci series?
        java.fibonacciSeries();

        //30.First 10 odd numbers?
        java.findFirstTenOddNumbers();

        //31.How do you get last element of an array?
        List<String> list31 = Arrays.asList("One", "Two", "Three", "Four", "Five", "Six");
        java.findLastElement(list31);

        //32. Find the age of a person in years if the birthday has given?
        LocalDate birthDay = LocalDate.of(1985, 01, 23);
        java.findAge(birthDay);
    }

    private void findAge(LocalDate birthDay) {
        LocalDate today = LocalDate.now();
        System.out.println("Age: "+ChronoUnit.YEARS.between(birthDay, today)+" years");
    }

    private void findLastElement(List<String> words) {
        String word = words.stream()
                .skip(words.size() - 1)
                .findFirst()
                .get();
        System.out.println("\nlast element of an array: "+ word);
    }

    private void findFirstTenOddNumbers() {
        System.out.println();
        System.out.println("First 10 odd numbers: ");
        IntStream.rangeClosed(0,9)
                .map(result -> (2*result) + 1)
                .forEach(result -> System.out.print(result+","));

        //optimized
        Stream.iterate(new int[] {1,3}, f-> new int[] {f[1],f[1]+2})
                .limit(10)
                .map(a->a[0])
                .forEach(result -> System.out.print(result+","));
    }

    private void fibonacciSeries() {
        System.out.print("Fibonacci Series:");
        Stream.iterate(new int[] {0,1}, f -> new int[] {f[1], f[0]+f[1]})
                .limit(10)
                .map(f->f[0])
                .forEach(result -> System.out.print(result+", "));
    }

    private void firstNonRepeatedCharacter(String word) {
        String ch = Arrays.stream(word.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() <= 1)
                .findFirst()
                .get()
                .getKey();
        System.out.println("non-repeated character: "+ ch);
    }

    private void firstRepeatedCharacter(String word) {
        Set<String> set = new HashSet<>();
        String repeated = Arrays.stream(word.split(""))
                .filter(str -> !set.add(str))
                .findFirst().get();
        System.out.println("repeated: "+repeated);

        //another way
        String repeatedChar = Arrays.stream(word.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .findFirst()
                .get()
                .getKey();
        System.out.println("repeatedChar: "+repeatedChar);
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

    private void findThreeMinAndMax(List<Integer> threeMinMax) {
        threeMinMax
                .stream()
                .sorted()
                .limit(3)
                .forEach(System.out::println);

        threeMinMax
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .forEach(System.out::println);
    }

    private void combineBothArraysDistinct(int[] one, int[] two) {
        int[] result = IntStream.concat(Arrays.stream(one), Arrays.stream(two))
                .sorted()
                .distinct()
                .toArray();
        System.out.println(Arrays.toString(result));
    }

    private void combineBothArrays(int[] a, int[] b) {
        int[] result = IntStream.concat(Arrays.stream(a), Arrays.stream(b)).sorted().toArray();
        System.out.println(Arrays.toString(result));
    }

    private void findMinAndMax(List<Integer> minMaxNumbers) {
        int min = minMaxNumbers
                .stream()
                .min(Comparator.naturalOrder())
                .get();

        int max = minMaxNumbers
                .stream()
                .max(Comparator.naturalOrder())
                .get();
        System.out.println("Min: "+min+" Max: "+max);
    }

    private void multiplesOfFive(List<Integer> listOfIntegers) {
        listOfIntegers
                .stream()
                .filter(num -> num%5 == 0)
                .forEach(System.out::println);
    }

    private void joinString(List<String> listOfStrings) {
        String join = listOfStrings
                .stream()
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println();
        System.out.println("Join: "+join);
    }

    private void sortDesc(List<Double> decimalList) {
        //normal
        List<Double> sortedList = decimalList
                .stream()
                .sorted(Comparator.comparing(a->a*-1))
                .toList();
        System.out.println("sortedList: "+sortedList);

        //optimized
        decimalList
                .stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::print);
    }

    private void findElementFrequency(List<String> stationeryList) {
        Map<String, Long> frequency = stationeryList
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("frequency element: "+frequency);
    }

    private void findFrequency(String inputString) {
        //normal
        Map<String,Integer> frequency = Arrays.stream(inputString.split(""))
                .collect(Collectors.groupingBy(word -> word))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry->entry.getKey(), entry -> entry.getValue().size()));
        System.out.println("frequency: "+frequency);

        //optimized
        inputString.chars()
                .mapToObj(word -> (char) word)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private void removeDuplicates(List<String> words) {
        List<String> uniqueElements = words
                .stream()
                .distinct()
                .toList();
        System.out.println("uniqueElements:"+ uniqueElements);
    }

    private void separateOddAndEven(List<Integer> numbers) {
        //normal
        List<Integer> oddList = numbers.stream().filter(number -> number % 2 != 0).collect(Collectors.toList());

        List<Integer> evenList = numbers.stream().filter(number -> number % 2 == 0).collect(Collectors.toList());

        System.out.println("oddList: " + oddList);
        System.out.println("evenList: " + evenList);

        //optimized
        numbers
                .stream()
                .collect(Collectors.partitioningBy(number -> number % 2 == 0))
                .entrySet()
                .stream()
                .forEach(num -> {
                    if (num.getKey()) {
                        System.out.println("even: " + num.getValue());
                    } else {
                        System.out.println("odd: " + num.getValue());
                    }
                });
    }
}
