package Algorithm.LeetCode;

import java.util.Arrays;

public class MergeSortedArray {
    public static void main(String[] args){
        merge1(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
        merge1(new int[]{1}, 1, new int[]{}, 0);
        merge1(new int[]{0}, 0, new int[]{1}, 1);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i=m; i<m+n; i++){
            nums1[i] = nums2[i-m];
        }

        Arrays.sort(nums1);
        for(int num : nums1){
            System.out.println(num);
        }
    }

    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        int tail1 = m-1, tail2 = n-1;

        while(tail1 >= 0 && tail2 >=0 ){
            if(nums1[tail1] > nums2[tail2]){
                nums1[index] = nums1[tail1];
                tail1--;
            }
            else{
                nums1[index] = nums2[tail2];
                tail2--;
            }
            index--;
        }

        while(tail2 >= 0){
            nums1[index] = nums2[tail2];
            tail2--;
            index--;
        }


        for(int num : nums1){
            System.out.println(num);
        }
    }
}
