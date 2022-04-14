package ModernJava.Chapter2;

/*
동작 파라미터화: 아직은 어덯게 실행할 것인지 결정하지 않은 코드 블록
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum Color {RED, GREEN}

public class BehaviorParams {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple(Color.GREEN, 200));
        apples.add(new Apple(Color.GREEN, 210));
        apples.add(new Apple(Color.RED, 100));
        apples.add(new Apple(Color.RED, 120));

        List<Apple> greenApples = filterAppleByColor(apples, Color.GREEN);
        List<Apple> redApples = filterAppleByColor(apples, Color.RED);
        List<Apple> predicatedApples = filterApples(apples, new AppleGreenColorPredicate());
        printApples(apples, new AppleComplexPrinter());
        printApples(apples, new AppleSimplePrinter());

        // 5. 람다 표현식
        List<Apple> result = filterApples(apples, (Apple apple) -> Color.RED.equals(apple.getColor()));
        printApples(result, new AppleComplexPrinter());

        // 6
        List<Apple> redAppless = filter(apples, (Apple apple) -> Color.RED.equals(apple.getColor()));
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
        evenNumbers.forEach(System.out::println);
    }

    // 1. Green Apple 필터링
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (Color.GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }

        return result;
    }

    // 2. 위의 Green Apple 필터링이 있는 상황에서 Red Apple 필터링을 원하는 경우?
    // 색을 파라미터화 하자.
    public static List<Apple> filterAppleByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }

        return result;
    }

    // 3. 색 이외에 가벼운 사과와 무거운 사과를 구분 요청할 경우
    public static List<Apple> filterAppleByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }

        return result;
    }

    // 4. 위의 코드로도 해결할 수 있지만 필터링 조건을 적용하는 대부분의 코드가 중복된다. DRY(Don't Repeat Yourself) 원칙을 어긴다.
    // 따라서 동작을 파라미터화 한다.
    // Predicate: 참 또는 거짓을 반환하는 함수
    // 선택 조건을 결정하는 인터페이스를 정의한다. = ApplePredicate
    // 전략 디자인 패턴: 각 알고리즘을 캡슐화하여 정의한 후, 런타임에 알고리즘을 선택한다.
    // 전달한 ApplePredicate 객체에 의해 filterApples 메서드의 동작이 결정된다. = 동작의 파라미터화
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }

    // Quiz 2-1
    public static void printApples(List<Apple> inventory, ApplePrinter p) {
        for (Apple apple : inventory) {
            System.out.println(p.printApple(apple));
        }
    }

    // 하지만 위의 방법들은 여러 클래스를 구현해서 인스턴스화해야한다.
    // 새로운 동작을 전달하려면 매번 인터페이스를 구현한 클래스를 구현한 뒤 인스턴스화해야한다.
    // 익명 클래스를 통해 줄일 수 있지만 람다 표현식으로 하면 더 가독성있고 깔끔하다.

    /// 6. 리스트 형식으로 추상화
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e: list) {
            if (p.test(e)) {
                result.add(e);
            }
        }

        return result;
    }
}
