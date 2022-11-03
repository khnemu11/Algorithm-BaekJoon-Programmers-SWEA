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
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken());
		int K = Integer.valueOf(st.nextToken());

		int dp[][] = new int[K + 1][N + 1];

		for (int i = 0; i <= K; i++) {
			dp[i][0] = 1;
		}

		for (int i = 1; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				int sum = 0;
				for (int k = 0; k <= j; k++) {
					sum = (sum + dp[i - 1][k]) % 1000000000;
				}
				dp[i][j] = sum;
			}
		}

		bw.write(String.valueOf(dp[K][N]));
		bw.newLine();
		br.close();
		bw.close();
	}

}