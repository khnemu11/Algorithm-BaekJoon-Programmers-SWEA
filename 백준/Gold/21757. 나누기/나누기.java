import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] num = new long[N+1];
        long[] sum = new long[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++){
            num[i] = Long.parseLong(st.nextToken());
            sum[i] = sum[i-1] + num[i];
        }

        long[][] dp = new long[N+1][5];
        long count = 0;

       if(sum[N] %4 ==0){
            long target = sum[N]/4;

            dp[0][0] = 1;

            for(int i=1;i<=N;i++){
                dp[i][0] = 1;
                for(int j=1;j<=3;j++){
                    dp[i][j] = dp[i-1][j];

                    if(sum[i] == target*j){
                        dp[i][j] += dp[i-1][j-1];
                    }
                }
            }

            count = dp[N-1][3];
        }

        System.out.println(count);
    }
}
