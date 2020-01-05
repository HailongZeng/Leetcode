package Amazon;

import java.util.*;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 *
 * Note:
 *
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * Example 1:
 *
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 *
 * Example 2:
 *
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 */

//Hierholzer算法
public class No332_Reconstruct_Itinerary {

    public static List<String> findItinerary(List<List<String>> tickets){
        LinkedList<String> res = new LinkedList<>();
        HashMap<String, PriorityQueue<String>> m = new HashMap<>();
        for (int i = 0; i < tickets.size(); i++){
            List<String> l =tickets.get(i);
            if (!m.containsKey(l.get(0))) m.put(l.get(0), new PriorityQueue<>());
            m.get(l.get(0)).add(l.get(1));
        }
        dfs("JFK", m, res);
        return res;
    }

    public static void dfs(String depature, HashMap<String, PriorityQueue<String>> m, LinkedList<String> res){
        PriorityQueue<String> arrivals = m.get(depature);
        while (arrivals != null && !arrivals.isEmpty()){
            dfs(arrivals.poll(), m, res);
        }
        res.addFirst(depature);
    }

    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        List<String> l1 = new ArrayList<>();
        l1.add("MUC");
        l1.add("LHR");
        tickets.add(l1);

        List<String> l2 = new ArrayList<>();
        l2.add("JFK");
        l2.add("MUC");
        tickets.add(l2);

        List<String> l3 = new ArrayList<>();
        l3.add("SFO");
        l3.add("SJC");
        tickets.add(l3);

        List<String> l4 = new ArrayList<>();
        l4.add("LHR");
        l4.add("SFO");
        tickets.add(l4);
        System.out.println(findItinerary(tickets));
    }
}
