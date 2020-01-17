package Nutanix_oa;

import java.util.*;

/**
 * @author HailongZeng
 * @date 1/10/20 1:59 下午
 */
public class No10_Gumball_Machine {

    public static double totalWaste(List<String> a){
        double res = 0;
        if (a.isEmpty()) return res;
        Map<String, Integer> m = new HashMap<>();//key is color, value is when the color is stored
        for (int i = 0; i < a.size(); i++){
            String tmp = a.get(i);
            if (m.containsKey(tmp)){
                res += 0;
            }else{
                if (m.size() == 3){
                    Comparator<Map.Entry<String, Integer>> valueComparator = (o1, o2)->o1.getValue() - o2.getValue();
                    List<Map.Entry<String, Integer>> list = new ArrayList<>(m.entrySet());
                    Collections.sort(list, valueComparator);
                    int index = list.get(0).getValue();
                    String key = list.get(0).getKey();
                    m.remove(key);
                    res += (1000 - (i - index) * 10) * 0.01;
                }
            }
            m.put(tmp, i);
        }
        return res;
    }

    public static void main(String[] args) {
//        String[] s = {"red", "green", "blue", "red", "white"};
        String[] s = {"red", "red"};
        List<String> a = new ArrayList<>(Arrays.asList(s));
        System.out.println(a);
        double res = totalWaste(a);
        System.out.println(res);
    }
}
