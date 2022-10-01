import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	static boolean visited[][];
	static BigInteger dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		visited = new boolean[n + 1][n + 1];
		dp = new BigInteger[n + 1][n + 1];

		BigInteger result = combination(n, m);
		bw.write(result.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static BigInteger combination(int n, int m) {
		if (visited[n][m] == true) {
			return dp[n][m];
		}

		visited[n][m] = true;

		if (m == 0 || m == n) {
			dp[n][m] = new BigInteger("1");
			return dp[n][m];
		} else {
			dp[n][m] = combination(n - 1, m).add(combination(n - 1, m - 1));
			return dp[n][m];
		}
	}
}
