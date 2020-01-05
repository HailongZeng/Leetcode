package Array;

/**
 * @author HailongZeng
 * @date 12/25/19 11:55 上午
 */

import java.util.*;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 *
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 *     Note that "i" comes before "love" due to a lower alphabetical order.
 * Example 2:
 *
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *     with the number of occurrence being 4, 3, 2 and 1 respectively.
 * Note:
 *
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 * Follow up:
 *
 * Try to solve it in O(n log k) time and O(n) extra space.
 */
public class No692_Top_K_Frequent_Words {

    //Time：O(NlogN)  N is the length of words    space: O(N)
    public static List<String> topKfFrequent1(String[] words, int k){
        Map<String, Integer> count = new HashMap<>();
        for (String word: words){
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> res = new ArrayList<>(count.keySet());
        Collections.sort(res, (w1, w2)->count.get(w1) == count.get(w2) ? w1.compareTo(w2) : count.get(w2) - count.get(w1));
        return res.subList(0, k);
    }

    //time: O(Nlogk)  space: O(N)
    public List<String> topKFrequent2(String[] words, int k) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> m = new HashMap<>();
        for (String word: words){
            m.put(word, m.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<String>((w1, w2)->m.get(w1) == m.get(w2) ? w2.compareTo(w1) : m.get(w1) - m.get(w2));
        for (String word: m.keySet()){
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }
        while (!heap.isEmpty()) res.add(heap.poll());
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String[] words = st.nextLine().split(" ");
            int k = Integer.parseInt(st.nextLine());
            System.out.println(topKfFrequent1(words, k));
        }
    }
}
