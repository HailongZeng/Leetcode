package Sort;

/**
 * ①. 从待排序序列中，找到关键字最小的元素；
 * ②. 如果最小元素不是待排序序列的第一个元素，将其和第一个元素互换；
 * ③. 从余下的 N - 1 个元素中，找出关键字最小的元素，重复①、②步，直到排序结束。
 */
public class SelectSort {

    public static void selectSort(int[] nums){
        for (int i = 0; i < nums.length - 1; i++){
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++){
                if (nums[j] < nums[minIndex]){
                    minIndex = j;
                }
            }
            if (minIndex != i){
                int temp = nums[minIndex];
                nums[minIndex] = nums[i];
                nums[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 3, 5, 5, 7, 6};
        selectSort(nums);
        System.out.print("[");
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                System.out.print(nums[i]);
            } else {
                System.out.print(nums[i] + ",");
            }
        }
        System.out.println("]");
    }
}
