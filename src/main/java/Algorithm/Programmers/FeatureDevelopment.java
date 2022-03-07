package Algorithm.Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeatureDevelopment {
    public static void main(String[] args){
        int[] arr = solution(new int[]{93,30,55}, new int[]{1, 30, 5});
        Arrays.stream(arr).forEach(System.out::println);
    }

    // 7 3 9 -> 7 7 9 = 2, 1
    // 5 10 1 1 20 1 -> 5 10 10 10 20 20 -> 1 3 2
    public static int[] solution(int[] progresses, int[] speeds) {
        List<Integer> anw = new ArrayList<>();
        int[] calculateRemainingDays = new int[progresses.length];

        for(int i=0;i <progresses.length; i++){
            calculateRemainingDays[i] = (100 - progresses[i]) % speeds[i] == 0 ? (100 - progresses[i]) / speeds[i] : (100 - progresses[i]) / speeds[i] + 1;
        }

        int biggestRemainingDays = calculateRemainingDays[0], initialCount = 0;
        for(int remainingDays : calculateRemainingDays){
            if(biggestRemainingDays >= remainingDays){
                initialCount++;
            }
            else{
                anw.add(initialCount);
                initialCount = 1;
                biggestRemainingDays = remainingDays;
            }
        }

        anw.add(initialCount);

        return anw.stream().mapToInt(i->i).toArray();
    }
}
