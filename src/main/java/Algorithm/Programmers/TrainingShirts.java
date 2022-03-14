package Algorithm.Programmers;

import java.util.Arrays;

public class TrainingShirts {
    public static void main(String[] args) {
        System.out.println(new TrainingShirts().solution(5, new int[]{2, 4}, new int[]{1, 3, 5})); // 5
        System.out.println(new TrainingShirts().solution(5, new int[]{2, 4}, new int[]{3})); // 4
        System.out.println(new TrainingShirts().solution(3, new int[]{3}, new int[]{1})); // 2
        System.out.println(new TrainingShirts().solution(3, new int[]{1}, new int[]{1})); // 3
        System.out.println(new TrainingShirts().solution(3, new int[]{1}, new int[]{3})); // 2
        System.out.println(new TrainingShirts().solution(5, new int[]{2, 3}, new int[]{3, 4})); // 4
        System.out.println(new TrainingShirts().solution(7, new int[]{2, 3, 4}, new int[]{1, 2, 3, 6})); // 6
        System.out.println(new TrainingShirts().solution(30, new int[]{1, 2, 7, 9, 10, 11, 12, 14, 15, 16, 18, 20, 21, 24, 25, 27},
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 20, 22, 23, 24, 25, 26, 27, 28})); // 30
    }

    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;

        Arrays.sort(lost);
        Arrays.sort(reserve);
        int lostIndex = 0;
        for(int i=0; i< reserve.length; i++) {
            for(int j=0; j<lost.length;j++) {
                if(reserve[i] == lost[j]) {
                    answer++;
                    reserve[i] = -1;
                    lost[j] = -100;
                }
            }
        }
        for(int addIndex = 0; addIndex < reserve.length && lostIndex < lost.length; addIndex++) {
            while(lostIndex < lost.length && reserve[addIndex] - lost[lostIndex] > 1) {
                lostIndex++;

                if(lostIndex == lost.length)
                    return answer;
            }
            if(lost[lostIndex] - reserve[addIndex] > 1) {
                continue;
            }

            answer++;
            lost[lostIndex++] = -1;
        }

        return answer;
    }
}
