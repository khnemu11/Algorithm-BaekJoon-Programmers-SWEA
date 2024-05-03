import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
*
*   dp[n][전체] = dp[n-1](2*1와 1*1을 세로로 붙인경우) + dp[n-2](1*2를 2개붙인 경우) + dp[n-1](위에만 튀어나온 경우에 1*1과 1*2를 사용한 경우) +  dp[n-1](아래만 튀어나온 경우에 1*1과 1*2를 사용한 경우)
*              = dp[n-1][전체] * 2 + dp[n-2][전체] + dp[n-1][위] + dp[n-1][아래]
*
*   dp[n][위] = dp[n-1](전체에 위에만 1*1 블록을 붙인경우) + dp[n-1][아래](위에다가 1*2를 븥인 경우)
*             = dp[n-1][전체] + dp[n-1][아래]
*
*   dp[n][아래] = dp[n-1](전체에 아래에만 1*1 블록을 붙인경우) + dp[n-1][위](아래에다가 1*2를 븥인 경우)
*             = dp[n-1][전체] + dp[n-1][위]
* */

public class Main {
    public static void main(String[] args) throws Exception {
        int mod = 1_000_000_007;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[1_000_000+1][3];

        dp[1][0] = 2;
        dp[1][1] = 1;
        dp[1][2] = 1;

        dp[2][0] = 7;
        dp[2][1] = 3;
        dp[2][2] = 3;

        for(int i=3;i<=N;i++){
            dp[i][0] = (dp[i-1][0] * 2 + dp[i-2][0] + dp[i-1][1] + dp[i-1][2]) % mod;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2])%mod;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1])%mod;
        }

        System.out.println(dp[N][0]);
    }
}