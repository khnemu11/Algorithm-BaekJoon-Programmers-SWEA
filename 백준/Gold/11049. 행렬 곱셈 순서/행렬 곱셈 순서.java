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

		int size = Integer.valueOf(br.readLine());
		int matrix[] = new int[size + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());

		matrix[0] = Integer.valueOf(st.nextToken());
		matrix[1] = Integer.valueOf(st.nextToken());

		for (int i = 2; i < matrix.length; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			matrix[i] = Integer.valueOf(st.nextToken());
		}

		int dp[][] = new int[matrix.length][matrix.length];

		for (int len = 2; len < dp.length; len++) {
			for (int from = 1; from + len - 1 < dp.length; from++) {
				int to = from + len - 1;
				dp[from][to] = Integer.MAX_VALUE;
				for (int mid = from; mid < to; mid++) {
					dp[from][to] = Math.min(
							dp[from][mid] + dp[mid + 1][to] + matrix[mid] * matrix[to] * matrix[from - 1],
							dp[from][to]);
				}
			}
		}

		bw.write(String.valueOf(dp[1][dp.length - 1]));
		bw.newLine();
		bw.flush();
	}
}
