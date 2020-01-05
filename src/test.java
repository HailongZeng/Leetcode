public class test {

    public static int decode(String s){
        if (s.length() == 0 || s == null || s.charAt(0) == '0') return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = s.charAt(0) > '0' ? 1 : 0;
        if (s.length() == 1) return dp[0];
        for (int i = 1; i < dp.length; i++){
            dp[i] = (s.charAt(i - 1) == '0') ? 0 : dp[i - 1];
            if (i > 1 && (s.charAt(i - 2) == '1' || s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')){
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(1&7);
        String s1 = "12";
        System.out.println(decode(s1));
        String s2 = "226";
        System.out.println(decode(s2));
    }
}
