import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


/*
*
* 22:36
*
* 15의 배수
* 1) 5로 끝나야함
* 2) 각자리수의 합이 3의 배수여야함
*   1+5
*   1+1+5+5
*
*
*   dp[n] = dp[n-3]*2
* 1) 1자리수
*  없음
* 2) 2자리수
* 15
* -> 1개
* 3) 3자리수
* 555
* 4) 4자리수
* 1515
* 5115
* 1155
*
* 5) 5자리수
* 11115
* -> 1개
*
*
* 15에 111추가하기 or 15추가하기
*
*dp[n] = dp[n-2]*3 + dp[n-3]*2
*
* */


public class Main {
    static int MOD = 1_000_000_007;
    static int INF = -1;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new long[1516];
        Arrays.fill(dp,-1);

        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        System.out.println(getCount(N));
    }

    public static long getCount(int n){
        if(dp[n] != INF){
            return dp[n] % MOD;
        }

        dp[n] = ((getCount(n-2)*3) % MOD + (getCount(n-3)*2) % MOD)%MOD;

        return dp[n];
    }
}