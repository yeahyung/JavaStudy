package ModernJava.Chapter3;

import ModernJava.Chapter2.Apple;

import java.util.Comparator;

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
    }
}
