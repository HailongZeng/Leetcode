package Math;

/**
 * @author HailongZeng
 * @date 1/11/20 9:30 下午
 */

import java.util.Scanner;

/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 *
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 *
 * Rectangle Area
 *
 * Example:
 *
 * Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
 * Output: 45
 * Note:
 *
 * Assume that the total area is never beyond the maximum possible value of int.
 */

public class No223_Rectangle_Area {

    //[A,B,C,D]--rect1   [E,F,G,H]--rect2
    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H){
        int x1 = Math.max(A, E);
        int y1 = Math.max(B, F);
        int x2 = Math.min(C, G);
        int y2 = Math.min(D, H);
        int overlapped = 0;
        if (x2 > x1 && y2 > y1) overlapped = (y2-y1) * (x2-x1);
        int res = (C-A)*(D-B) + (G-E)*(H-F) - overlapped;
        return res;
    }

    public static void main(String[] args) {
        Scanner st = new Scanner(System.in);
        int n = st.nextInt();
        for (int i = 0; i < n; i++){
            int A = st.nextInt();
            int B = st.nextInt();
            int C = st.nextInt();
            int D = st.nextInt();
            int E = st.nextInt();
            int F = st.nextInt();
            int G = st.nextInt();
            int H = st.nextInt();
            int res = computeArea(A, B, C, D, E, F, G, H);
            System.out.println(res);
        }
    }
}
