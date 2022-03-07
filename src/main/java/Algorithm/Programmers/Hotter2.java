package Algorithm.Programmers;

import java.util.PriorityQueue;

public class Hotter2 {
    public static void main(String[] args){
        System.out.println(solution(new int[]{1,2,3,9,10,12}, 7)); // 2
    }

    public static int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> scovList = new PriorityQueue<>();
        for(int scov : scoville){
            scovList.add(scov);
        }

        while(scovList.size() > 1 && scovList.peek() < K){
            int first = scovList.poll();
            int second = scovList.poll();

            scovList.add(first + second * 2);
            answer++;
        }

        if(scovList.peek() < K){
            return -1;
        }

        return answer;
    }
}
