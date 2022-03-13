package Algorithm.Programmers;

import java.util.Arrays;

public class Carpet {
    public static void main(String[] args) {
        Arrays.stream(new Carpet().solution(10, 2)).forEach(System.out::println);
        Arrays.stream(new Carpet().solution(8, 1)).forEach(System.out::println);
        Arrays.stream(new Carpet().solution(24, 24)).forEach(System.out::println);
    }

    public int[] solution(int brown, int yellow) {
        int totalCount = brown + yellow;
        int div = 0, answer = 0;
        for(int i=(int) Math.sqrt(totalCount); i< totalCount; i++) {
            if(totalCount % i == 0) {
                if((i-2)*(totalCount/i -2)== yellow) {
                    div = i;
                    answer = totalCount / i;
                    break;
                }
            }
        }

        return div >= answer ? new int[]{div, answer} : new int[]{answer, div};
    }
}
