package Algorithm.Programmers;

import java.util.HashMap;
import java.util.Map;

public class nonCompletedParticipant {
    public static void main(String[] args){
        System.out.println(solution(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"}));
    }

    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> participantCount = new HashMap<>();

        for(String player : participant){
            participantCount.put(player, participantCount.getOrDefault(player, 0) + 1);
        }

        for(String player : completion){
            participantCount.put(player, participantCount.get(player) - 1);
        }

        for(String key : participantCount.keySet()){
            if(participantCount.get(key) != 0){
                answer = key;
            }
        }

        return answer;
    }
}
