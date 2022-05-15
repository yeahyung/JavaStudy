package ModernJava.Chapter6;

import ModernJava.Chapter4.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CollectDataUsingStream {
    public static void main(String[] args) {
        /*
        Collectors 에서 제공하는 메서드의 기능
        1. 스트림의 요소를 하나의 값으로 리듀스하고 요약
        2. 요소 그룹화
        3. 요소 분할
         */

        List<Dish> menu = new ArrayList<>();
        menu.add(new Dish("food1", true, 100, "FISH"));
        menu.add(new Dish("food2", false, 200, "MEAT"));
        menu.add(new Dish("food3", true, 300, "OTHER"));
        menu.add(new Dish("food4", true, 400, "FISH"));
        menu.add(new Dish("food5", false, 500, "MEAT"));
        menu.add(new Dish("food6", false, 600, "OTHER"));

        //long howManyDishes = menu.stream().collect(Collectors.counting());
        long howManyDishes = menu.stream().count();

        // 칼로리가 가장 높은 요리 찾기
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream()
                .collect(maxBy(dishCaloriesComparator));

        // 총 칼로리 계산하기
        int totalCalories = menu.stream()
                .collect(summingInt(Dish::getCalories));

        // 칼로리 평균 계산하기
        double avgCalories = menu.stream()
                .collect(averagingInt(Dish::getCalories));

        // 요약본 -> count, sum, min, average, max 제공
        IntSummaryStatistics menuStatistics = menu.stream()
                .collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);
    }
}
