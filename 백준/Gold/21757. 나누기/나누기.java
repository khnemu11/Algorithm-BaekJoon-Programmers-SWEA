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

        long[][] dp = new long[4][N+1];
        Arrays.fill(dp[0],1);

        if(sum[N] % 4 == 0){
            long target = 0;

            for(int i=1;i<dp.length;i++){
                target += sum[N]/4;

                for(int j=1;j<=N;j++){
                    dp[i][j] = dp[i][j-1];

                    if(sum[j] == target){
                        dp[i][j] += dp[i-1][j-1];
                    }
                }
            }
        }

        long count = dp[3][N];

        System.out.println(count);
    }
}
