import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long MOD = 1_000_000_007;

        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N+1][5];
        dp[0][0] = 1;

        for(int i=0;i<dp.length-1;i++){
            for(int j=0;j<dp[i].length;j++){
                if(dp[i][j] > 0){
                    if(j == 0){
                        dp[i+1][0] = (dp[i+1][0] + dp[i][j]*25% MOD) % MOD;
                        dp[i+1][1] = (dp[i+1][1] + dp[i][j]) % MOD;
                    }
                    else if(j == 1){
                        dp[i+1][1] = (dp[i+1][1] + dp[i][j]*25% MOD) % MOD;
                        dp[i+1][2] = (dp[i+1][2] + dp[i][j]) % MOD;
                    }
                    else if(j == 2){
                        dp[i+1][2] = (dp[i+1][2] + dp[i][j]*25% MOD) % MOD;
                        dp[i+1][3] = (dp[i+1][3] + dp[i][j]) % MOD;
                    }
                    else if(j == 3){
                        dp[i+1][3] = (dp[i+1][3] + dp[i][j]*25% MOD) % MOD;
                        dp[i+1][4] = (dp[i+1][4] + dp[i][j]) % MOD;
                    }
                    else if(j == 4){
                        dp[i+1][4] = (dp[i+1][4] + dp[i][j]*26% MOD) % MOD;
                    }
                }
            }
        }
        System.out.println(dp[N][4]);
    }
}