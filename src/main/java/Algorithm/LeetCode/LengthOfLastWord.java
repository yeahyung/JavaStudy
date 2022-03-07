package Algorithm.LeetCode;

public class LengthOfLastWord {

    public static void main(String[] args){
        System.out.println(lengthOfLastWord("Hello World"));
        System.out.println(lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println(lengthOfLastWord("luff is joyboy"));
    }

    public static int lengthOfLastWord(String s) {
//        String[] splitWords = s.trim().split(" ");
//        return splitWords[splitWords.length-1].length();
        s = s.trim();
        int lastPos = s.lastIndexOf(" ");
        return s.length() -1 - lastPos;
    }
}
