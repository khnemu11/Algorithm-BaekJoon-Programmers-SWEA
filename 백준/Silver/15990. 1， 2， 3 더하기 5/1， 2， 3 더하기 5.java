import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    final static int MOD = 1_000_000_009;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int T = Integer.parseInt(br.readLine());
        long[][] dp = new long[100001][4];

        dp[1][1] = 1;

        dp[2][2] = 1;

        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for(int i=4;i<dp.length;i++){
            dp[i][1] = (dp[i-1][3] + dp[i-1][2]) % MOD;
            dp[i][2] = (dp[i-2][1] + dp[i-2][3]) % MOD;
            dp[i][3] = (dp[i-3][2] + dp[i-3][1]) % MOD;
        }

        for(int testcase=1;testcase<=T;testcase++){
            int n = Integer.parseInt(br.readLine());
            long sum = 0;

            for(int i=1;i<=3;i++){
                sum = (sum + dp[n][i]) % MOD;
            }

            System.out.println(sum);
        }
    }
}
