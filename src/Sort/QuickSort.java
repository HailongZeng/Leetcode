package Sort;

/**
 * 快速排序（Quicksort）是对冒泡排序的一种改进，借用了分治的思想，由C. A. R. Hoare在1962年提出。
 * ①. 从数列中挑出一个元素，称为”基准”（pivot）。
 * ②. 重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（相同的数可以到任一边）。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
 * ③. 递归地（recursively）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 */
public class QuickSort {

    public static void quickSort(int[] nums, int start, int end){
        int pivot = nums[start];
        int i = start;
        int j = end;
        while (i < j){
            while ((i < j) && (nums[j] >= pivot)){
                j--;
            }
            while ((i < j) && (nums[i] < pivot)){
                i++;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        if (i - 1 > start) quickSort(nums, start, i - 1);
        if (i + 1 < end) quickSort(nums, i + 1, end);
        return;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 3, 5, 5, 7, 6};
        quickSort(nums, 0, nums.length - 1);
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
