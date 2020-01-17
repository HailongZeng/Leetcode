package Amazon_VO;

/**
 * @author HailongZeng
 * @date 1/13/20 11:05 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 */
public class No30_Remove_Linked_List_Elements {

    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        while (head != null){
            if (head.val == val){
                head = head.next;
                continue;
            }
            cur.next = new ListNode(head.val);
            head = head.next;
            cur = cur.next;
        }
        return res.next;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            ListNode head = stringToListNode(line);
            int val = Integer.parseInt(io.readLine());
            ListNode ret = removeElements(head, val);
            String s = listNodeToString(ret);
            System.out.println(s);
        }
    }

    private static String listNodeToString(ListNode res) {
        if (res == null) return "[]";
        StringBuilder sb = new StringBuilder();
        while (res != null){
            sb.append(res.val + ",");
            res = res.next;
        }
        return "[" + sb.toString().substring(0, sb.length()-1) + "]";
    }

    //[1,2,6,3,4,5,6]  6
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
