package ModernJava.Chapter4;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Stream {
    /*
    스트림: 데이터 컬렉션을 처리하는 기능
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
    }
}
