package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/13/20 2:10 下午
 */

import java.util.*;

/**
 *
 */
public class No20_Clone_Graph {

    public static class Node{
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    //dfs  time:O(N)  since we process each node exactly once  space:O(N)
    private static Map<Node, Node> visited1 = new HashMap<>();
    public static Node cloneGraph1(Node node) {
        if (node == null) return null;
        if (visited1.containsKey(node)) return visited1.get(node);
        Node cloneNode = new Node(node.val, new ArrayList());
        visited1.put(node, cloneNode);
        for (Node neighbor: node.neighbors){
            cloneNode.neighbors.add(cloneGraph1(neighbor));
        }
        return cloneNode;
    }

    public static Node cloneGraph2(Node node){
        if (node == null) return null;
        Map<Node, Node> visited2 = new HashMap<>();
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(node);
        visited2.put(node, new Node(node.val, new ArrayList()));
        while (!queue.isEmpty()){
            Node n = queue.remove();
            for (Node neighbor: n.neighbors){
                if (!visited2.containsKey(neighbor)){
                    visited2.put(neighbor, new Node(neighbor.val, new ArrayList()));
                    queue.add(neighbor);
                }
                visited2.get(n).neighbors.add(visited2.get(neighbor));
            }
        }
        return visited2.get(node);
    }
}
