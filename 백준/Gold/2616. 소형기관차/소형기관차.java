import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N  =Integer.parseInt(br.readLine());
        int[] sum = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++){
            int customer = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1] + customer;
        }

        int M = Integer.parseInt(br.readLine());

        int[][] dp = new int[4][N+1];

        for(int i=1;i<dp.length;i++){
            for(int j=M;j<dp[i].length;j++){
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                dp[i][j] = Math.max(dp[i][j],dp[i-1][j-M]+sum[j] - sum[j-M]);
            }
        }

        System.out.println(dp[3][dp[0].length-1]);
    }
}
