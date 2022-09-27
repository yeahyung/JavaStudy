package Algorithm.LeetCode;

import org.junit.Assert;
import org.junit.Test;

public class ExcelSheetColumnNumber {

    @Test
    public void excelSheetColumnNumber() {
        Assert.assertEquals(titleToNumber("A"), 1);
        Assert.assertEquals(titleToNumber("AB"), 28);
        Assert.assertEquals(titleToNumber("ZY"), 701);
    }

    public int titleToNumber(String columnTitle) {
        int anw = 0;
        int digit = 1;
        StringBuilder tempBuffer = new StringBuilder(columnTitle);
        columnTitle = tempBuffer.reverse().toString();
        for (char character : columnTitle.toCharArray()) {
            anw += (character - 'A' + 1) * digit;
            digit *= 26;
        }

        return anw;
    }
}
