package Algorithm.LeetCode;

public class LongestCommonPrefix {

    public static void main(String[] args){
        System.out.println(longestCommonPrefix(new String[]{"cir","car"}));
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }

    public static String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];

       for(int index = 1; index < strs.length; index++){
           // indexOf() == 0 --> 무조건 맨 앞에 있어야함
            while(strs[index].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length()-1);
                if(prefix.isEmpty()){
                    prefix = "";
                }
            }
       }

        return prefix;
    }
}