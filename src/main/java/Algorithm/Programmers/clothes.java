package Algorithm.Programmers;

import java.util.HashMap;
import java.util.Map;

public class clothes {
    public static void main(String[] args){
        System.out.println(solution(new String[][]{{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}}));
    }

    public static int solution(String[][] clothes) {
        int answer = 1;

        Map<String, Integer> typeCount = new HashMap<>();
        for(String[] clothesType : clothes){
            // clothesType[1] = type
            typeCount.put(clothesType[1], typeCount.getOrDefault(clothesType[1], 0) + 1);
        }

        // max 2^31 - 1 = integer 범위
        for(Integer value : typeCount.values()){
            answer = answer * (value+1);
        }

        return answer-1;
    }
}
