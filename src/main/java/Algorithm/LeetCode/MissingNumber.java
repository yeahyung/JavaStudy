package Algorithm.LeetCode;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MissingNumber {

    @Test
    public void missingNumber() {
        Assertions.assertEquals(missingNumber(new int[]{3, 0, 1}), 2);
        Assertions.assertEquals(missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}), 8);
    }

    public int missingNumber(int[] nums) {
        int numCount = nums.length;
        int totalSum = numCount * (numCount + 1) / 2;

        for (int num : nums) {
            totalSum -= num;
        }
        return totalSum;
    }
}
