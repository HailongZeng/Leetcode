package Amazon_OA2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode 937
 * You have an array of logs.  Each log is a space delimited string of words.
 *
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 *
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
 *
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
 *
 * Return the final order of the logs.
 *
 *
 *
 * Example 1:
 *
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 *
 * Constraints:
 *
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */
public class Reorder_Data_in_Log_Files {

    public static String[] reorderLogFiles(String[] logs){
        if (logs.length == 0 || logs == null) return null;
        List<String> digitLogs = new ArrayList<>();
        List<String> letterLogs = new ArrayList<>();
        for (String log: logs){
            if (log.split(" ")[1].charAt(0) < 'a') digitLogs.add(log);
            else letterLogs.add(log);
        }
        Collections.sort(letterLogs, (o1, o2)->{
            String s1 = o1.substring(o1.indexOf(' ') + 1);
            String s2 = o2.substring(o2.indexOf(' ') + 1);
            return s1.compareTo(s2);
        });
        for (int i = 0; i < logs.length; i++){
            if (i < letterLogs.size()) logs[i] = letterLogs.get(i);
            else logs[i] = digitLogs.get(i - letterLogs.size());
        }
        return logs;
    }

    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 zrt zero"};
        String[] res = reorderLogFiles(logs);
        List<String> a = Arrays.asList(res);
        System.out.println(a);
    }
}
