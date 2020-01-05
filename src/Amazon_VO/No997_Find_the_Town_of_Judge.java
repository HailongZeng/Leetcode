package Amazon_VO;

import java.util.Scanner;

/**
 * @author HailongZeng
 * @date 1/4/20 7:27 下午
 */
public class No997_Find_the_Town_of_Judge {

    public static int findJudge(int N, int[][] trust) {
        int[] degrees = new int[N+1];
        for (int i = 0; i < trust.length; i++){
            degrees[trust[i][0]]--;
            degrees[trust[i][1]]++;
        }
        for (int i = 1; i <= N; i++){
            if (degrees[i] == N-1) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int N = st.nextInt();
        for (int i = 0; i < N; i++){
            int n = st.nextInt();
            int[][] trust = new int[n][2];
            for (int j = 0; j < n; j++){
                trust[j][0] = st.nextInt();
                trust[j][1] = st.nextInt();
            }
            System.out.println(findJudge(N, trust));
        }
    }
}
