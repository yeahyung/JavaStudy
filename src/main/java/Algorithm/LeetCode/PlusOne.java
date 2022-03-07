package Algorithm.LeetCode;

public class PlusOne {

    public static void main(String[] args){
        printArray(plusOne(new int[]{1,2,3}));
        printArray(plusOne(new int[]{4,3,2,1}));
        printArray(plusOne(new int[]{0}));
        printArray(plusOne(new int[]{9}));
        printArray(plusOne(new int[]{9,9}));
        printArray(plusOne(new int[]{1,9,9}));
        printArray(plusOne(new int[]{1,8,9}));
    }

    public static int[] plusOne(int[] digits) {
        if(digits[digits.length-1] == 9){
            int index = 0;
            while(index < digits.length && digits[digits.length -1 -index] == 9){
                digits[digits.length -1 -index] = 0;
                index++;
            }
            
            if(index == digits.length){
                int[] copyArray = new int[index + 1];
                copyArray[0] = 1;
                for(int i=1; i< index + 1; i++){
                    copyArray[i] = digits[i-1];
                }
                return copyArray;
            }
            else{
                digits[digits.length -1 -index] += 1;
            }
            
        }
        else{
            digits[digits.length-1] += 1;
        }

        return digits;
    }

    public static void printArray(int[] digits){
        for(int num : digits){
            System.out.print(num);
        }
        System.out.println();
    }
}
