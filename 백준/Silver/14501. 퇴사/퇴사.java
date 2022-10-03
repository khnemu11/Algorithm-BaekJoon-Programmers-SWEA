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
		int dp[][] = new int[16][16];

		int day = Integer.valueOf(br.readLine());

		for (int i = 1; i <= day; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.valueOf(st.nextToken());
			int earn = Integer.valueOf(st.nextToken());

			if (i + time - 1 > day) {
				for (int j = 1; j <= day; j++) {
					dp[i][j] = dp[i - 1][j];
				}
			} else {
				for (int j = 1; j < time + i - 1; j++) {
					dp[i][j] = dp[i - 1][j];
				}

				if (dp[i - 1][time + i - 1] < dp[i][i-1] + earn) {
					dp[i][time + i - 1] = dp[i][i-1] + earn;
				} else {
					dp[i][time + i - 1] = dp[i - 1][time + i - 1];
				}

				for (int j = time + i; j <= day; j++) {
					if (dp[i - 1][j] < dp[i][j - 1]) {
						dp[i][j] = dp[i][j - 1];
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}
		}
		bw.write(String.valueOf(dp[day][day]));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
