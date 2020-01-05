package Sort;

/**
 * ①. 从第一个元素开始，该元素可以认为已经被排序
 * ②. 取出下一个元素，在已经排序的元素序列中从后向前扫描
 * ③. 如果该元素（已排序）大于新元素，将该元素移到下一位置
 * ④. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 * ⑤. 将新元素插入到该位置后
 * ⑥. 重复步骤②~⑤
 */
public class InsertSort {

    public static void insertSort1(int[] nums){
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j > 0; j--){
                if (nums[j - 1] <= nums[j]) break;
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
            }
        }
        return;
    }

    public static void insertSort2(int[] nums){
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];  //取出下一个元素，在已经排序的元素序列中从后向前扫描
            for (int j = i; j >= 0; j--){
                if (j > 0 && nums[j - 1] > temp){
                    nums[j] = nums[j - 1];  //如果该元素大于取出的元素temp，将该元素移到下一个位置
                } else{//将新元素插入到该位置后
                    nums[j] = temp;
                    break;
                }
            }
        }
        return;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 3, 5, 5, 7, 6};
        insertSort1(nums);
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
