package Algorithm.LeetCode;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumberOf1Bits {

    @Test
    public void numberOf1Bits() {
        Assertions.assertEquals(hammingWeight(00000000000000000000000000001011), 3);
        Assertions.assertEquals(hammingWeight(00000000000000000000000010000000), 1);
        //Assert.assertEquals(hammingWeight(11111111111111111111111111111101), 31);
    }

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}
