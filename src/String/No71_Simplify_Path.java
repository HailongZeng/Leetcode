package String;

/**
 * @author HailongZeng
 * @date 1/13/20 7:34 下午
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
 *
 * In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix
 *
 * Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.
 *
 *
 *
 * Example 1:
 *
 * Input: "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 * Example 2:
 *
 * Input: "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 * Example 3:
 *
 * Input: "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 * Example 4:
 *
 * Input: "/a/./b/../../c/"
 * Output: "/c"
 * Example 5:
 *
 * Input: "/a/../../b/../c//.//"
 * Output: "/c"
 * Example 6:
 *
 * Input: "/a//b////c/d//././/.."
 * Output: "/a/b/c"
 */
public class No71_Simplify_Path {

    public static String simplifyPath(String path) {
        if (path.length() == 0) return "/";
        Stack<String> stack = new Stack<String>();
        for (String s: path.split("/")){
            if (s.equals("..")){
                if (!stack.isEmpty()){
                    stack.pop();
                }
            }else if(!s.equals("") && !s.equals(".")){
                stack.push(s);
            }
        }
        StringBuilder sb = new StringBuilder("/");
        for (String s: stack){
            sb.append(s+"/");
        }
        if (!stack.isEmpty()){
            sb.setLength(sb.length()-1);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = io.readLine()) != null){
            String res = simplifyPath(line);
            System.out.println(res);
        }
    }
}
