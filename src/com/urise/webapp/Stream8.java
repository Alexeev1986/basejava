package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream8 {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 1, 3, 5, 5, 7, 9, 8, 4, 2, 2, 6};
        System.out.println(minValue(numbers));
        List<Integer> numbersList = Arrays.stream(numbers).boxed().toList();
        System.out.println(oddOrEven(numbersList));
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream()
                .filter(n -> ((integers.stream().mapToInt(Integer::intValue).sum()) % 2 == 0) == (n % 2 == 0))
                .distinct()
                .collect(Collectors.toList());
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (acc, digit) -> acc * 10 + digit);
    }
}

