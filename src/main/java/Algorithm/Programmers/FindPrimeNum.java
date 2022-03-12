package Algorithm.Programmers;

public class FindPrimeNum {
    public static int answer = 0;

    public static void main(String[] args) {
//        System.out.println(new FindPrimeNum().solution("17")); // 3
//        System.out.println(new FindPrimeNum().solution("011")); // 2
//        System.out.println(new FindPrimeNum().solution("0")); // 0
//        System.out.println(new FindPrimeNum().solution("1111")); // 1
        System.out.println(new FindPrimeNum().solution("002")); // 18
        System.out.println(new FindPrimeNum().solution("1231")); // 18
    }

    public int solution(String numbers) {
        // 1 ~ 9999999
        answer = 0;
        boolean[] isNotPrime = new boolean[(int) Math.pow(10.0, numbers.length())];
        isNotPrime[0] = true;
        isNotPrime[1] = true;
        for(int i=2; i<=(int) Math.sqrt(Math.pow(10.0, numbers.length())); i++) {
            for(int j=i+i; j < (int) Math.pow(10.0, numbers.length()); j += i) {
                isNotPrime[j] = true;
            }
        }

        int[] nums = new int[numbers.length()];
        for(int i=0; i<numbers.length(); i++) {
            nums[i] = numbers.charAt(i) - '0';
        }

        // 모든 경우의 수 구하기 by dfs
        for(int i=1; i<=numbers.length(); i++) {
            int[] output = new int[i];
            boolean[] isVisit = new boolean[numbers.length()];
            permutation(nums, output, isVisit, 0, numbers.length(), i, isNotPrime);
        }

        return answer;
    }

    public static void permutation(int[] nums, int[] output, boolean[] isVisit, int depth, int length, int count, boolean[] isNotPrime) {
        if(count==0) {
            int targetNum = 0;
            for(int i=0; i<output.length; i++){
                targetNum = targetNum * 10 + output[i];
            }
            if(!isNotPrime[targetNum]) {
                isNotPrime[targetNum] = true;
                answer++;
            }
            return;
        }
        for(int i=0; i<length; i++) {
            if(!isVisit[i]) {
                isVisit[i] = true;
                output[depth] = nums[i];
                permutation(nums, output, isVisit, depth+1, length, count-1, isNotPrime);
                isVisit[i] = false;
            }
        }
    }

}
