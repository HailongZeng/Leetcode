package Amazon_VO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author HailongZeng
 * @date 1/13/20 12:12 下午
 */
public class No16_Convert_Sorted_List_to_Binary_Search_Tree {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int x){
            val = x;
        }
    }

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return helper(head, null);
    }

    public static TreeNode helper(ListNode head, ListNode tail){
        if (head == tail) return null;
        ListNode slow = head, fast = head;
        while (fast != tail && fast.next != tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode cur = new TreeNode(slow.val);
        cur.left = helper(head, slow);
        cur.right = helper(slow.next, tail);
        return cur;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            ListNode head = stringToListNode(line);
            TreeNode root = sortedListToBST(head);
            String res = treeNodeToString(root);
            System.out.println(res);
        }
    }

    private static String treeNodeToString(TreeNode root) {
        if (root == null) return "[]";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            TreeNode node = q.poll();
            if (node == null) sb.append("null,");
            else{
                sb.append(node.val + ",");
                q.add(node.left);
                q.add(node.right);
            }
        }
        return "[" + sb.toString().substring(0, sb.length() - 1) + "]";
    }

    private static ListNode stringToListNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return null;
        String[] parts = input.split(",");
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for (String s: parts){
            cur.next = new ListNode(Integer.parseInt(s));
            cur = cur.next;
        }
        return dummy.next;
    }
}
