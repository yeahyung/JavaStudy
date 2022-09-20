package Algorithm.LeetCode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalsTriangle2 {
    @Test
    public void PascalsTriangle() {
        // combination
        Assert.assertEquals(getRow(3), Arrays.asList(1, 3, 3, 1));
        Assert.assertEquals(getRow(1), Arrays.asList(1, 1));
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> answer = new ArrayList<>();
        answer.add(1);

        if (rowIndex == 0) {
            return answer;
        }
        long value = 1L;
        for (int i = 1; i < rowIndex; i++) {
            value = value * (rowIndex + 1 - i) / i;
            answer.add((int) value);
        }

        answer.add(1);
        return answer;
    }
}
