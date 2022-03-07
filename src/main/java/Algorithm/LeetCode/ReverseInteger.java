package Algorithm.LeetCode;

/*
Given a signed 32-bit integer x, return x with its digits reversed.
If reversing x causes the value to go outside the signed 32-bit
 integer range [-231, 231 - 1], then return 0.
 */
public class ReverseInteger {

    public static void main(String[] args){
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(0));
    }

    public static int reverse(int x) {
        long answer=0;
        int mod;
        while(x != 0){
            mod = x % 10;
            x = x / 10;
            answer = answer * 10 + mod;
        }

        if(answer > Integer.MAX_VALUE || answer < Integer.MIN_VALUE)
            return 0;

        return (int) answer;
    }
}
