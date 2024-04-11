import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


/*
 	걸린 시간 : 1시간 20분
 	
 	1~현재 색을 이용해서 N의 길이가 가능한 개수
 	= 현재 색을 사용하지않고 1~이전색을 이용해서 N의 길이가 가능한 개수 
 	  + 현재 색을 사용하고 1~이전색을 이용해서 N-1의 길이가 가능한 개수
 	
 	이때 첫번째 색을 사용하면 N번째 색을 사용할 수 없으므로 1을 사용한 경우와 사용하지 않는 경우를 나워서 해결한다.
*/

public class Main {
	static long MOD = 1_000_000_003;
	
	public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       int N = Integer.parseInt(br.readLine());
       int K = Integer.parseInt(br.readLine());

       //짝수면서 그룹화가 불가능한 경우
       if(N%2 == 0 && K > N/2) {
    	   System.out.println(0);
       }
       //홀수면서 그룹화가 불가능한 경우
       else if(N%2 == 1 && K > N/2 + 1) {
    	   System.out.println(0);
       }
       //그룹화가 가능한 경우
       else {
		   //1~현재까지 그룹화가 가능한 개수[0 : 1을 사용하지 않음, 1:1을 사용함][길이][색깔번호]
		   long dp[][][] = new long[2][K+1][N+1];
		   
		   //1를 사용하지 않은 경우 초기화
		   dp[0][1][2] = 1;
		   
		   for(int i=3;i<=N;i++) {
			   dp[0][1][i] = dp[0][1][i-1]+1;
		   }
		   
		   //1을 사용한 경우 초기화
		   Arrays.fill(dp[1][1], 1);
		   dp[1][1][0] = 0;
		   dp[1][1][N] = 0;
		   
		   //길이 2부터 탐색
		   for(int k=2;k<=K;k++) {
			   for(int n=k*2-1;n<=N;n++) {
				   //1을 사용하지 않고 2~n번째 색까지 중에서 k길이를 만들 수 있는 그룹화 개수 
				   dp[0][k][n] = (dp[0][k][n-1] + dp[0][k-1][n-2] - dp[0][k-1][1])%MOD;
				   
				   //1을 사용하고 1~n번째 색까지 중에서 k길이를 만들 수 있는 그룹화 개수
				   //= 1~n-1번째 색까지 k개를 고르고 현재 색을 안 고르는 경우 + k-1길이 중 1~n-1색까지 고르는 최대 경우
				   dp[1][k][n] = (dp[1][k][n-1] + dp[1][k-1][n-2] - dp[1][k-1][0])%MOD;
	    	   }
		   }
		   
		   long sum = (dp[0][K][N] + dp[1][K][N-1])%MOD;
		   
		   System.out.println(sum);
       }
	}
}