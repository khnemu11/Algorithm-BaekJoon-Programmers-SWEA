import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
	public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       long[][] dp = new long[31][31];
       
       //반조각만 남았을 때 먹을 수 있는 경우의 수는 1개이다.
       for(int i=0;i<dp.length;i++) {
    	   dp[0][i] = 1;
       }
       
       for(int full=1;full<=30;full++) {
    	   for(int half=0;half<30;half++) {
    		   //반쪽이 없어서 1개를 먹어야 되는경우
        	   if(half == 0) {
        		   dp[full][half] = dp[full-1][half+1];
        	   }
        	   //반쪽과 1개짜리가 둘다 있는 경우
        	   else {
        		   dp[full][half] = dp[full-1][half+1] + dp[full][half-1];
        	   }
           }
       }
       
       
       int N  = 0;
       
       while((N = Integer.parseInt(br.readLine()))!= 0) {
    	   //N개의 1개짜리 약과 0개의 반쪽짜리 약을 먹는 경우 출력
    	   System.out.println(dp[N][0]);
       }
	}
}