package Algorithm.Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockExam {
    public static void main(String[] args) {
        Arrays.stream(new MockExam().solution(new int[]{1, 2, 3, 4, 5})).
                forEach(System.out::println); // 1
        Arrays.stream(new MockExam().solution(new int[]{1, 3, 2, 4, 2})).
                forEach(System.out::println); // 1, 2, 3
    }

    public int[] solution(int[] answers) {
        List<Integer> answer = new ArrayList<>();

        // 1, 2, 3, 4, 5
        // 2, 1, 2, 3, 2, 4, 2, 5
        // 3, 3, 1, 1, 2, 2, 4, 4, 5, 5
        int[][] answerList = new int[][]{{1, 2, 3, 4,5},
            {2, 1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
        int[] correctCount = new int[3];

        for(int i=0; i<answers.length; i++) {
            for(int j=0; j<3; j++) {
                if(answers[i] == answerList[j][i%answerList[j].length]) {
                    correctCount[j]++;
                }
            }
        }

        int max = 0;
        for(int i=0; i<3; i++){
            if(correctCount[i] >= max) {
                max = correctCount[i];
            }
        }

        for(int i=0; i<3; i++) {
            if(correctCount[i] == max) {
                answer.add(i+1);
            }
        }

        return answer.stream().mapToInt(i->i).toArray();
    }
}
