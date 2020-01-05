package Amazon;

/**
 * @author HailongZeng
 * @date 12/15/19 9:28 下午
 */

import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 *
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class No347_Top_K_Frequent_Elements {

    public static List<Integer> topKFrequent(int[] nums, int k){
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
//        Comparator<Map.Entry<Integer, Integer>> valueComparator = Comparator.comparingInt(Map.Entry::getValue);
        Comparator<Map.Entry<Integer, Integer>> valueComparator = (o1, o2)->o2.getValue() - o1.getValue();
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, valueComparator);
        int i = 0;
        for (Map.Entry<Integer, Integer> entry: list){
            if (i == k) break;
            res.add(entry.getKey());
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++){
                nums[j] = st.nextInt();
            }
            int k = st.nextInt();
            System.out.println(topKFrequent(nums, k));
        }
    }
}
