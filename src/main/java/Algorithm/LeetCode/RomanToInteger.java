package Algorithm.LeetCode;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static void main(String[] args){
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));
    }

    public static int romanToInt(String s) {
        /*
        I             1
        V             5
        X             10
        L             50
        C             100
        D             500
        M             1000
        I -> V, X
        X -> L, C
        C -> D, M 앞에 있을 경우 - 처리
         */
        Map<String, Integer> map = new HashMap<>();
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);

        int total = 0;

        for(int index=0; index < s.length(); index++){
            if(s.charAt(index) == 'I' || s.charAt(index) == 'X' || s.charAt(index) == 'C'){
                // 4 9 40 90 400 900 처리
                if(index < s.length() -1 && map.containsKey(String.valueOf(s.charAt(index)) + s.charAt(index+1))){
                    total += map.get(String.valueOf(s.charAt(index)) + s.charAt(index + 1));
                    index++;
                }
                else{
                    total += map.get(String.valueOf(s.charAt(index)));
                }
            }
            else{
                total += map.get(String.valueOf(s.charAt(index)));
            }
        }

        return total;
    }
}
