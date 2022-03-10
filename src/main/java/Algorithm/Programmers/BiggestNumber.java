package Algorithm.Programmers;

import java.util.PriorityQueue;

public class BiggestNumber {
    public static void main(String[] args) {
        System.out.println(
                new BiggestNumber().solution(new int[]{6, 10, 2})); // 6210
        System.out.println(
                new BiggestNumber().solution(new int[]{3, 30, 34, 5, 9})); // 9534330
        System.out.println(
                new BiggestNumber().solution(new int[]{3, 30, 33, 331, 332, 334, 5, 9})); // 9533433333233130
        System.out.println(
                new BiggestNumber().solution(new int[]{0, 0, 0, 0}));
        System.out.println(
                new BiggestNumber().solution(new int[]{40, 404}));
    }

    public String solution(int[] numbers) {
        StringBuilder stringBuilder = new StringBuilder();
        PriorityQueue<NumInfo> pq = new PriorityQueue<>();

        // 6 자리수로 강제 변환 후, 순서대로 append, 1000은 0을 제외하고 가장 낮은 우선순위를 갖기 때문에 굳이 6자리로 변환할 필요 없음
        for(int num : numbers) {
            int digitCount = String.valueOf(num).length();
            StringBuilder stringNumBuilder = new StringBuilder();
            for(int i = 0; i < 6 / digitCount; i++) {
                stringNumBuilder.append(num);
            }
            pq.add(new NumInfo(Integer.parseInt(stringNumBuilder.toString()), num));
        }

        while(!pq.isEmpty()) {
            NumInfo numInfo = pq.poll();
            stringBuilder.append(numInfo.realNum);
        }

        if(stringBuilder.charAt(0) == '0')
            return "0";
        return stringBuilder.toString();
    }
}

class NumInfo implements Comparable<NumInfo> {
    int sixDigitNum;
    int realNum;

    public NumInfo(int sixDigitNum, int realNum) {
        this.sixDigitNum = sixDigitNum;
        this.realNum = realNum;
    }

    @Override
    public int compareTo(NumInfo numInfo){
        return this.sixDigitNum <= numInfo.sixDigitNum ? 1 : -1;
    }
}
