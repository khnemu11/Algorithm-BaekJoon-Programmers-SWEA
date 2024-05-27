import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int dp[][] = new int[K+1][N+1];

        for(int i=1;i<=K;i++){
            st = new StringTokenizer(br.readLine());
            int I = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            for(int time=1; time<T && time <=N;time++){
                dp[i][time] = dp[i-1][time];
            }

            for(int time=T;time<=N;time++){
                dp[i][time] = Math.max(dp[i-1][time - T] + I,dp[i-1][time]);
            }
        }

        System.out.println(dp[K][N]);
    }
}