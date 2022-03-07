package Algorithm.Programmers;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Printer {

    public static void main(String[] args){
        System.out.println(solution(new int[]{2, 1, 3, 2}, 2));
        System.out.println(solution(new int[]{1, 1, 9, 1, 1, 1}, 0));
    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;

        int totalLength = priorities.length-1;
        Queue<Integer> queue = new LinkedList<>();
        Integer[] priority = new Integer[priorities.length];
        for(int i = 0; i< priorities.length; i++){
            priority[i] = priorities[i];
            queue.add(priorities[i]);
        }
        Arrays.sort(priority, Collections.reverseOrder());

        while(!queue.isEmpty()){
            int num = queue.poll();
            // print 해야하는 경우
            if(num == priority[answer]){
                // target인 경우
                if(location==0){
                    return answer;
                }
                answer++;
                totalLength--;
            }
            // print 하지 말아야 하는 경우, queue에 다시 넣는다
            else{
                queue.add(num);
            }
            location = location==0? totalLength:location-1;
        }
        return answer;
    }
}
