package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream8 {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 1, 3, 5, 5, 7, 9, 8, 4, 2, 2, 6};
        System.out.println("minValue: " + minValue(numbers));
        List<Integer> numbersList = Arrays.stream(numbers).boxed().toList();
        System.out.println("oddOrEven1: " + oddOrEven1(numbersList));
        System.out.println("oddOrEven2: " + oddOrEven2(numbersList));
    }

    private static List<Integer> oddOrEven1(List<Integer> integers) {
        int sum = (integers.stream().mapToInt(Integer::intValue).sum()) % 2;
        return integers.stream()
                .filter(n -> (sum == 0) == (n % 2 == 0))
                .collect(Collectors.toList());
    }

    private static List<Integer> oddOrEven2(List<Integer> integers) {
        return integers.stream()
                .filter(n -> ((integers.stream().mapToInt(Integer::intValue).sum()) % 2 == 0) == (n % 2 == 0))
                .collect(Collectors.toList());
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (acc, digit) -> acc * 10 + digit);
    }
}

