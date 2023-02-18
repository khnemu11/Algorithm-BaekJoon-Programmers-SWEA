import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.valueOf(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.valueOf(br.readLine());
			int coins[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
			int target = Integer.valueOf(br.readLine());

			int dp[][] = new int[N + 1][target + 1];

			for (int coin = 0; coin < coins.length; coin++) {
				for (int i = 1; i < coins[coin]; i++) {
					dp[coin + 1][i] = dp[coin][i];
				}
				dp[coin + 1][coins[coin]] = dp[coin][coins[coin]] + 1;
				for (int i = coins[coin] + 1; i < dp[0].length; i++) {
					if (dp[coin + 1][i - coins[coin]] == 0) {
						dp[coin + 1][i] = dp[coin][i];
					} else {
						dp[coin + 1][i] = dp[coin][i] + dp[coin + 1][i - coins[coin]];
					}
				}
			}
			bw.write(dp[N][target] + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}