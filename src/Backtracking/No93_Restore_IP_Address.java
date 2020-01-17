package Backtracking;

/**
 * @author HailongZeng
 * @date 1/6/20 5:30 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * Example:
 *
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 */
public class No93_Restore_IP_Address {

    //the format of IPV4 address: xxx.xxx.xxx.xxx   each part xxx should be <= 255 and > 0
    public static List<String> restoreIpAddress(String s){
        List<String> res = new ArrayList<>();
        helper(res, "", s, 0);
        return res;
    }

    public static void helper(List<String> res, String cur, String s, int k){
        if (k == 4){
            if (s.isEmpty()) res.add(cur);
            return;
        }
        for (int i = 1; i < 4; i++){
            if (s.length() < i) break;
            int val = Integer.parseInt(s.substring(0, i));
            if (val > 255 || i != String.valueOf(val).length()) continue;
            helper(res, cur+s.substring(0, i) + (k == 3 ? "" : "."), s.substring(i), k+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = io.readLine()) != null){
            List<String> res = restoreIpAddress(s);
            System.out.println(res);
        }
    }
}
