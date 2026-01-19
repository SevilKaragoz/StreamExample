package com.streamexample;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserStreamExample {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User(1L,"Name1","Surname1", 30,true),
                new User(2L, "Name2", "Surname2", 25,false),
                new User(3L, "Name3", "Surname3", 33,true )
        );
        //-------------------------------------------------------------------------------------
        List<String> activeUsers  =
                users.stream()
                        .filter(User::getActive)
                        .sorted(Comparator.comparing(User::getAge))
                        .map(User::getName)
                        .toList();

        System.out.println(activeUsers);
        //-------------------------------------------------------------------------------------
        List<String> olderUsers  =
                users.stream()
                        .filter(s -> s.getAge()> 25)
                        .map(User::getName)
                        .toList();
        System.out.println(olderUsers);

        //-------------------------------------------------------------------------------------
        User oldestUser = users.stream()
                .max(Comparator.comparing(User::getAge))
                .orElse(null);
        System.out.println(oldestUser.getName());
        //-------------------------------------------------------------------------------------
        long countActiveUser = users.stream()
                .filter(User::getActive)
                .count();
        System.out.println(countActiveUser);
        //-------------------------------------------------------------------------------------
        String allName = users.stream()
                .map(User::getName)
                .collect(Collectors.joining(", "));
        System.out.println(allName);
        //-------------------------------------------------------------------------------------
        double avgAge = users.parallelStream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
        System.out.println(avgAge);
        //-------------------------------------------------------------------------------------

    }
}
