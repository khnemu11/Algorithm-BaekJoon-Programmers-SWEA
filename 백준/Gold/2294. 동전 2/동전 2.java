import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int size = Integer.valueOf(st.nextToken());
		int goal = Integer.valueOf(st.nextToken());

		long dp[] = new long[goal + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int k = 0; k < size; k++) {
			int coin = Integer.valueOf(br.readLine());
			if (coin > goal) {
				continue;
			}
			dp[coin] = 1;

			for (int i = coin + 1; i <= goal; i++) {
				dp[i] = Math.min(dp[i], dp[i - coin] + 1);
			}
		}
		if (dp[goal] == Integer.MAX_VALUE) {
			bw.write("-1");
		} else {
			bw.write(String.valueOf(dp[goal]));
		}
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

}
