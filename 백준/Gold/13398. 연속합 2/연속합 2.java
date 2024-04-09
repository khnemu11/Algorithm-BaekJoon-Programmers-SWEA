import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int NOT_DELETE = 0;
	static int DELETE = 1;
	
	public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int n = Integer.parseInt(br.readLine());
       
       int[]num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
       int[][]dp = new int[2][n];
       
       dp[NOT_DELETE][0] = num[0];
       dp[DELETE][0] = num[0];
       int max = num[0];
       
       for(int i=1;i<dp[0].length;i++) {
    	   dp[NOT_DELETE][i] = Math.max(dp[NOT_DELETE][i-1] + num[i], num[i]);
    	   dp[DELETE][i] = Math.max(dp[NOT_DELETE][i-1], dp[DELETE][i-1] + num[i]);
    	   
    	   max = Math.max(dp[DELETE][i],max);
    	   max = Math.max(max,dp[NOT_DELETE][i]);
       }
       
       System.out.println(max);
       
	}
}