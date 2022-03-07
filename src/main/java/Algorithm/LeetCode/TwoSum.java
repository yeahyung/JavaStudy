package Algorithm.LeetCode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args){
        int[] nums = {2,7,11,15};
        twoSum(nums, 9);
    }
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i<nums.length; i++){
            map.put(nums[i], i);
        }

        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                System.out.println("anw : " + i + " : " + map.get(nums[i]));
                return new int[]{i, map.get(nums[i])};
            }
        }

        return null;
    }
}
