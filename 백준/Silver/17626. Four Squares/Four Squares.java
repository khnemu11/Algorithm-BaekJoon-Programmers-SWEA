import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int num = Integer.valueOf(br.readLine());
		int dp[] = new int[num + 1];

		dp[0] = 0;
		dp[1] = 1;

		for (int i = 2; i <= num; i++) {
			dp[i] = i;
			for (int j = 1; j <= Math.sqrt(i); j++) {
				dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
			}
		}

		bw.write(String.valueOf(dp[num]));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
