package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/14/20 10:00 下午
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example:
 *
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 */
//leetcode315
public class No42_Count_of_Smaller_Numbers_After_Self {

    //brute force  time:O(n^2)  extra space:O(1)
    public static List<Integer> countSmaller1(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            int count = 0;
            for (int j = i+1; j < nums.length; j++){
                if (nums[j] < nums[i]) count++;
            }
            res.add(count);
        }
        return res;
    }

    //insertion sort  average time:O(nlogn)  space:O(n)
    public static List<Integer> countSmaller2(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        LinkedList<Integer> res = new LinkedList<>();
        ArrayList<Integer> sorted = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--){
            if (sorted.isEmpty()){
                sorted.add(nums[i]);
                res.addFirst(0);
            }else if(nums[i] > sorted.get(sorted.size() - 1)){
                sorted.add(sorted.size(), nums[i]);
                res.addFirst(sorted.size()-1);
            }else{
                int l = 0, r = sorted.size()-1;
                while (l < r){
                    int m = l + (r - l) / 2;
                    if (nums[i] > sorted.get(m)){
                        l = m + 1;
                    }else{
                        r = m;
                    }
                }
                sorted.add(r, nums[i]);
                res.addFirst(r);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 6, 1};
        List<Integer> res = countSmaller1(nums);
        System.out.println(res);
    }
}
