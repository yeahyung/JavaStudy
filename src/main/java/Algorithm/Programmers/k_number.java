package Algorithm.Programmers;

import java.util.*;

public class k_number {
    public static void main(String[] args) {
        Arrays.stream(solution(new int[]{1,5,2,6,3,7,4}, new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}})).
                forEach(System.out::println); // 5,6,3
    }

    public static int[] solution(int[] array, int[][] commands) {
        int[] anw = new int[commands.length];

        for(int i = 0; i < commands.length; i++) {
            int[] tempArr = new int[commands[i][1] - commands[i][0] + 1];
            for(int j = 0; j < commands[i][1] - commands[i][0] + 1; j++) {
                tempArr[j] = array[j + commands[i][0] - 1];
            }
            Arrays.sort(tempArr);
            anw[i] = tempArr[commands[i][2] - 1];
        }
        return anw;
    }
}
