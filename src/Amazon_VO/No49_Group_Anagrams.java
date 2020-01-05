package Amazon_VO;

import java.util.*;

/**
 * @author HailongZeng
 * @date 1/4/20 7:52 下午
 */
public class No49_Group_Anagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0 || strs == null) return new ArrayList<>();
        int[] charToInteger = {2, 3, 5, 7, 11, 13, 17, 19, 23, 27, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 97, 103, 107};
//        List<List<String>> res = new ArrayList<>();
        Map<Long, List<String>> map = new HashMap<>();
        for (String s: strs){
            long pro = 1;
            for (char c: s.toCharArray()){
                pro *= charToInteger[c-'a'];
            }
            if (!map.containsKey(pro)){
                map.put(pro, new ArrayList<>());
            }
            map.get(pro).add(s);
        }
//        for (long l: map.keySet()){
//            res.add(map.get(l));
//        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = Integer.parseInt(st.nextLine());
        for (int i = 0; i < N; i++){
            String[] strs = st.nextLine().split(",");
            System.out.println(groupAnagrams(strs));
        }
    }
}
