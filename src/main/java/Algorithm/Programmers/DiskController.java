package Algorithm.Programmers;

import java.util.PriorityQueue;
import java.util.TreeMap;

public class DiskController {
    public static void main(String[] args){
        System.out.println(solution(new int[][]{{0,3},{1,9},{2,6}})); // 9
        System.out.println(solution(new int[][]{{0,3},{1,9},{2,6}, {1, 8}})); //
    }

    public static int solution(int[][] jobs) {
        int answer = 0;

        // 작업을 할 수 있는 상황에 가장 짧게 걸리는 작업을 실행
        TreeMap<Integer, PriorityQueue<Integer>> jobList = new TreeMap<>();
        for(int[] job : jobs){
            PriorityQueue<Integer> jobValue = new PriorityQueue<>();
            if(jobList.containsKey(job[0])){
                jobValue = jobList.get(job[0]);
            }
            jobValue.add(job[1]);
            jobList.put(job[0], jobValue);
        }

        int currentTime = 0;
        while(jobList.size() != 0){
            if(jobList.firstKey() <= currentTime){
                // 사용 가능한 것 모두 key의 value 모두 검사 & min value 추출
                int minJobKey = jobList.firstKey();
                int minJobValue = jobList.firstEntry().getValue().peek();
                for(int key : jobList.keySet()){
                    if(key <= currentTime && minJobValue > jobList.get(key).peek()){
                        minJobKey = key;
                        minJobValue = jobList.get(key).peek();
                    }
                }

                if(jobList.get(minJobKey).size() != 1){
                    jobList.get(minJobKey).poll();
                }
                else{
                    jobList.remove(minJobKey);
                }
                answer = answer + (currentTime - minJobKey) + minJobValue;
                System.out.println(answer);
                currentTime += minJobValue;
            }
            else{
                currentTime++;
            }
        }

        return answer/jobs.length;
    }
}
