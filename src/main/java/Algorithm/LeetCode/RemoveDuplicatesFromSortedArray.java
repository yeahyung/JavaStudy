package Algorithm.LeetCode;

public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args){
        System.out.println(removeDuplicates(new int[]{1,1,2}));
        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
    }

    public static int removeDuplicates(int[] nums) {
        if(nums.length == 0)
            return 0;

        int count=1;

        int value = nums[0];

        for(int index = 1; index < nums.length; index++){
            if(value == nums[index]){
                continue;
            }
            else{
                value = nums[index];
                nums[count] = nums[index];
                count++;
            }
        }

        for(int num: nums){
            System.out.print(num);
        }
        return count;
    }
}
