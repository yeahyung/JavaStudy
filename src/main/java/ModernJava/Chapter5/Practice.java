package ModernJava.Chapter5;

import ModernJava.Chapter4.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Practice {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리
        List<Transaction> quiz1 = transactions.stream()
                .filter(transaction -> 2011 == transaction.getYear())
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        quiz1.forEach(System.out::println);

        // 2. 거래자가 근무하는 모든 도시를 중복 없이 나열
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList()).forEach(System.out::println);

        // 3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬
        transactions.stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList()).forEach(System.out::println);

        // 4. 모든 거래자의 이름을 알파벳순으로 정렬
        transactions.stream()
                .map(transaction -> transaction.getTrader())
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList()).forEach(System.out::println);

        // 5. 밀라노에 거래자가 있는가?
        System.out.println(transactions.stream()
                .anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity())));

        // 6. 케임브리지에 거주하는 거래자의 모든트랜잭션값
        System.out.println(transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .reduce(Integer::sum));

        // 7. 전체 트랜잭션 중 최댓값은?
        System.out.println(transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max));

        // 8. 전체 트랜잭션 중 최솟값은?
        System.out.println(transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min));
    }
}
