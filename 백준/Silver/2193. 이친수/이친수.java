import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		long dp[] = new long[91];
		dp[0]=0;
		dp[1]=1;
		int n = Integer.valueOf(br.readLine());
		
		for(int i=2;i<=n;i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		bw.write(String.valueOf(dp[n]));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
