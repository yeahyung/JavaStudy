package Algorithm.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Palindrome {
    public static void main(String[] args){
        System.out.println(isPalindrome(122));
        System.out.println(isPalindrome(11));
    }

    public static boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }else if (x<10){
            return true;
        }

        List<Integer> intToList = new ArrayList<>();
        while(x != 0){
            intToList.add(x%10);
            x = x / 10;
        }

        for(int i=0;i<intToList.size()/2;i++){
            if(!intToList.get(i).equals(intToList.get(intToList.size()-i-1)))
                return false;
        }

        return true;
    }
}
