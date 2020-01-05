package Amazon_OA2;
import java.util.*;

public class test {

    public static class PairInt{
        int first, second;
        public PairInt(){}
        public PairInt(int first, int second){
            this.first = first;
            this.second = second;
        }
    }

    public static List<PairInt> criticalConnections(int numOfWarehouses, int numOfRoads, List<PairInt> roads) {
        // WRITE YOUR CODE HERE
        List<PairInt> res = new ArrayList<>();
        HashSet<Integer> s = new HashSet<>();
        if (numOfWarehouses - 1 > numOfRoads) return res;
        ArrayList<Integer>[] graph = new ArrayList[numOfWarehouses + 1];
        int[] dfn = new int[numOfWarehouses + 1];
        int[] low = new int[numOfWarehouses + 1];
        Arrays.fill(dfn, -1);

        //draw graph
        for (int i = 1; i <= numOfWarehouses; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < numOfRoads; i++){
            PairInt tmp = roads.get(i);
            graph[tmp.first].add(tmp.second);
            graph[tmp.second].add(tmp.first);
            s.add(tmp.first);
            s.add(tmp.second);
        }

        for (int i = 1; i <= numOfWarehouses; i++){
            if (dfn[i] == -1){
                tarjan(i, low, dfn, graph, res, 0);
            }
        }
        return res;
    }

    public static int time = 0;
    public static void tarjan(int u, int[] low, int[] dfn, ArrayList<Integer>[] graph, List<PairInt> res, int node){
        dfn[u] = low[u] = ++time;
        for (int j = 0; j < graph[u].size(); j++){
            int v = graph[u].get(j);
            if (v == node) continue;
            if (dfn[v] == -1){
                tarjan(v, low, dfn, graph, res, u);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > dfn[u]){
                    PairInt tmp = new PairInt(u, v);
                    res.add(tmp);
                }
            }else{
                low[u] = Math.min(low[u], dfn[v]);
            }
        }
    }

    public static void main(String[] args) {
        int numOfWarehouses = 3;
        int numOfRoads = 1;
        List<PairInt> roads = new ArrayList<>();

        PairInt a1 = new PairInt(1, 2);
//        PairInt a2 = new PairInt(1, 2);
//        PairInt a3 = new PairInt(1, 3);
        roads.add(a1);
//        roads.add(a2);
//        roads.add(a3);

        List<PairInt> res = criticalConnections(numOfWarehouses, numOfRoads, roads);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i).first + "," + res.get(i).second);
        }
    }
}
