package com.streamtelecom;

import com.streambank.Customer;
import com.sun.net.httpserver.Authenticator;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Subscriber> subscribers = Arrays.asList(
                new Subscriber( "1001", "IND", true ),
                new Subscriber("2002", "IND", false ),
                new Subscriber("3003", "COR", true )
        );
        List<Usage> usages = Arrays.asList(
                new Usage( "1001", "DATA", 1_000 ),
                new Usage( "1001", "VOICE", 1_000 ),
                new Usage("2002", "DATA", 100 ),
                new Usage("3003", "2002", 200 )
        );
        List<Bill> bills = Arrays.asList(
                new Bill( "1001", 1_000, true),
                new Bill("2002", 2_000, false ),
                new Bill("3003", 2_000, false )
        );

        // Her abone için type göre usage bul -----------------------------------------------
        Map<String, Map<String, Double>> getUsagebySubscriber = usages.stream()
                .collect(Collectors.groupingBy(
                Usage::getSubscriberId,
                Collectors.groupingBy(
                      Usage::getType,
                      Collectors.summingDouble(Usage::getAmount)
                )
         ));
        // Segment bazlı toplam kullanım  -----------------------------------------------
        Map<String,  String > mapSubsSegment =  subscribers.stream()
                .collect(Collectors.toMap(
                        Subscriber::getSubscriberId,
                        Subscriber::getSegment
                ));
        System.out.println(mapSubsSegment);

       Map<String,  Double> totalUsagebySegment = usages.stream()
               .collect(Collectors.groupingBy(
                       u -> mapSubsSegment.get(u.getSubscriberId()),
                       Collectors.summingDouble(Usage::getAmount)
               ));
        System.out.println(totalUsagebySegment);

        // en çok DATA kullanan 2 abone bul
        // type = DATA filter, group ( birden fazla DATA olabilir) abone grouplayarak bul
        // bu map value deki amounta göre sort , limit = 2 ,  ve aboneleri liste şeklinde dön
        List<String> listSubs=usages.stream()
                .filter( t-> t.getType().equals("DATA"))
                .collect( Collectors.groupingBy(
                        Usage::getSubscriberId,
                        Collectors.summingDouble(Usage::getAmount)
                ))
                 .entrySet().stream()
                 .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(2)
                .map(Map.Entry::getKey)
                        .toList();

        System.out.println(listSubs);

        //Ödenmemiş faturası olan aktif aboneler bul ------------------------------------
        // subs active = true ve bills den gelen aboneler olanları bul
        // filter  paid = false subs ları bul
        List<String> activeSubsWithUnpaidBills =
                subscribers.stream()
                        .filter(Subscriber::isActive)
                        .map(Subscriber::getSubscriberId)
                        .filter(subscriberId ->
                                bills.stream()
                                        .anyMatch(b ->
                                                b.getSubscriberId().equals(subscriberId)
                                                        && !b.isPaid()
                                        )
                        )
                        .toList();

        System.out.println(activeSubsWithUnpaidBills);
    }
}
