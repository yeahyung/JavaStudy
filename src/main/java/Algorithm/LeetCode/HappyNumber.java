package Algorithm.LeetCode;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HappyNumber {
    @Test
    public void happyNumber() {
        Assertions.assertEquals(isHappy(19), true);
        Assertions.assertEquals(isHappy(2), false);
    }

    public boolean isHappy(int n) {
        Map<Integer, Boolean> numberOccursMap = new HashMap<>();

        while (true) {
            if (n == 1) {
                return true;
            }

            if (numberOccursMap.get(n) != null) {
                return false;
            }
            numberOccursMap.put(n, true);

            int num = 0;
            while (n != 0) {
                int firstPlace = n % 10;
                num += firstPlace * firstPlace;
                n = n / 10;
            }
            n = num;
        }
    }
}
