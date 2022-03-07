package Algorithm.LeetCode;

public class RemoveElement {
    public static void main(String[] args){
        System.out.println(removeElement(new int[]{3,2,2,3}, 3));
        System.out.println(removeElement(new int[]{0,1,2,2,3,0,4,2}, 2));
    }

    public static int removeElement(int[] nums, int val) {
        int count = 0;
        for(int index = 0; index < nums.length; index ++){
            if(nums[index] != val){
                nums[count] = nums[index];
                count++;
            }
        }
        return count;
    }
}
