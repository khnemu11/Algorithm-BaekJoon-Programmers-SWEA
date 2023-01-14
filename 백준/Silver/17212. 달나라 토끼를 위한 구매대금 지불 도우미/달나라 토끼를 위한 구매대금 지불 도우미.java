import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int target = Integer.valueOf(br.readLine());
		int dp[] = new int[target + 1];
		int coins[] = { 2, 5, 7 };
		for (int i = 1; i <= target; i++) {
			dp[i] = dp[i - 1] + 1;
			for (int coin : coins) {
				if (i - coin < 0) {
					continue;
				}
				dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
			}
		}
		bw.write(String.valueOf(dp[target]));
		bw.newLine();
		bw.flush();
	}
}
