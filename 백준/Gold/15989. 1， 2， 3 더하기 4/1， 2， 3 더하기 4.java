import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int max = 10_000;
        int[][] dp = new int[max+1][4];

        dp[1][1] = 1; //1

        dp[2][1] = 1; //1+1
        dp[2][2] = 1; //2

        dp[3][1] = 1; //1+1+1
        dp[3][2] = 1; //1+2
        dp[3][3] = 1; //3

        for(int i=4;i<=max;i++){
            dp[i][1] = dp[i-1][1];
            dp[i][2] = dp[i-2][1] + dp[i-2][2];
            dp[i][3] = dp[i-3][1]  +dp[i-3][2]+dp[i-3][3];
        }

        int T = Integer.parseInt(br.readLine());

        for(int testcase =1; testcase<=T;testcase++){
            int n = Integer.parseInt(br.readLine());

            System.out.println(Arrays.stream(dp[n]).sum());
        }
    }
}