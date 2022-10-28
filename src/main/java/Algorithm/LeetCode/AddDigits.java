package Algorithm.LeetCode;

import org.junit.Assert;
import org.junit.Test;

public class AddDigits {

    @Test
    public void addDigits() {
        Assert.assertEquals(addDigits(38), 2);
        Assert.assertEquals(addDigits(0), 0);
    }

    public int addDigits(int num) {
        while (num >= 10) {
            num = sumOfDigits(num);
        }

        return num;
    }

    public int sumOfDigits(int num) {
        int count = 0;
        while (num != 0) {
            count += num % 10;
            num = num / 10;
        }
        return count;
    }

}
