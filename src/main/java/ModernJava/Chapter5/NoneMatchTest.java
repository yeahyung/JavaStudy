package ModernJava.Chapter5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Temp {
    int value;
    Boolean isInternal;
}

public class NoneMatchTest {
    public static void main(String[] args) {
        List<Integer> internalList = Arrays.asList(1, 2, 3, 4);
        List<Integer> commonList = Arrays.asList(3, 4, 5, 6);

        internalList.stream()
                .filter(internal ->
                        commonList.stream()
                                .noneMatch(common ->
                                        common.equals(internal)))
                .collect(Collectors.toList()).forEach(System.out::print);

        System.out.println();

        commonList.stream()
                .filter(internal ->
                        internalList.stream()
                                .noneMatch(common ->
                                        common.equals(internal)))
                .collect(Collectors.toList()).forEach(System.out::print);
    }
}
