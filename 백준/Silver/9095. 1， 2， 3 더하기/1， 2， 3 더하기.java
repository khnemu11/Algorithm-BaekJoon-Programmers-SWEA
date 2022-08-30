import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int dp[];

	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());
		dp = new int[11];
		dp[0] = dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		for (int i = 4; i <= 10; i++) {
			dp[i]=dp[i-3]+dp[i-1]+dp[i-2];
		}

		for(int i=0;i<T;i++) {
			bw.write(String.valueOf(dp[Integer.valueOf(br.readLine())]));
			bw.newLine();
		}

		bw.close();
		br.close();
	}

}
