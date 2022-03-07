package Algorithm.LeetCode;

/*
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
 */
public class ValidPalindrome {
    public static void main(String[] args){
        System.out.println(isPalindrome(new String("A5man, a plan, a canal: Panam5a")));
    }

//    public static boolean isPalindrome(String s){
//        List<Character> charList = new ArrayList<>();
//        for(int i=0; i<s.length(); i++){
//            if(48 <= s.charAt(i) && s.charAt(i) <= 57){
//                //System.out.println("num:" + s.charAt(i));
//                charList.add(s.charAt(i));
//            }
//            else if(65 <= s.charAt(i) && s.charAt(i) <= 90){
//                //System.out.println("A: " +Character.toLowerCase(s.charAt(i)));
//                charList.add(Character.toLowerCase(s.charAt(i)));
//            }
//            else if(97 <= s.charAt(i) && s.charAt(i) <= 122){
//                //System.out.println("a:" + s.charAt(i));
//                charList.add(s.charAt(i));
//            }
//        }
//
//        //System.out.println(charList);
//        for(int i=0;i<charList.size()/2; i++){
//            if(!charList.get(i).equals(charList.get(charList.size()-1-i)))
//                return false;
//        }
//
//        return true;
//    }

    public static boolean isPalindrome(String s){
        char[] c = s.toCharArray();
        for (int i = 0, j = c.length - 1; i < j; ) {
            if (!Character.isLetterOrDigit(c[i])) i++;
            else if (!Character.isLetterOrDigit(c[j])) j--;
            else if (Character.toLowerCase(c[i++]) != Character.toLowerCase(c[j--]))
                return false;
        }
        return true;
    }
}
