package Amazon_OA2;

import java.util.*;

/**
 * AWS wants to increase the reliability of their network by upgrading crucial data center routers. Each data center has a single router that is connected to every other data center through a direct link or some other data center.
 *
 * To increase the fault tolerance of the network, AWS wants to identify routers which would result in a disconnected network if they went down and replace then with upgraded versions.
 *
 * Write an algorithm to identify all such routers.
 *
 * Input:
 *
 * The input to the function/method consists of three arguments:
 *
 * numRouters, an integer representing the number of routers in the data center.
 * numLinks, an integer representing the number of links between the pair of routers.
 * Links, the list of pair of integers - A, B representing a link between the routers A and B. The network will be connected.
 * Output:
 *
 * Return a list of integers representing the routers with need to be connected to the network all the time.
 *
 * Example:
 *
 * Input:
 *
 * numRouters = 7
 * numLinks = 7
 * Links = [[0,1], [0, 2], [1, 3], [2, 3], [2, 5], [5, 6], [3,4]]
 *
 * Output:
 *
 * [2, 3, 5]
 *
 * Explanation:
 *
 * On disconnecting router 2, a packet may be routed either to the routers- 0, 1, 3, 4 or the routers - 5, 6, but not to all.
 *
 * On disconnecting router 3, a packet may be routed either to the routers - 0,1,2,5,6 or to the router - 4, but not to all.
 *
 * On disconnecting router 5, a packet may be routed either to the routers - 0,1,2,3,4 or to the router - 6, but not to all.
 *
 * Related problems:
 *
 * Critical Connections
 */
public class Critical_Routers {

    public static List<Integer> router(int numRouters, int numLinks, List<List<Integer>> Links){
        HashSet<Integer> res = new HashSet<>();
        HashMap<Integer, HashSet<Integer>> m = new HashMap<>();
        for (int i = 0; i < numRouters; i++){
            m.put(i, new HashSet<>());
        }
        for (int i = 0; i < numLinks; i++){
            List<Integer> tmp = Links.get(i);
            m.get(tmp.get(0)).add(tmp.get(1));
            m.get(tmp.get(1)).add(tmp.get(0));
        }
        int[] low = new int[numRouters];
        int[] dfn = new int[numRouters];
        int[] parents = new int[numRouters];
        Arrays.fill(dfn, -1);
        Arrays.fill(parents, -1);
        for (int i = 0; i < numRouters; i++){
            if (dfn[i] == -1){
                tarjan(m, low, dfn, parents, i, res);
            }
        }
        return new ArrayList<>(res);
    }

    public static int time = 0;
    public static void tarjan(HashMap<Integer, HashSet<Integer>> m, int[] low, int[] dfn, int[] parents, int u, HashSet<Integer> res){
        int children = 0;
        dfn[u] = low[u] = ++time;
        for (int v: m.get(u)){
            if (v == parents[u]) continue;
            if (dfn[v] == -1){
                children++;
                parents[v] = u;
                tarjan(m, low, dfn, parents, v, res);
                low[u] = Math.min(low[u], low[v]);
                if ((parents[u] == -1 && children > 1) || (parents[u] != -1 && low[v] >= dfn[u])){
                    res.add(u);
                }
            }else if(v != parents[u]){
                low[u] = Math.min(low[u], dfn[v]);
            }
        }
    }

    public static void main(String[] args) {
        int numRouters = 7;
        int numLinks = 7;
        List<List<Integer>> Links = new ArrayList<>();
        List<Integer> t1 = new ArrayList<>();
        t1.add(0);
        t1.add(1);
        Links.add(t1);
        List<Integer> t2 = new ArrayList<>();
        t2.add(0);
        t2.add(2);
        Links.add(t2);
        List<Integer> t3 = new ArrayList<>();
        t3.add(1);
        t3.add(3);
        Links.add(t3);
        List<Integer> t4 = new ArrayList<>();
        t4.add(2);
        t4.add(3);
        Links.add(t4);
        List<Integer> t5 = new ArrayList<>();
        t5.add(2);
        t5.add(5);
        Links.add(t5);
        List<Integer> t6 = new ArrayList<>();
        t6.add(5);
        t6.add(6);
        Links.add(t6);
        List<Integer> t7 = new ArrayList<>();
        t7.add(3);
        t7.add(4);
        Links.add(t7);

        List<Integer> res = router(numRouters, numLinks, Links);
        System.out.print("[");
        for (int i = 0; i < res.size(); i++){
            if (i == res.size() - 1) System.out.print(res.get(i) + "]");
            else System.out.print(res.get(i) + ",");
        }
    }
}
