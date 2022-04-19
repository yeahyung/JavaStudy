package ModernJava.Chapter3;

import ModernJava.Chapter2.Apple;
import ModernJava.Chapter2.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import static java.util.Comparator.comparing;

public class Lambda {
    // 람다 표현식: 메서드로 전달할 수 있는 익명 함수를 단순화한 것
    // 함수: 특정 클래스에 종속되는 것을 메서드라한다, 함수는 종속되지 않음 따라서 람다는 함수
    public static void main(String[] args) {
        //                             람다 파라미터                람다 바디
        Comparator<Apple> byWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

        /*
        (parameters) -> expression
        (parameters) -> { statements; }
         */

        // 함수형 인터페이스: 하나의 추상 메서드만 지정하는 인터페이스
        // 람다 표현식으로 함수형 인터페이스의 추상 메서드 구현을 직접 전달한다.

        // 함수 디스크립터: 람다 표현식의 시그니처를 서술하는 메서드
        // 람다 표현식의 시그니처: 함수형 인터페이스의 추상 메서드 시그니쳐


        // 람다 표현식을 메서드 참조로 바꾸기
        List<String> str = Arrays.asList("a", "b", "A", "B");
        //str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        str.sort(String::compareToIgnoreCase);

        // Quiz 3-6
        ToIntFunction<String> stringToInt = (String s) -> Integer.parseInt(s);
        Function<String, Integer> stringToInteger = Integer::parseInt;

        BiPredicate<List<String>, String> contain = (list, element) -> list.contains(element);
        BiPredicate<List<String>, String> contains2 = List::contains;;

        // Predicate<String> startsWithNumber = (String string) -> this.startsWithNumber(string);
        // Predicate<String> startsWithNumber2 = this::startsWithNumber2;

        // 생성자 참조
        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();

        Supplier<Apple> c2 = () -> new Apple();
        Apple a2 = c2.get();

        Function<Integer, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(110);

        Function<Integer, Apple> c4 = (weight) -> new Apple(weight);
        Apple a4 = c4.apply(110);

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);
        apples.forEach(s -> System.out.println(s.getWeight()));

        // sort 코드
        // 1. 코드 전달
        apples.sort(new AppleComparator());

        // 2. 익명 함수
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        // 3. 람다 표현식 사용
        apples.sort((Apple apple1, Apple apple2) -> apple1.getWeight().compareTo(apple2.getWeight()));
        apples.sort(comparing(apple -> apple.getWeight()));

        // 4. 메서드 참조 사용
        apples.sort(comparing(Apple::getWeight));

        // Comperator 연결
        apples.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColorToString));

        // Function 조합
        // f.andThen(g) -> g(f(x))
        // f.compose(g) -> f(g(x))
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline = addHeader
                .andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        System.out.println(transformationPipeline.apply("hey labda"));
    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer i : list) {
            result.add(f.apply(i));
        }

        return result;
    }
}
