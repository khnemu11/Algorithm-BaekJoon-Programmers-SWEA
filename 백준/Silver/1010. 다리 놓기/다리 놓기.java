import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T =Integer.valueOf(br.readLine());
		long dp[][] = new long[31][31];
		
		
		
		for(int i=0;i<T;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int left = Integer.valueOf(st.nextToken());
			int right = Integer.valueOf(st.nextToken());
		
			bw.write(String.valueOf(bico(dp,right,left)));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static long bico(long [][]dp,int n,int k) {
		if(dp[n][k]>0) {
			return dp[n][k];
		}
		
		if(n==k || k==0) {
			dp[n][k]=1;
		}
		else {
			dp[n][k] = bico(dp,n-1,k) + bico(dp,n-1,k-1);
		}

		return dp[n][k]; 
	}
}