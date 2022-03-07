package Algorithm.LeetCode;

public class ImplementStrStr {

    public static void main(String[] args){
        System.out.println(strStr("a", "a"));
        System.out.println(strStr("hello", "ll"));
        System.out.println(strStr("aaaaa", "bba"));
        System.out.println(strStr("", ""));
    }

    public static int strStr(String haystack, String needle) {
        //return haystack.indexOf(needle);
        if("".equals(needle)) {
            return 0;
        }

        char[] strToChar = haystack.toCharArray();
        for(int index=0; index<= strToChar.length-needle.length(); index++){
            int needleIndex = 0;
            while(needleIndex < needle.length() && strToChar[index + needleIndex] == needle.charAt(needleIndex)){
                needleIndex++;
            }

            if(needleIndex == needle.length()){
                return index;
            }
        }
        return -1;
    }
}
