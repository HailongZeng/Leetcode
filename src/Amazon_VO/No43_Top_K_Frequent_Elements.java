package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/14/20 11:56 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
//leetcode347
public class No43_Top_K_Frequent_Elements {

    //time:O(NlogN)  space:O(N)
    public static List<Integer> topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Comparator<Map.Entry<Integer, Integer>> valueComparator = (o1, o2)->o2.getValue() - o1.getValue();
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, valueComparator);
        int i = 0;
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: list){
            if (i == k) break;
            res.add(entry.getKey());
            i++;
        }
        return res;
    }

    public static List<Integer> topKFrequent2(int[] nums, int k){
        Map<Integer, Integer> m = new HashMap<>();
        for (int num: nums){
            m.put(num, m.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
                return m.get(o1) - m.get(o2);
            }
        });
        for (Integer key: m.keySet()){
            pq.offer(key);
            if (pq.size() > k) pq.poll();
        }
        List<Integer> res = new ArrayList<>(pq);
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            int[] nums = stringToArray(line);
            int k = Integer.parseInt(io.readLine());
            List<Integer> res = topKFrequent1(nums, k);
            System.out.println(res);
        }
    }

    private static int[] stringToArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[0];
        String[] parts = input.split(",");
        int[] nums = new int[parts.length];
        for (int i = 0; i < nums.length; i++){
            nums[i] = Integer.parseInt(parts[i]);
        }
        return nums;
    }
}
