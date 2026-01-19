package com.streamexample;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        /*
        Ara işlemler : filter map sorted
        Sonlandırıcı işlemler : forEach collect reduce count
         */
        /*
            Collection Stream -> List.stream(), set.streamI()
            Array Stream      -> Array.stream()
            I/O Stream
         */
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        List<Integer> couplesSquare =
                numbers.stream()
                        .filter(n -> n % 2 == 0)
                        .map(n -> n * n)
                        .collect(Collectors.toList());
        System.out.println(couplesSquare); //[4, 16, 36]
        //------------------------------------------------------------------------------
        int totalCouplesSquare =
                numbers.stream()
                        .filter(n -> n % 2 == 0)
                        .map(n -> n * n)
                        .reduce(0,Integer::sum);
        System.out.println(totalCouplesSquare); // 56

        //------------------------------------------------------------------------------

        long ciftSayisi = numbers.stream().filter(n -> n % 2 == 0).count();
        System.out.println(ciftSayisi); // 3
        //------------------------------------------------------------------------------

        boolean varMi = numbers.stream().anyMatch(n -> n > 4);
        System.out.println(varMi); // true
        //------------------------------------------------------------------------------

    }
}