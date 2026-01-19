package com.streambank;

import com.streamexample.User;

import javax.swing.text.Segment;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = Arrays.asList(
                new Customer("1", "John", true, "I"),
                new Customer("2", "Clara", false, "C"),
                new Customer("3", "Tom", true, "C")
        );
        List<Account> accounts = Arrays.asList(
                new Account("111111", "1", 10_000, true),
                new Account("122222", "1", 20_000, false),
                new Account("133333", "1", 20_000, false),
                new Account("164444", "2", 3_000, false),
                new Account("155555", "3", 2_000, false)
        );
        List<Transaction> transactions = Arrays.asList(
                new Transaction("122222", 1_000, "W"),
                new Transaction("133333", 1_000, "T"),
                new Transaction("144444", 2_000, "T")
        );
        // 1. Aktif müşterilerin listesi -----------------------------------------------
        List<Customer> customerList = customers.stream()
                .filter(Customer::isActive)
                .toList();
        // Bloke olmayan ve bakiyesi pozitif hesaplar------------------------------------
        List<Account> accountList = accounts.stream()
                .filter(t -> !t.isBlocked() && t.getBalance() > 0)
                .toList();
        // Bir Müşterinin ( customerId = 1 )toplam bakiyesi------------------------------
        double totalBalance = accounts.stream()
                .filter(t -> t.customerId.equals("1"))
                .mapToDouble(Account::getBalance)
                .sum();
        // Her hesap için kaç işlem yapılmış?--------------------------------------------
        Map<String, Long> transactionCount = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getAccountNo,
                        Collectors.counting()
                ));
  /* ***NOT*** :
    groupingBy beraber kullanılabilecek kombinasyonlar:
       mapping() , flatMapping, filtering , teeing
   */
        //Segment bazlı müşteri sayısı ----------------------------------------------------
        Map<String, Long> customerCountbySegment = customers.stream()
                .collect(Collectors.groupingBy(Customer::getSegment,
                        Collectors.counting()
                ));
        // Aktif müşteriler için segment bazlı müşteri sayısı------------------------------
        Map<String, Long> activeCustbySegment = customers.stream()
                .collect(Collectors.groupingBy(Customer::getSegment,
                        Collectors.filtering(Customer::isActive, Collectors.counting())
                ));

        // Aktif müşteriler için segment bazlı müşterileri bul ve set halinde segmentteki müşterileri yaz
        Map<String, Set<String>> customerNamesbySegment = customers.stream()
                .collect(Collectors.groupingBy(Customer::getSegment,
                        Collectors.filtering(Customer::isActive,
                                Collectors.mapping(Customer::getName, Collectors.toSet()))
                ));


        // Her customer için ( teeing + groupingby )
        //   -- account sayısı
        //   -- Toplam bakiyesini bul
        Map<String, AccountSummary> segmentSummary =
                accounts.stream()
                        .collect(Collectors.groupingBy(
                                Account::getCustomerId,
                                Collectors.teeing(
                                        Collectors.counting(),
                                        Collectors.summingDouble(Account::getBalance),
                                        AccountSummary::new)
                        ));
        System.out.println(segmentSummary);
        // {1=AccountSummary[typeCount=3, totalAmount=50000.0],
        //  2=AccountSummary[typeCount=1, totalAmount=3000.0],
        //  3=AccountSummary[typeCount=1, totalAmount=3000.0]}

        // Her customer için ( teeing + groupingby )
        //   -- account sayısı
        //   -- Toplam bakiyesini bul  , ama blokeli hesapları atla
        Map<String, AccountSummary> segmentSummary1 =
                accounts.stream()
                        .filter(account -> !account.isBlocked())
                        .collect(Collectors.groupingBy(
                                Account::getCustomerId,
                                Collectors.teeing(
                                        Collectors.counting(),
                                        Collectors.summingDouble(Account::getBalance),
                                        AccountSummary::new)
                        ));
        System.out.println(segmentSummary1);
        // {1=AccountSummary[typeCount=2, totalAmount=40000.0],
        //  2=AccountSummary[typeCount=1, totalAmount=3000.0],
        //  3=AccountSummary[typeCount=1, totalAmount=3000.0]}

        // Her hesap için en büyük tutarlı işlem
        Map<String, Optional<Transaction>> maxAmount = transactions.stream().
                collect(Collectors.groupingBy(
                        Transaction::getAccountNo,
                        Collectors.maxBy(Comparator.comparing(Transaction::getAmount))
                ));
        System.out.println(maxAmount);


        // Aktif Müşterilerin toplam banka bakiyesi
        Set<String> activeCustomers =
                customers.stream()
                        .filter(Customer::isActive)
                        .map(Customer::getCustomerId)
                        .collect(Collectors.toSet());

        double custTotalBalance =
                accounts.stream()
                        .filter(a -> activeCustomers.contains(a.getCustomerId()))
                        .mapToDouble(Account::getBalance)
                        .sum();

       // sorted örneği accountno ve balance göre sırala
        accounts.stream()
                .sorted(Comparator.comparing(Account::getAccountNo)
                        .thenComparing(Account::getBalance))
                .forEach(c -> System.out.println(c.getAccountNo() + " - " + c.getBalance()));
        // account listesinden 1 tane account skip -> 2 tane yazdır
        accounts.stream().skip(1).limit(2).forEach(c -> System.out.println(c.getAccountNo()));

        // anyMatch  allMatch NoneMatch
        boolean isEx = accounts.stream().anyMatch(n -> n.getBalance() > 10000); // true
        boolean isExist = accounts.stream().allMatch(n -> n.getBalance() > 10000);
        boolean isNotExist = accounts.stream().noneMatch(n -> n.getBalance() > 100000);
        System.out.println(isEx); // true
        System.out.println(isExist); // false
        System.out.println(isNotExist); // true

        // Aktif Müşteri ve müşterinin Blokeli HEsap var mı ?
        boolean activeCustomerBlockedAccount = customers.stream()
                .filter(Customer::isActive)
                .anyMatch(c -> accounts.stream()
                        .anyMatch(a -> a.getCustomerId().equals(c.getCustomerId()) && a.isBlocked()));
    }
}