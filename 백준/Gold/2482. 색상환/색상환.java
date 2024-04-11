import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


/*
 * 처음과 끝은 같이 사용 볼가
 * 
 * 1~N-1를 사용하는 경우
 * 2~N을 사용하는 경우를 구하면 됨
 * 
 * 1을 사용하는 경우 + sum(3~N-1)을 사용하는 경우
 * dp[길이][색]
 * 
 * N = 6
 *
 * 1을 쓰는 경우
 *
 * dp[1을 쓰는 경우][길이 2][1] = 0
 * dp[1을 쓰는 경우][길이 2][2] = 0
 * dp[1을 쓰는 경우][길이 2][3] = 1
 * dp[1을 쓰는 경우][길이 2][4] = 2
 * dp[1을 쓰는 경우][길이 2][5] = 3
 * 
 * dp[1을 쓰는 경우][길이 3][1] = 0
 * dp[1을 쓰는 경우][길이 3][2] = 0
 * dp[1을 쓰는 경우][길이 3][3] = 0
 * dp[1을 쓰는 경우][길이 3][4] = 0
 * dp[1을 쓰는 경우][길이 3][5] = dp[1을 쓰는 경우][길이 2][3] = 1
 * 
 * dp[1을 안쓰는 경우][길이 2][1] = 0
 * dp[1을 안쓰는 경우][길이 2][2] = 0
 * dp[1을 안쓰는 경우][길이 2][3] = 0
 * dp[1을 안쓰는 경우][길이 2][4] = dp[1을 안쓰는 경우][길이 1][2] = 1
 * dp[1을 안쓰는 경우][길이 2][5] = dp[1을 안쓰는 경우][길이 1][2] + dp[1을 안쓰는 경우][길이 1][3] = 2
 * dp[1을 안쓰는 경우][길이 2][6] = dp[1을 안쓰는 경우][길이 1][2] + dp[1을 안쓰는 경우][길이 1][3] + dp[1을 안쓰는 경우][길이 2][4]= 3
 * 
 * dp[1을 안쓰는 경우][길이 3][1] = 0
 * dp[1을 안쓰는 경우][길이 3][2] = 0
 * dp[1을 안쓰는 경우][길이 3][3] = 0
 * dp[1을 안쓰는 경우][길이 3][4] = 0
 * dp[1을 안쓰는 경우][길이 3][5] = 0
 * dp[1을 안쓰는 경우][길이 3][6] = dp[1을 안쓰는 경우][길이 2][4] = 1
 * 
 * 1 2 3 4 5 6
 * 
 * 1 3/1 4/1 5
 * 2 4/2 5/2 6
 * 3 5/3 6
 * 4 6
 * 
 * 길이 3)
 * 
 * dp[3][1] = X = 0
 * dp[3][2] = X = 0
 * dp[3][3] = X = 0
 * dp[3][4] = dp[2][1] = 0 
 * dp[3][5] = dp[2][1] + dp[2][2] + dp[2][3] = 1
 * dp[3][6] = dp[2][2] + dp[2][3] + dp[2][4]= 3
 * 
 * 1 2 3 4 5 6
 * 
 * 1 3 5
 * 2 4 6
 * 
 * 2개
 * */

public class Main {
	//dp[색을 고른 횟수][색의 개수]
	static long[][] dp;
	static boolean[][] visited;
	static long MOD = 1_000_000_003;
	
	public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       int N = Integer.parseInt(br.readLine());
       int K = Integer.parseInt(br.readLine());
       
       dp = new long[K+1][N+1];
       visited = new boolean[K+1][N+1];
       
       if(N%2 == 0 && K > N/2) {
    	   System.out.println(0);
       }
       else if(N%2 == 1 && K > N/2 + 1) {
    	   System.out.println(0);
       }
       else {
    	   long count = 0;
    	   long dp[][][] = new long[2][K+1][N+1];
    	   
    	   //1를 사용하지 않은 경우 초기화
    	   
    	   dp[0][1][2] = 1;
    	   
    	   for(int i=3;i<=N;i++) {
    		   dp[0][1][i] = dp[0][1][i-1]+1;
    	   }
    	   
    	   //1을 사용한 경우 초기화
    	   dp[1][1][1] = 1;
    	   Arrays.fill(dp[1][1], 1);
    	   dp[1][1][0] = 0;
    	   dp[1][1][N] = 0;
    	   
    	   for(int k=2;k<=K;k++) {
    		   for(int n=k*2-1;n<=N;n++) {
    			   dp[0][k][n] = (dp[0][k][n-1] + dp[0][k-1][n-2] - dp[0][k-1][1])%MOD;
    			   dp[1][k][n] = (dp[1][k][n-1] + dp[1][k-1][n-2] - dp[1][k-1][0])%MOD;
    			   
//    			   for(int i=1; i<=n-2;i++) {
//    				   if(n == 1) {
//    					   dp[1][k][n] = (dp[1][k][n] + dp[1][k-1][i]) % MOD;
//    				   }else if(n == N) {
//    					   dp[0][k][n] = (dp[0][k][n] + dp[0][k-1][i]) % MOD;
//    				   }else {
//    					   dp[1][k][n] = (dp[1][k][n] + dp[1][k-1][i]) % MOD;
//    					   dp[0][k][n] = (dp[0][k][n] + dp[0][k-1][i]) % MOD;
//    				   }
//    				   
//    			   }
        	   }
    	   }
    	  
//    	   System.out.println(Arrays.deepToString(dp));
    	   
    	   long sum = (dp[0][K][N] + dp[1][K][N-1])%MOD;

    	   
    	   System.out.println(sum);
       }
	}
}