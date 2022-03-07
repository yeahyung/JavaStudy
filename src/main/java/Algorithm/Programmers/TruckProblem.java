package Algorithm.Programmers;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class TruckProblem {
    public static void main(String[] args){
//        System.out.println(solution(2, 10, new int[]{7,4,5,6})); // 8
//        System.out.println(solution(100, 100, new int[]{10})); // 101
//        System.out.println(solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10})); // 110
System.out.println(solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10,1})); // 120
//        System.out.println(solution(1, 1, new int[]{1})); // 2
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        int currentTotalWeight = 0;

        // truck weight, 다리 위에 올라간 시간
        Queue<TruckInfo> onBridgeTruckList = new LinkedList<>();
        for(int truckWeight : truck_weights){
           answer++;

           // 맨 앞 트럭이 빠져나갈 수 있는 조건인지 확인
           TruckInfo firstTruckOnBridge = onBridgeTruckList.peek();
           if(Objects.nonNull(firstTruckOnBridge)){
               if(firstTruckOnBridge.time + bridge_length == answer){
                   onBridgeTruckList.poll();
                   currentTotalWeight -= firstTruckOnBridge.weight;
               }
           }

           // 다리 위에 올라갈 수 없는 경우
           if(currentTotalWeight + truckWeight > weight){
                while(currentTotalWeight + truckWeight > weight){
                    answer++;
                    TruckInfo truckOnBridge = onBridgeTruckList.peek();
                    if(Objects.nonNull(truckOnBridge)){
                        if(truckOnBridge.time + bridge_length == answer){
                            onBridgeTruckList.poll();
                            currentTotalWeight -= truckOnBridge.weight;
                        }
                    }
                }
           }

           onBridgeTruckList.add(new TruckInfo(truckWeight, answer));
           currentTotalWeight += truckWeight;
        }

        while(!onBridgeTruckList.isEmpty()){
            answer++;
            TruckInfo lastTruck = onBridgeTruckList.peek();
            if(lastTruck.time + bridge_length == answer){
                onBridgeTruckList.poll();
            }
        }

        return answer;
    }

    static class TruckInfo{
        int weight;
        int time;

        TruckInfo(int weight, int time){
            this.weight = weight;
            this.time = time;
        }
    }
}
