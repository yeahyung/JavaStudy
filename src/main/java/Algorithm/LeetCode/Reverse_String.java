package Algorithm.LeetCode;

public class Reverse_String {
    public static void main(String[] args){
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
    }

    static void reverseString(char[] s){
        int index = s.length;
        for (int i=0;i<index/2;i++){
            char temp = s[i];
            s[i] = s[index-1-i];
            s[index-1-i]=temp;
        }

        for(char item : s)
            System.out.print(item);
    }
}
