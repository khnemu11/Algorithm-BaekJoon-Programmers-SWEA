import java.io.*;

public class Main {
    final static int MOD = 1_000_000_009;
    final static int MAX = 1_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        int[] dp = new int[MAX+1];

        dp[1] = 1;  //1
        dp[2] = 2;  //2, 1+1
        dp[3] = 4;  //3, 2+1, 1+1+1, 1+2

        for(int i=4;i<dp.length;i++){
            dp[i] = ((dp[i-1]%MOD) + (dp[i-2]%MOD))%MOD;
            dp[i] = ((dp[i]%MOD) + (dp[i-3]%MOD))%MOD;
        }

        int T = Integer.parseInt(br.readLine());

        for(int testcase=1;testcase<=T;testcase++){
            int n = Integer.parseInt(br.readLine());

            System.out.println(dp[n]);
        }
    }
}

