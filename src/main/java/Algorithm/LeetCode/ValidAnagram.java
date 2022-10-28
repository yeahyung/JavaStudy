package Algorithm.LeetCode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    @Test
    public void validAnagram() {
        Assert.assertEquals(isAnagram("anagram", "nagaram"), true);
        Assert.assertEquals(isAnagram("car", "rat"), false);
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();

        Arrays.sort(str1);
        Arrays.sort(str2);

        for (int i = 0; i < s.length(); i++) {
            if (str1[i] != str2[i]) {
                return false;
            }
        }
        return true;
    }
}