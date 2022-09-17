import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int length = Integer.valueOf(br.readLine());

		int[] dp = new int[1001];

		dp[1] = 1;
		dp[2] = 3;

		for (int i = 3; i <= length; i++) {
			dp[i] = (dp[i - 1] % 10007 + dp[i - 2] % 10007+dp[i - 2] % 10007) % 10007;

		}

		bw.write(String.valueOf(dp[length]));
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}
}
