package Nutanix_oa;

/**
 * @author HailongZeng
 * @date 1/10/20 1:08 下午
 */

/**
 * count the number of operation needed
 * 给一串由0和1组成的数组，0代表已读，1代表未读。三种operation，打开未读，跳出，直接读下一封邮件。每个operation的"cost"都是1.算要多少operations。
 * [0,0,0,1,1,0,0,1,0,1,0,0]需要6步，读1(+1)，点下一篇mail(+1)，跳出去(+1)，略过0，读1(+1)，跳出去(+1，点下一篇也是cost1，一个道理),读1(+1)
 */
public class No6_Email {

    public static int countOperations(int[] arr){
        int res = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == 1){
                res++;
                continue;
            }
            if (i > 0 && arr[i-1] == 1) res++;
            if (i == arr.length - 1 && arr[i] == 0) res--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0};
        int res = countOperations(arr);
        System.out.println(res);
    }
}
