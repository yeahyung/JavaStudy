package Algorithm.LeetCode;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainsDuplicate {
    @Test
    public void containsDuplicate() {
        Assertions.assertEquals(containsDuplicate(new int[]{1, 2, 3, 4}), false);
        Assertions.assertEquals(containsDuplicate(new int[]{1, 2, 3, 1}), true);
        Assertions.assertEquals(containsDuplicate(new int[]{1, 1, 3, 1}), true);
    }

    public boolean containsDuplicate(int[] nums) {
//        Map<Integer, Boolean> numMap = new HashMap<>();
//        for (int num : nums) {
//            if (numMap.containsKey(num)) {
//                return true;
//            }
//            numMap.put(num, true);
//        }
//        return false;

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            if (!numSet.add(num)) {
                return true;
            }
        }
        return false;

        // 또는 정렬해서 i, i+1 index 비교도 가능
    }
}
