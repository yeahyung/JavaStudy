package ModernJava.Chapter4;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Stream {
    /*
    스트림: 데이터 처리 연산을 지원하도록 소스에서 추출된 연속된 요소
    연속된 요소: 스트림은 컬렉션과 마찬가지로 특정 요소 형식으로 이루어진 연속된 값 집합의 인터페이스를 제공한다.
    컬렉션의 주제: 데이터, 스트림의 주제: 계산
    스트림은 컬렉션, 배열 등의 데이터 제공 소스로부터 데이터를 소비하며 정렬된 컬렉션으로 스트림을 생성하면 정렬이 그대로 유지된다.

    스트림의 2가지 특징
    1. 파이프라이닝: 스트림 연산은 스트림 연산끼리 연결해서 커다란 파이프라인을 구성할 수 있도록 스트림 자신을 반환한다.
    내부 반복: 반복자를 이용해서 명시적으로 반복하는 컬렉션과 달리 스트림은 내부 반복을 지원한다.
     */
    public static void main(String[] args) {
        List<Dish> menu = new ArrayList<>();
        menu.add(new Dish("food1", true, 100, "FISH"));
        menu.add(new Dish("food2", false, 200, "MEAT"));
        menu.add(new Dish("food3", true, 300, "OTHER"));
        menu.add(new Dish("food4", true, 400, "FISH"));
        menu.add(new Dish("food5", false, 500, "MEAT"));
        menu.add(new Dish("food6", false, 600, "OTHER"));

        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        lowCaloricDishes.forEach(System.out::println);

        // stream 활용법
        menu.stream() // parallelStream 사용 시 멀티코어 아키텍쳐에서 병렬로 실행한다.
                .filter(d -> d.getCalories() < 400) // 400 칼로리 미만 필터링
                .sorted(Comparator.comparing(Dish::getCalories)) // 정렬
                .map(Dish::getName) // 요리명 추출
                .collect(Collectors.toList()) // 요리명을 List 에 저장
                .forEach(System.out::println);

        Map<String, List<Dish>> dishesByType = menu.stream()
                .collect(groupingBy(Dish::getType));

        /*
         collect 를 제외한 filter, amp, limit 은 데이터 처리 연산을 하며
         파이프라인을 형성할 수 있도록 스트림을 반환
         filter: 람다를 인수로 받아 스트림에서 특정 요소를 제외시킨다.
         map: 람다를 이용해서 한 요소를 다른 요소로 변환하거나 정보를 추출한다.
         limit: 정해진 개수 이상의 요소가 스트림에 저장되지 못하게 스트림의 크기를 축소한다.
         collect: 스트림을 다른 형식으로 변환한다.
         */
        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .sorted(Comparator.comparing(Dish::getCalories).reversed())
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(threeHighCaloricDishNames);

        /*
         컬렉션: 현재 자료구조가 포함하는 모든 값을 메모리에 저장하는 자료구조
         즉, 컬렉션의 모든 요소는 컬렉션에 추가하기 전에 계산되어야 한다.

         스트림: 요청할 때만 요소를 계산하는 고정된 자료구조(스트림에 요소를 추가하거나 제거할 수 없음)
         사용자가 데이터를 요청할 때만 값을 계산한다.
         딱 한 번만 탐색할 수 있음
         */

        // Quiz 4-1.
        menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .collect(Collectors.toList());


    }
}
