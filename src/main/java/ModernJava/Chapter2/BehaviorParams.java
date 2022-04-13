package ModernJava.Chapter2;

/*
동작 파라미터화: 아직은 어덯게 실행할 것인지 결정하지 않은 코드 블록
 */

import java.util.ArrayList;
import java.util.List;

enum Color {RED, GREEN}

class Apple {
    Color color;
    int weight;

    public Apple() {
        ;
    }

    public Apple(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }
}

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
}
