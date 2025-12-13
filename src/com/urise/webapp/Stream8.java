package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Stream8 {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 1, 3, 5, 5, 7, 9, 8, 4, 2, 2, 6};
        System.out.println("Original array: " + Arrays.toString(numbers));
        System.out.println("minValue: " + minValue(numbers));
        List<Integer> numbersList = Optional.of(numbers)
                .map(numb -> Arrays.stream(numb).boxed().toList())
                .orElse(new ArrayList<>());
        System.out.println("oddOrEven: " + oddOrEven(numbersList));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (acc, digit) -> acc * 10 + digit);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return Optional.ofNullable(integers)
                .map(list -> {
                    int sum = (list.stream().mapToInt(Integer::intValue).sum()) % 2;
                    return list.stream()
                            .filter(n -> n % 2 != sum)
                            .collect(Collectors.toList());
                }).orElse(new ArrayList<>());
    }
}

