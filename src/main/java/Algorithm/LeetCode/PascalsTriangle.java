package Algorithm.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    public static void main(String[] args){
        System.out.println(generate(5));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<Integer> returnRecordLists = new ArrayList<>();
        returnRecordLists.add(123);
        returnRecordLists.add(124);
        System.out.println(returnRecordLists.toString());
        List<List<Integer>> anw = new ArrayList<>();
        List<Integer> part = new ArrayList<>();
        List<Integer> prev = new ArrayList<>();

        part.add(1);
        anw.add(part);
        prev = part;

        for(int index = 2; index <= numRows; index ++){
            part = new ArrayList<>();
            part.add(1);

            for(int pas = 1; pas <= index - 2; pas ++){
                part.add(prev.get(pas-1) + prev.get(pas));
            }

            part.add(1);
            anw.add(part);
            prev = part;
        }

        return anw;
    }
}
