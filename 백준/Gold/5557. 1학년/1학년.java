import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N+1][21];
        int[]num = new int[N+1];

        int[]input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i=0;i<input.length;i++){
            num[i+1] = input[i];
        }

        dp[1][num[1]] = 1;

        for(int i=2;i<N;i++){
            for(int j=0;j<=20;j++){
                if(dp[i-1][j] > 0) {
                    if(j-num[i] >= 0){
                        dp[i][j-num[i]] += dp[i-1][j];
                    }
                    if(j+num[i]<=20){
                        dp[i][j+num[i]] += dp[i-1][j];
                    }
                }
            }
        }

        System.out.println(dp[N-1][num[N]]);
    }
}