package Algorithm.Programmers;

import java.util.Arrays;

public class StockPrice {

    public static void main(String[] args){
        Arrays.stream(solution(new int[]{1,2,3,2,3})).forEach(System.out::println); // 4, 3, 1, 1, 0
        Arrays.stream(solution(new int[]{1,2,4,2,3,4})).forEach(System.out::println); // 5, 4, 1, 2, 1, 0
        Arrays.stream(solution(new int[]{1, 2, 3, 2, 3, 1})).forEach(System.out::println); // 5, 4, 1, 2, 1, 0
    }
    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for(int i=0; i<prices.length-1; i++){
            for(int j=i+1; j<prices.length; j++){
                answer[i] += 1;
                if(prices[i] > prices[j]){
                    break;
                }
            }
        }

        return answer;
    }
}
