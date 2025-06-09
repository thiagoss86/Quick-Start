package com.thiago.quick.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class QuickStartApplication {

//	Input: nums1 = [0,1,3], nums2 = [3,4,1]
//	Output = 1
//	Explanation: The smallest element common to both arrays is 1, so we return 1.

    public static void main(String[] args) {
        SpringApplication.run(QuickStartApplication.class, args);

        int[] a = {0, 1, 2};
        int[] b = {2, 4, 7};

        var smallestCommon = findSmallestCommon(a, b);

        smallestCommon.ifPresentOrElse(
                elm -> System.out.println("Menor valor em comum é : " + elm),
                () -> System.out.println("Não é elementos em comum.")
        );

    }

    public static OptionalInt findSmallestCommon(int[] arrA, int[] arrB) {

        Set<Integer> set1 = Arrays.stream(arrA)
                .boxed()
                .collect(Collectors.toSet());

        return Arrays.stream(arrB)
                .filter(set1::contains)
                .min();
    }


}
