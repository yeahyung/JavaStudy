package Algorithm.Programmers;

import java.util.HashMap;
import java.util.Map;

public class PhoneBook {

    public static void main(String[] args){
        System.out.println(solution(new String[]{"119", "97674223", "1195524421"}));
    }

    public static boolean solution(String[] phone_book) {
        boolean answer = true;

        Map<String, Integer> phoneBookMap = new HashMap<>();
        for(String phoneNumber : phone_book){
            if(phoneBookMap.containsKey(phoneNumber)){
                answer = false;
                break;
            }

            for(int i=0; i<phoneNumber.length()-1; i++){
                phoneBookMap.put(phoneNumber.substring(0, i+1), 1);
            }
        }
        for(String phoneNumber : phone_book){
            if (phoneBookMap.containsKey(phoneNumber)) {
                answer = false;
                break;
            }
        }

        return answer;
    }
}
