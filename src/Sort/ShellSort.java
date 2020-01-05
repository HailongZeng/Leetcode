package Sort;

/**
 * 首个突破O(n^2)的排序算法。简单插入排序的改进版本
 * ①. 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；（一般初次取数组半长，之后每次再减半，直到增量为1）
 * ②. 按增量序列个数k，对序列进行k 趟排序；
 * ③. 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 */
public class ShellSort {

    public static void shellSort1(int[] nums){
        int gap = nums.length / 2;
        for (; gap > 0; gap /= 2){
            for (int j = 0; (j + gap) < nums.length; j++){
                for (int k = 0; (k + gap) < nums.length; k += gap){
                    if (nums[k] > nums[k + gap]){
                        int temp = nums[k + gap];
                        nums[k + gap] = nums[k];
                        nums[k] = temp;
                    }
                }
            }
        }
        return;
    }

    public static void shellSort2(int[] nums){
        int gap = 1, i, j, len = nums.length;
        int temp;
        while (gap < len / 3){
            gap = gap * 3 + 1;   // by Knuth, 1973   1, 4, 13, 40, 121.....
        }
        for (; gap > 0; gap /= 3){
            for (i = gap; i < len; i++){
                temp = nums[i];
                for (j = i - gap; j >= 0 && nums[j] > temp; j -= gap){
                    nums[j + gap] = nums[j];
                }
                nums[j + gap] = temp;
            }
        }
        return;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 3, 5, 5, 7, 6};
        shellSort2(nums);
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
