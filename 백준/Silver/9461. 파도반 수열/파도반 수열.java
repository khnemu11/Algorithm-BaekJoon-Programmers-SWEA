import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static long []dp=new long[101];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		dp[0]=0;
		dp[1]=1;
		dp[2]=1;
		dp[3]=1;
		
		int T = Integer.valueOf(br.readLine());
		
		for(int i=0;i<T;i++) {
			int n = Integer.valueOf(br.readLine());

			
			bw.write(String.valueOf(p(n)));
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();

	}

	public static long p(int n) {		
		if(n>3) {
			for(int i=4;i<=n;i++) {
				dp[i]=dp[i-2]+dp[i-3];
			}
		}
		
		return dp[n];
	}
}
