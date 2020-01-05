package Amazon_OA2;

import java.util.*;

public class test1 {

    public static class PairInt{
        int first, second;
        public PairInt(){}
        public PairInt(int first, int second){
            this.first = first;
            this.second = second;
        }
    }

    public static List<PairInt> list;
    public static Map<Integer, Boolean> visited;
    public static List<PairInt> criticalConnections(int numOfServers, int numOfConnections, List<PairInt> connections) {
        Map<Integer, HashSet<Integer>> adj = new HashMap<>();
        for(PairInt connection : connections){
            int u = connection.first;
            int v = connection.second;
            if(adj.get(u) == null){
                adj.put(u,new HashSet<Integer>());
            }
            adj.get(u).add(v);
            if(adj.get(v) == null){
                adj.put(v,new HashSet<Integer>());
            }
            adj.get(v).add(u);
        }

        list = new ArrayList<>();
        for(int i = 0;i < numOfConnections; i++){
            visited = new HashMap<>();
            PairInt p = connections.get(i);
            int x = p.first;
            int y = p.second;
            adj.get(x).remove(y);
            adj.get(y).remove(x);
            DFS(adj,1);
            if(visited.size()!=numOfServers){
                if(p.first > p.second)
                    list.add(new PairInt(p.second,p.first));
                else
                    list.add(p);
            }
            adj.get(x).add(y);
            adj.get(y).add(x);
        }
        return list;
    }

    public static void DFS(Map<Integer, HashSet<Integer>> adj, int u){
        visited.put(u, true);
        if(adj.get(u).size()!=0){
            for(int v : adj.get(u)){
                if(visited.getOrDefault(v, false)== false){
                    DFS(adj,v);
                }
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
