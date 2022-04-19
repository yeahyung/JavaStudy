package Algorithm.Programmers;

public class H_Index {

    public static void main(String[] args) {
        System.out.println(new H_Index().solution(new int[]{3, 0, 6, 1, 5})); // 3
        // 1 1 0 1 0 2 -> 5 4 3 3 2 2
        System.out.println(new H_Index().solution(new int[]{0, 0, 100})); // 1
        System.out.println(new H_Index().solution(new int[]{0})); // 0
        System.out.println(new H_Index().solution(new int[]{0, 0, 0})); // 0
        System.out.println(new H_Index().solution(new int[]{1})); // 1
        System.out.println(new H_Index().solution(new int[]{2})); // 1
        System.out.println(new H_Index().solution(new int[]{1, 1})); // 1
        System.out.println(new H_Index().solution(new int[]{1, 1, 1})); // 1
        System.out.println(new H_Index().solution(new int[]{2, 2})); // 1
        System.out.println(new H_Index().solution(new int[]{2, 2, 2})); // 1
    }

    public int solution(int[] citations) {
        int answer = 0;
        // index: 인용된 횟수, 인용된 횟수는 10000까지 가질 수 있지만, 논문의 최대수 이상 인용된 것은 논문의 최대수로 가정해도 됨
        int[] countArr = new int[citations.length + 1];

        for(int i=0; i < citations.length; i++) {
            if(citations[i] >= citations.length) {
                countArr[citations.length]++;
            } else {
                countArr[citations[i]]++;
            }
        }

        for(int i=countArr.length-1; i >= 1; i--) {
            countArr[i-1] += countArr[i];
        }

        for(int i=0; i< countArr.length; i++) {
            if(i <= countArr[i]){
                answer = i;
            }
        }

        return answer;
    }
}
