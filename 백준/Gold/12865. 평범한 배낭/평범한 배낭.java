import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int num = Integer.valueOf(st.nextToken());
		int maxWeight = Integer.valueOf(st.nextToken());
		
		int weights[] = new int [num+1];
		int values[] = new int[num+1];
		
		for(int i=1;i<=num;i++) {
			st = new StringTokenizer(br.readLine());
			weights[i] =  Integer.valueOf(st.nextToken());
			values[i] =  Integer.valueOf(st.nextToken());
		}
		
		int dp [][] = new int [num+1][maxWeight+1];
		
		for(int i=1;i<=num;i++) {
			for(int j=0;j<=maxWeight;j++) {
				if(weights[i] > j) {
					dp[i][j] = dp[i-1][j]; 
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weights[i]]+values[i]);
				}
			}
		}
		bw.write(String.valueOf(dp[dp.length-1][dp[0].length-1]));
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
	}
}
