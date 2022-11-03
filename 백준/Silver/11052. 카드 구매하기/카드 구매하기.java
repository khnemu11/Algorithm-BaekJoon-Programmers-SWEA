import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		int num[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int dp[] = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			int cost = num[i - 1];

			for (int j = i; j <= N; j++) {
				dp[j] = Math.max(dp[j - i] + cost, dp[j]);
			}
		}

		bw.write(String.valueOf(dp[N]));
		bw.newLine();
		br.close();
		bw.close();
	}

}