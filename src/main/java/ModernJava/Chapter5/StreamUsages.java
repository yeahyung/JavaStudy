package ModernJava.Chapter5;

import ModernJava.Chapter4.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamUsages {
    public static void main(String[] args) {
        List<Dish> menu = new ArrayList<>();
        menu.add(new Dish("food1", true, 100, "FISH"));
        menu.add(new Dish("food2", false, 200, "MEAT"));
        menu.add(new Dish("food3", true, 300, "OTHER"));
        menu.add(new Dish("food4", true, 400, "FISH"));
        menu.add(new Dish("food5", false, 500, "MEAT"));
        menu.add(new Dish("food6", false, 600, "OTHER"));

        // 5.1 Filtering
        // filter 메서드는 Predicate(Boolean을 반환하는 함수)를 인수로 받아 일치하는 요소를 포함하는 스트림을 반환한다.
        // distinct 메서드는 고유 요소로 이루어진 스트림을 반환한다(객체의 hashCode, equals로 결정)
        List<Integer> numbers = Arrays.asList(1, 2, 1 ,3 ,3 ,2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        // 5.2 Stream Slicing: 스트림의 요소를 선택하거나 스킵하는 방법
        // Predicate 를 이용한 Slicing
        // limit을 활용한 스트림 축소
        List<Dish> dishes = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());

        // 요소 건너뛰기(처음 n개 요소 skip)
        List<Dish> skipDishes = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());

        // Quiz 5.1
        List<Dish> meatDishes = menu.stream()
                .filter(dish -> "MEAT".equals(dish.getType()))
                .limit(2)
                .collect(Collectors.toList());

        // 5.3 Mapping 특정 데이터를 선택하는 기능
        // 5.3.1 스트림의 각 요소에 함수 적용하기
        // 함수를 인수로 받는 map 메서드는, 인수로 제공된 함수를 적용한 결과를 반환한다.
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

        List<Integer> dishNameLength = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());

        // 5.3.2 스트림 평면화
        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<String[]> res = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
        for(String[] temp : res) {
            Arrays.stream(temp).forEach(System.out::print);
        }
        System.out.println();

        // --> flatMap 사용: 스트림의 각 값을 다른 스트림으로 만든 다음에 모든 스트림을 하나의 스트림으로 연결하는 기능
        List<String> uniqueCharacters = words.stream()
                .map(word -> word.split(""))
                // 생성된 스트림을 하나의 스트림으로 평면화
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        uniqueCharacters.forEach(System.out::print);
        System.out.println();

        // Quiz 5.2.1 Mapping
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> powerdNums = nums.stream()
                .map(num -> num * num)
                .collect(Collectors.toList());
        powerdNums.forEach(System.out::print);
        System.out.println();

        // Quiz 5.2.2
        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);
        List<int[]> pairs = num1.stream()
                .flatMap(i -> num2.stream()
                        .filter(j -> (i + j) % 3 == 0) // Quiz 5.2.3
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairs.forEach(
                pair -> System.out.print(pair[0] + "" + pair[1] + " : ")
        );
    }
}