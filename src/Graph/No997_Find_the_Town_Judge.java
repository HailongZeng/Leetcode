package Graph;

/**
 * @author HailongZeng
 * @date 12/17/19 10:36 上午
 */

import java.util.Scanner;

/**
 * In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.
 *
 * If the town judge exists, then:
 *
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.
 *
 * If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: N = 2, trust = [[1,2]]
 * Output: 2
 * Example 2:
 *
 * Input: N = 3, trust = [[1,3],[2,3]]
 * Output: 3
 * Example 3:
 *
 * Input: N = 3, trust = [[1,3],[2,3],[3,1]]
 * Output: -1
 * Example 4:
 *
 * Input: N = 3, trust = [[1,2],[2,3]]
 * Output: -1
 * Example 5:
 *
 * Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
 * Output: 3
 *
 *
 * Note:
 *
 * 1 <= N <= 1000
 * trust.length <= 10000
 * trust[i] are all different
 * trust[i][0] != trust[i][1]
 * 1 <= trust[i][0], trust[i][1] <= N
 */
public class No997_Find_the_Town_Judge {

    //directed graph  degree = indegree-outdegree   only the judge has N-1 degree
    public static int findJudge(int N, int[][] trust){
        int[] degree = new int[N+1];
        for (int[] a: trust){
            degree[a[0]]--;
            degree[a[1]]++;
        }
        for (int i = 1; i <= N; i++){
            if (degree[i] == N - 1) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int numOfCases = st.nextInt();
        for (int i = 0; i < numOfCases; i++){
            int N = st.nextInt();
            int n = st.nextInt();
            int[][] trust = new int[n][2];
            for (int j = 0; j < n; j++){
                int a = st.nextInt();
                int b = st.nextInt();
                trust[j] = new int[]{a, b};
            }
            System.out.println(findJudge(N, trust));
        }
    }
}
