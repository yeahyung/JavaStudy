package Algorithm.LeetCode;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExcelSheetColumnNumber {

    @Test
    public void excelSheetColumnNumber() {
        Assertions.assertEquals(titleToNumber("A"), 1);
        Assertions.assertEquals(titleToNumber("AB"), 28);
        Assertions.assertEquals(titleToNumber("ZY"), 701);
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
