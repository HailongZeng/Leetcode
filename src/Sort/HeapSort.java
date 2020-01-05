package Sort;

/**
 * 1991年的计算机先驱奖获得者、斯坦福大学计算机科学系教授罗伯特·弗洛伊德(Robert W．Floyd) 和威廉姆斯(J．Williams) 在1964年共同发明了著名的堆排序算法(Heap Sort).
 *
 * 基本思想：此处以大顶堆为例，堆排序的过程就是将待排序的序列构造成一个堆，选出堆中最大的移走，再把剩余的元素调整成堆，找出最大的再移走，重复直至有序。
 * ①. 先将初始序列K[1..n]
 * 建成一个大顶堆, 那么此时第一个元素K1
 * 最大, 此堆为初始的无序区.
 * ②. 再将关键字最大的记录K1
 *  (即堆顶, 第一个元素)和无序区的最后一个记录 Kn
 *  交换, 由此得到新的无序区K[1..n−1]
 * 和有序区K[n]
 * , 且满足K[1..n−1].keys⩽K[n].key
 *
 * ③. 交换K1
 *  和 Kn
 *  后, 堆顶可能违反堆性质, 因此需将K[1..n−1]
 * 调整为堆. 然后重复步骤②, 直到无序区只有一个元素时停止.
 *
 *
 * 建堆、堆顶和堆的最后一个元素交换
 *
 * 对于堆节点的访问：
 *
 * 父节点i的左子节点在位置：(2*i+1);
 * 父节点i的右子节点在位置：(2*i+2);
 * 子节点i的父节点在位置：floor((i-1)/2);
 */
public class HeapSort {

    public static void heapSort(int[] nums){
        for (int i = nums.length; i > 0; i--){
            max_heapify(nums, i);
            int temp = nums[0];  //堆顶元素与Kn交换
            nums[0] = nums[i - 1];
            nums[i - 1] = temp;
        }
        return;
    }

    public static void max_heapify(int[] nums, int limit){
        if (nums.length <= 0 || nums.length < limit) return;
        int parentIndex = limit / 2;
        for (; parentIndex >= 0; parentIndex--){
            if (parentIndex * 2 >= limit){
                continue;
            }
            int left = parentIndex * 2;  //左子节点位置
            int right = (left + 1) >= limit ? left : (left + 1);  //右节点位置，如果没有右节点，默认为左节点位置
            int maxChildId = nums[left] >= nums[right] ? left : right;
            if (nums[maxChildId] > nums[parentIndex]){//交换父节点与左右节点中的最大值
                int temp = nums[parentIndex];
                nums[parentIndex] = nums[maxChildId];
                nums[maxChildId] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 3, 5, 5, 7, 6};
        heapSort(nums);
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
