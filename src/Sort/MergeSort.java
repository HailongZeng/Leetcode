package Sort;

import java.util.Arrays;

/**
 * 归并排序是建立在归并操作上的一种有效的排序算法，1945年由约翰·冯·诺伊曼首次提出。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用，且各层分治递归可以同时进行。
 *
 * 归并排序可通过两种方式实现：
 *
 * 自上而下的递归
 * 自下而上的迭代
 *
 * 一、递归法（假设序列共有n个元素）：
 *
 * ①. 将序列每相邻两个数字进行归并操作，形成 floor(n/2)个序列，排序后每个序列包含两个元素；
 * ②. 将上述序列再次归并，形成 floor(n/4)个序列，每个序列包含四个元素；
 * ③. 重复步骤②，直到所有元素排序完毕。
 *
 * 二、迭代法
 *
 * ①. 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
 * ②. 设定两个指针，最初位置分别为两个已经排序序列的起始位置
 * ③. 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
 * ④. 重复步骤③直到某一指针到达序列尾
 * ⑤. 将另一序列剩下的所有元素直接复制到合并序列尾
 */
public class MergeSort {

    public static int[] mergeSort(int[] nums){
        if (nums.length <= 1) return nums;
        int num = nums.length >> 1;
        int[] leftArr = Arrays.copyOfRange(nums, 0, num);
        int[] rightArr = Arrays.copyOfRange(nums, num, nums.length);
        return mergeTwoArray(mergeSort(leftArr), mergeSort(rightArr));
    }

    public static int[] mergeTwoArray(int[] arr1, int[] arr2){
        int i = 0, j = 0, k = 0;
        int[] res = new int[arr1.length + arr2.length];
        while (i < arr1.length && j < arr2.length){
            if (arr1[i] <= arr2[j]){
                res[k++] = arr1[i++];
            }else{
                res[k++] = arr2[j++];
            }
        }
        while (i < arr1.length){
            res[k++] = arr1[i++];
        }
        while (j < arr2.length){
            res[k++] = arr2[j++];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 3, 5, 5, 7, 6};
        int[] res = mergeSort(nums);
        System.out.print("[");
        for (int i = 0; i < res.length; i++) {
            if (i == res.length - 1) {
                System.out.print(res[i]);
            } else {
                System.out.print(res[i] + ",");
            }
        }
        System.out.println("]");
    }
}
