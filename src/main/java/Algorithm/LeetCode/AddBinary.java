package Algorithm.LeetCode;

public class AddBinary {

    public static void main(String[] args){
        System.out.println(addBinary("11", "1"));
        System.out.println(addBinary("1010", "1011"));
    }

    public static String addBinary(String a, String b) {
        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();

        StringBuffer answer = new StringBuffer();
        boolean isOver = false;

        for(int indexA = charA.length-1, indexB = charB.length-1; indexA >= 0 || indexB >= 0; indexA--,indexB--){
            int value = 0;
            if(isOver){
                value++;
            }

            if(indexA >= 0){
                value += charA[indexA] - 48;
            }
            if(indexB >= 0){
                value += charB[indexB] - 48;
            }

            if(value >= 2){
                answer.append(value - 2);
                isOver = true;
            }
            else{
                answer.append(value);
                isOver = false;
            }
        }

        if(isOver){
            answer.append(1);
        }

        return answer.reverse().toString();
    }
}
