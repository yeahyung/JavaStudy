package Algorithm.LeetCode;

public class SearchInsertPosition {

    public static void main(String[] args){
        System.out.println(searchInsert(new int[]{1,3,5,6}, 5));
        System.out.println(searchInsert(new int[]{1,3,5,6}, 2));
        System.out.println(searchInsert(new int[]{1,3,5,6}, 7));
        System.out.println(searchInsert(new int[]{1,3,5,6}, 0));
        System.out.println(searchInsert(new int[]{}, 1));
    }

    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;

        while(left <= right){
            int pos = (left + right)/2;
            if(nums[pos] == target){
                return pos;
            }
            else if(nums[pos] > target){
                right = pos - 1;
            }
            else{
                left = pos + 1;
            }
        }

        return left;
    }
}
