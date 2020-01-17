package Nutanix_oa;

/**
 * @author HailongZeng
 * @date 1/10/20 1:30 下午
 */

/**
 *  cargo 有一艘船要送 N 个货物要送到 N 个港口，一共有 M 个港口，船一次只能装最多 Y 斤货物且不能
 *  超过 X 个货物，而且只能从 id=0 的货物往上送，问总共要停在码头多少次，装货/卸货各算一次。
 */

/**
 * input consists of 4 lines:
 * 1.N and M, space-separated
 * 2.X and Y, space-separated
 * 3.N integers denoting container weights A
 * 4.N integers denoting destination ports of corresponding container
 * input:
 * 3 5
 * 2 150
 * 70 60 30
 * 2 4 1
 * output:
 * 5
 * input:
 * 5 3
 * 5 180
 * 30 30 100 90 50
 * 3 3 1 1 3
 * output:
 * 6
 *
 * Required time:O(N*logN)+M
 * Required worst-cast space:O(N+M)
 */
//greedy, choose as many containers as possible each time
public class No8_Cargo_Port {
}
