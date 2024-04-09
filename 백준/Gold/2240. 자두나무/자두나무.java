import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 17: 29
 * */

public class Main {
	public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       int T = Integer.parseInt(st.nextToken());
       int W = Integer.parseInt(st.nextToken());
       
       int[][][]dp = new int[3][W+1][T+1];
       
       int no = Integer.parseInt(br.readLine());
       
       if(no == 1) {
    	   dp[1][0][1] = 1;
       }else {
    	   dp[2][1][1] = 1;
       }
       
       for(int i=2;i<=T;i++) {
    	   no = Integer.parseInt(br.readLine());
    	   
    	   dp[no][0][i] = Math.max(dp[no][0][i], dp[no][0][i-1] + 1);
		   dp[no%2 + 1][0][i] = Math.max(dp[no%2 + 1][0][i], dp[no%2 + 1][0][i-1]);
    	   
    	   for(int w=1;w<=W; w++) {
			   dp[no][w][i] = Math.max(dp[no][w][i-1], dp[no%2 + 1][w-1][i-1]) + 1;
			   dp[no%2 + 1][w][i] = Math.max(dp[no][w-1][i-1], dp[no%2 + 1][w][i-1]);
    	   }
       }
       
       int max = 0;

	   for(int w=0;w<=W;w++) {
		   max =Math.max(max,Math.max(dp[1][w][T], dp[2][w][T]));
	   }
       
       System.out.println(max);
	}
}