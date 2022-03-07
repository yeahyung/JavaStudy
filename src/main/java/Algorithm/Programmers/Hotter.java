package Algorithm.Programmers;

import java.util.Map;
import java.util.TreeMap;

public class Hotter {
    public static void main(String[] args){
        System.out.println(solution(new int[]{1,2,3,9,10,12}, 7)); // 2
    }

    public static int solution(int[] scoville, int K) {
        int answer = 0;

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for(int scov : scoville){
            treeMap.put(scov, treeMap.getOrDefault(scov, 0) + 1);
        }

        while(treeMap.firstKey() < K){
            // 더 이상 섞을 수 없는 경우
            if(treeMap.size() < 2){
                return -1;
            }
            answer++;
            Map.Entry<Integer, Integer> firstElement = treeMap.firstEntry();
            if(firstElement.getValue() > 1){
                // 동일한 맵기 섞는 경우
                treeMap.put(firstElement.getKey() * 3, treeMap.getOrDefault(firstElement.getKey() * 3, 0) + 1);
                if(firstElement.getValue() == 2){
                    treeMap.remove(firstElement.getKey());
                }
                else{
                    treeMap.put(firstElement.getKey(), firstElement.getValue() -2);
                }
            }
            else{
                treeMap.remove(firstElement.getKey());
                Map.Entry<Integer, Integer> secondElement = treeMap.firstEntry();
                treeMap.put(firstElement.getKey() + secondElement.getKey() * 2, treeMap.getOrDefault(firstElement.getKey() + secondElement.getKey() * 2, 0) + 1);
                if(secondElement.getValue() == 1){
                    treeMap.remove(secondElement.getKey());
                }
                else{
                    treeMap.put(secondElement.getKey(), secondElement.getValue() - 1);
                }
            }
        }

        return answer;
    }
}
