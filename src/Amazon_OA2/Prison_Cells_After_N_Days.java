package Amazon_OA2;

/**
 * LeetCode 957
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 *
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 *
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
 *
 * We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
 *
 * Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)
 *
 *
 *
 * Example 1:
 *
 * Input: cells = [0,1,0,1,1,0,0,1], N = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation:
 * The following table summarizes the state of the prison on each day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 *
 * Example 2:
 *
 * Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
 * Output: [0,0,1,1,1,1,1,0]
 *
 *
 * Note:
 *
 * cells.length == 8
 * cells[i] is in {0, 1}
 * 1 <= N <= 10^9
 */
public class Prison_Cells_After_N_Days {

    public static int[] prisonAfterNDays(int[] cells, int N){
        int rem = ((N - 1) % 14) + 1;
        int temp[] = new int[cells.length];
        for (int day = 0; day < rem; day++){
            for (int i = 1; i < temp.length - 1; i++){
                temp[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            }
            System.arraycopy(temp, 0, cells, 0, cells.length);
        }
        return cells;
    }

    public static void main(String[] args) {
        int[] cells1 = {0, 1, 0, 1, 1, 0, 0, 1};
        int N1 = 7;
        int[] res1 = prisonAfterNDays(cells1, N1);
        System.out.print("[");
        for (int i = 0; i < res1.length; i++){
            if (i == res1.length - 1) {
                System.out.print(res1[i]);
            }else{
                System.out.print(res1[i] + ",");
            }
        }
        System.out.println("]");

        int[] cells2 = {1, 0, 0, 1, 0, 0, 1, 0};
        int N2 = 1000000000;
        int[] res2 = prisonAfterNDays(cells2, N2);
        System.out.print("[");
        for (int i = 0; i < res2.length; i++){
            if (i == res2.length - 1) {
                System.out.print(res2[i]);
            }else{
                System.out.print(res2[i] + ",");
            }
        }
        System.out.println("]");
    }
}
